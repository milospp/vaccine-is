package vaccineisemployee.termin.controller;

import lombok.AllArgsConstructor;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vaccineisemployee.termin.model.Termin;
import vaccineisemployee.termin.service.TerminService;
import zajednicko.model.CTlicniPodaci;
import zajednicko.model.STpol;
import zajednicko.repository.CRUDRDFRepository;
import zajednicko.service.MailService;

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
    private final CRUDRDFRepository crudrdfRepository;

    @GetMapping()
    public ResponseEntity<Termin> getInteresovanje() throws DatatypeConfigurationException {
//        Termin t = new Termin();
//        LocalDate localDate = LocalDate.of(2019, 4, 25);
//
//        XMLGregorianCalendar xmlGregorianCalendar =
//                DatatypeFactory.newInstance().newXMLGregorianCalendar("2024-10-10T05:22:33");
//
        CTlicniPodaci lp = new CTlicniPodaci();
        lp.setId(String.valueOf(UUID.randomUUID()));
        lp.setIme("marko");
        lp.setPrezime("petrovic");
        lp.setPol(STpol.МУШКО);
        lp.setDatumRodjenja(DatatypeFactory.newInstance().newXMLGregorianCalendar("2024-10-10T05:22:33"));

        Termin t = terminService.zakaziPrviSlobodan(lp);
//        System.out.println("Radi");
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

    @GetMapping("/test")
    public ResponseEntity<String> testTriplet() {
        crudrdfRepository.uploadTriplet("test", "korisnik/1", "cekaTermin", "2022-12-29");
        crudrdfRepository.uploadTriplet("test", "korisnik/1", "ime", "imenko");
        crudrdfRepository.uploadTriplet("test", "korisnik/1", "prezime", "prezic");
        crudrdfRepository.uploadTriplet("test", "korisnik/1", "id", "455");
        crudrdfRepository.uploadTriplet("test", "korisnik/1", "datum", "2022-12-28");
        crudrdfRepository.uploadTriplet("test", "korisnik/1", "boolean", "true");

        crudrdfRepository.uploadTriplet("test", "korisnik/2", "cekaTermin", "2022-12-29");
        crudrdfRepository.uploadTriplet("test", "korisnik/2", "ime", "imenko");
        crudrdfRepository.uploadTriplet("test", "korisnik/2", "prezime", "preric");
        crudrdfRepository.uploadTriplet("test", "korisnik/2", "id", "455");
        crudrdfRepository.uploadTriplet("test", "korisnik/2", "datum", "2022-12-28");
        crudrdfRepository.uploadTriplet("test", "korisnik/2", "boolean", "true");

        ResultSet rs = crudrdfRepository.findByObject("test", "2022-12-29");
        rs.getResultVars();
        String res = "";
        for (ResultSet it = rs; it.hasNext(); ) {
            QuerySolution s = it.next();
            res += s.get("o") + " - " + s.get("s") + "\n";
            System.out.println("s = " + s);
        }



        return new ResponseEntity<>("Triplet se resio \n" + res, HttpStatus.OK);
    }
}
