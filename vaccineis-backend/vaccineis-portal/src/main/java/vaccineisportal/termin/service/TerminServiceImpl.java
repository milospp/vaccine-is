package vaccineisportal.termin.service;

import lombok.AllArgsConstructor;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.stereotype.Service;
import vaccineisportal.interesovanje.model.Interesovanje;
import vaccineisportal.interesovanje.service.InteresovanjeService;
import vaccineisportal.termin.model.Termin;
import vaccineisportal.termin.repository.TerminExistRepository;
import zajednicko.model.CTlicniPodaci;
import zajednicko.repository.CRUDRDFRepository;
import zajednicko.service.MarshallingService;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TerminServiceImpl implements TerminService{
    protected final TerminExistRepository terminExistRepository;
    private final CRUDRDFRepository crudrdfRepository;
    private final MarshallingService marshallingService;
    private final InteresovanjeService interesovanjeService;

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
    public Termin zakaziPrviSlobodan(CTlicniPodaci cTlicniPodaci) throws DatatypeConfigurationException {
        LocalDateTime localDateTime = findFreeTermin();
        if (localDateTime == null) return null;

        Termin termin = new Termin();

        XMLGregorianCalendar xmlGregorianCalendar =
                DatatypeFactory.newInstance().newXMLGregorianCalendar(localDateTime.toString() + ":00");
//        termin.setId(String.valueOf(UUID.randomUUID()));
        termin.setId(null);
        termin.setDatumVrijeme(xmlGregorianCalendar);
        termin.setKorisnik(cTlicniPodaci);
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

//        List<Termin> termini = getAll();
//        for (Termin t : termini) {
//            LocalDate localDate = LocalDate.of(
//                    t.getDatumVrijeme().getYear(),
//                    t.getDatumVrijeme().getMonth(),
//                    t.getDatumVrijeme().getDay());
//
//            if (localDate.isBefore(startDate) || localDate.isAfter(endDate)) continue;
//
//            Integer br = broj_termina.getOrDefault(localDate, 0) + 1;
//            broj_termina.put(localDate, br);
//        }

        LocalDate freeDay;
        LocalDateTime freeAppointment;

        List<LocalDate> dates = new ArrayList<>(broj_termina.keySet());
        dates.sort(LocalDate::compareTo);
        for ( Map.Entry<LocalDate, Integer> entry : broj_termina.entrySet()){
            if (entry.getValue() < max_termina) {
                freeDay = entry.getKey();
                return freeDay.atTime(LocalTime.of(8,0, 0)).plusHours(entry.getValue());

            }
        }

        return null;
    }

    @Override
    public void saveMetadata(Termin termin) {
        crudrdfRepository.uploadTriplet("gradjanin","korisnik/" + termin.getKorisnik().getId().toString(), "termin", termin.getId().toString());
    }

    @Override
    public void terminZaInteresovanje() {
        ResultSet results = crudrdfRepository.findByPredicate("rdf", "ceka_od");

        Map<String, String> zahtevi = new HashMap<>();

        for (ResultSet it = results; it.hasNext(); ) {
            QuerySolution s = it.next();
            zahtevi.put(s.get("s").toString(), s.get("o").toString());
        }

        if (zahtevi.size() == 0) return;

        List<Map.Entry<String, String>> zahteviList = new ArrayList<>(zahtevi.entrySet());
        zahteviList.sort(Comparator.comparing(Map.Entry::getValue));


        for (Map.Entry<String, String> entry : zahteviList){
            LocalDateTime dobijen = srediTerminZa(entry.getKey(), entry.getValue());
            if (dobijen == null) return;
        }
    }

    private LocalDateTime srediTerminZa(String uri, String ceka_od) {
        LocalDateTime freeTermin = this.findFreeTermin();
        if (freeTermin == null) return null;

        crudrdfRepository.deleteTriplet("rdf", uri, "ceka_od", ceka_od);
        return freeTermin;
    }
}
