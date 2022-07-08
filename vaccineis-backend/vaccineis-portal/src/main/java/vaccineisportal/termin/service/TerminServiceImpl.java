package vaccineisportal.termin.service;

import lombok.AllArgsConstructor;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vaccineisportal.authentication.service.AuthenticationService;
import vaccineisportal.interesovanje.model.Interesovanje;
import vaccineisportal.interesovanje.repository.InteresovanjeExistRepository;
import vaccineisportal.interesovanje.service.InteresovanjeService;
import vaccineisportal.termin.model.Termin;
import vaccineisportal.termin.repository.TerminExistRepository;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.model.util.ResultSetConnection;
import zajednicko.repository.CRUDRDFRepository;
import zajednicko.service.MailService;
import zajednicko.service.MarshallingService;
import zajednicko.util.ZajednickoUtil;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@AllArgsConstructor
@Service
public class TerminServiceImpl implements TerminService{

    protected final TerminExistRepository terminExistRepository;
    private final CRUDRDFRepository crudrdfRepository;
    private final MarshallingService marshallingService;
    private final AuthenticationService authenticationService;
    private final MailService mailService;
    private final InteresovanjeExistRepository interesovanjeExistRepository;

    @Override
    public Termin addTermin(String termin) {
        return terminExistRepository.create(termin);
    }

    @Override
    public Termin getTermin(String uuid) {
        return terminExistRepository.findOne(uuid);
    }

    @Override
    public List<Termin> findTermin(String query) {
        return terminExistRepository.findXpath(query);
    }



    @Override
    public List<Termin> getAll() {
        return terminExistRepository.findAll();
    }

    @Override
    public Termin zakaziPrviSlobodan(String interesovanjeUri, LocalDateTime localDateTime) throws DatatypeConfigurationException {
        if (localDateTime == null) localDateTime = findFreeTermin();
        if (localDateTime == null) return null;

        Termin termin = new Termin();
        String uuid = ZajednickoUtil.getIdFromUri(interesovanjeUri);

        XMLGregorianCalendar xmlGregorianCalendar =
                DatatypeFactory.newInstance().newXMLGregorianCalendar(localDateTime.toString() + ":00");
        termin.setDatumVrijeme(xmlGregorianCalendar);

        String korisnikUri = crudrdfRepository.findFirstBySubjectAndPred("rdf", ZajednickoUtil.XML_PREFIX + "interesovanje/" + uuid, "interesovanje_korisnik");
        termin.setKorisnikId(ZajednickoUtil.getIdFromUri(korisnikUri));
        termin.setInteresovanjeId(uuid);
        String varijabla = marshallingService.marshall(termin, Termin.class);
        Termin t = addTermin(varijabla);
        saveMetadata(t);
        return t;
    }


    private LocalDateTime findFreeTermin() {
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(7);
        int max_termina = 10;

        Map<LocalDate, Integer> broj_termina = new HashMap<>();
        for (int i = 1; i <= 7; ++i) {
            broj_termina.put(LocalDate.now().plusDays(i), 0);
        }

        List<Termin> termini = getAll();
        for (Termin t : termini) {
            LocalDate localDate = LocalDate.of(
                    t.getDatumVrijeme().getYear(),
                    t.getDatumVrijeme().getMonth(),
                    t.getDatumVrijeme().getDay());

            if (localDate.isBefore(startDate) || localDate.isAfter(endDate)) continue;

            Integer br = broj_termina.getOrDefault(localDate, 0) + 1;
            broj_termina.put(localDate, br);
        }

        LocalDate freeDay;
        LocalDateTime freeAppointment;

        List<LocalDate> dates = new ArrayList<>(broj_termina.keySet());
        dates.sort(LocalDate::compareTo);
        for ( LocalDate key : dates){
            if (broj_termina.get(key) < max_termina) {
                freeDay = key;
                return freeDay.atTime(LocalTime.of(8,0, 0)).plusHours(broj_termina.get(key));

            }
        }

        return null;
    }

    @Override
    public void saveMetadata(Termin termin) {
        crudrdfRepository.uploadTriplet("rdf",ZajednickoUtil.XML_PREFIX + "korisnik/" + termin.getKorisnikId().toString(), "termin", ZajednickoUtil.XML_PREFIX + "termin/" + termin.getId());
        crudrdfRepository.uploadTriplet("rdf",ZajednickoUtil.XML_PREFIX + "termin/" + termin.getId(), "termin_ceka_potvrdu", ZajednickoUtil.XML_PREFIX + "korisnik/" + termin.getKorisnikId());
    }

    @Override
    @Scheduled(cron = "0 0 * * * ?") // Svaki sat u *:00:00
    public void terminZaInteresovanje() {
        System.out.println("CRON ----- TerminServiceImpl.terminZaInteresovanje");
        ResultSetConnection resultsCon = crudrdfRepository.findByPredicate("rdf", "ceka_od");
        ResultSet results = resultsCon.getResultSet();

        Map<String, String> zahtevi = new HashMap<>();

        for (ResultSet it = results; it.hasNext(); ) {
            QuerySolution s = it.next();
            zahtevi.put(s.get("s").toString(), s.get("o").toString());
        }

        if (zahtevi.size() == 0){
            resultsCon.closeConnection();
            return;
        }

        List<Map.Entry<String, String>> zahteviList = new ArrayList<>(zahtevi.entrySet());
        zahteviList.sort(Comparator.comparing(Map.Entry::getValue));

        for (Map.Entry<String, String> entry : zahteviList){
            LocalDateTime dobijen = srediTerminZa(entry.getKey(), entry.getValue());
            if (dobijen == null) {
                resultsCon.closeConnection();
                return;
            }

            List<String> splits = Arrays.asList(entry.getKey().split("/"));
            String interesovanjeId = splits.get(splits.size() - 1);
            Interesovanje interesovanje = interesovanjeExistRepository.findOne(interesovanjeId);

            String email = interesovanje.getLicneInformacije().getKontakt().getEmail();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            String date = dobijen.format(formatter);

            mailService.sendMail(email,
                    "Слободан термин за пријем вакцине против COVID-19",
                    "Поштовани " + interesovanje.getLicneInformacije().getIme() + ", <br><br>" +
                            "Додељен вам је слободан термин за вакцинисање: <br><br>" + date,
                    null);
        }

        resultsCon.closeConnection();
    }

    private LocalDateTime srediTerminZa(String uri, String ceka_od) {
        LocalDateTime freeTermin = this.findFreeTermin();
        try {
            zakaziPrviSlobodan(uri, freeTermin);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        if (freeTermin == null) return null;

        crudrdfRepository.deleteTriplet("rdf", uri, "ceka_od", ceka_od);
        return freeTermin;
    }

    @Override
    public Termin dobaviTerminBezSaglasnosti() {

        Korisnik korisnik = authenticationService.getLoggedInUser();

        String query = "?s <" + ZajednickoUtil.RDF_PREDICATE + "termin_ceka_potvrdu> <" + ZajednickoUtil.XML_PREFIX + "korisnik/" + korisnik.getId() + ">";

        ResultSetConnection resultsCon = crudrdfRepository.findWhere("rdf", query);
        ResultSet results = resultsCon.getResultSet();

        if (!results.hasNext()) {
            resultsCon.closeConnection();
            return null;
        }

        QuerySolution s = results.next();
        resultsCon.closeConnection();
        String terminUri = s.get("s").toString();

        String[] token = terminUri.split("/");
        String terminId = token[token.length - 1];


        Termin termin = getTermin(terminId);
        return termin;
    }
}
