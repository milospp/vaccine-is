package vaccineisemployee.termin.service;

import lombok.AllArgsConstructor;
import net.sf.cglib.core.Local;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Collection;
import vaccineisemployee.termin.model.Termin;
import vaccineisemployee.termin.repository.TerminExistRepository;
import zajednicko.model.CTlicniPodaci;
import zajednicko.repository.CRUDRDFRepository;

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

    @Override
    public Termin addTermin(Termin termin) {
        return terminExistRepository.save(termin);
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
    public Termin zakaziPrviSlovodan(CTlicniPodaci cTlicniPodaci) throws DatatypeConfigurationException {
        LocalDateTime localDateTime = findFreeTermin();
        if (localDateTime == null) return null;

        Termin termin = new Termin();

        XMLGregorianCalendar xmlGregorianCalendar =
                DatatypeFactory.newInstance().newXMLGregorianCalendar(localDateTime.toString());
        termin.setDatumVrijeme(xmlGregorianCalendar);
        termin.setKorisnik(cTlicniPodaci);
        return addTermin(termin);
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
        for ( Map.Entry<LocalDate, Integer> entry : broj_termina.entrySet()){
            if (entry.getValue() < max_termina) {
                freeDay = entry.getKey();
                return freeDay.atTime(LocalTime.of(8,0)).plusHours(entry.getValue());

            }
        }

        return null;
    }

    @Override
    public void saveMetadata(Termin termin) {
        String prefix = "http://www.ftn.uns.ac.rs/";
        crudrdfRepository.uploadTriplet(prefix + "gradjanin",prefix + "korisnik/" + termin.getKorisnik().getId().toString(), "termin", termin.getId().toString());
    }
}
