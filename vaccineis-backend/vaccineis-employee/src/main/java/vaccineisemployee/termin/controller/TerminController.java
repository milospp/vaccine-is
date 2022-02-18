package vaccineisemployee.termin.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vaccineisemployee.termin.model.Termin;
import vaccineisemployee.termin.service.TerminService;
import zajednicko.model.CTlicniPodaci;
import zajednicko.model.STpol;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/termin")
public class TerminController {

    private final TerminService terminService;

    @GetMapping()
    public ResponseEntity<Termin> getInteresovanje() throws DatatypeConfigurationException {
        Termin t = new Termin();
        LocalDate localDate = LocalDate.of(2019, 4, 25);

        XMLGregorianCalendar xmlGregorianCalendar =
                DatatypeFactory.newInstance().newXMLGregorianCalendar("2024-10-10T05:22:33");

        CTlicniPodaci lp = new CTlicniPodaci();
        lp.setId(String.valueOf(UUID.randomUUID()));
        lp.setIme("marko");
        lp.setPrezime("petrovic");
        lp.setPol(STpol.МУШКО);
        t.setDatumVrijeme(xmlGregorianCalendar);
        t.setKorisnik(lp);
        t = terminService.addTermin(t);

        terminService.saveMetadata(t);
        System.out.println("Radi");
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Termin> getid(@PathVariable String id) throws DatatypeConfigurationException {

        Termin t = terminService.getTermin(id);
        System.out.println("Radi");
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Termin>> getQuery(@RequestParam String query) {
        List<Termin> t = terminService.findTermin(query);
        System.out.println("Radi");
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Termin>> getQuery() {
        List<Termin> t = terminService.getAll();
        return new ResponseEntity<>(t, HttpStatus.OK);
    }
}
