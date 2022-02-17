package vaccineisemployee.termin.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vaccineisemployee.termin.model.Termin;
import vaccineisemployee.termin.service.TerminService;
import zajednicko.model.CTlicniPodaci;
import zajednicko.model.STpol;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

@AllArgsConstructor
@RestController
@RequestMapping("/api/termin")
public class TerminController {

    private final TerminService terminService;

    @GetMapping()
    public ResponseEntity<String> getInteresovanje() throws DatatypeConfigurationException {
        Termin t = new Termin();
        LocalDate localDate = LocalDate.of(2019, 4, 25);

        XMLGregorianCalendar xmlGregorianCalendar =
                DatatypeFactory.newInstance().newXMLGregorianCalendar("2024-10-10T05:22:33");

        CTlicniPodaci lp = new CTlicniPodaci();
        lp.setIme("marko");
        lp.setPrezime("petrovic");
        lp.setPol(STpol.МУШКО);
        t.setDatumVrijeme(xmlGregorianCalendar);
        t.setKorisnik(lp);
        terminService.addTermin(t);
        System.out.println("Radi");
        return new ResponseEntity<>("true", HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Termin> getInteresovanje(@PathVariable String id) throws DatatypeConfigurationException {

        Termin t = terminService.getTermin(id);
        System.out.println("Radi");
        return new ResponseEntity<>(t, HttpStatus.OK);
    }
}
