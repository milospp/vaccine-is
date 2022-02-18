package vaccineisportal.termin.controller;

import lombok.AllArgsConstructor;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.web.bind.annotation.*;
import vaccineisportal.termin.model.Termin;
import vaccineisportal.termin.service.TerminService;
import zajednicko.model.CTlicniPodaci;
import zajednicko.model.STpol;
import zajednicko.repository.CRUDRDFRepository;
import zajednicko.service.MailService;

import javax.annotation.security.PermitAll;
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

    @PostMapping("/pronadji-termine")
    public ResponseEntity<String> pronadjiTermine() {
        terminService.terminZaInteresovanje();
        return new ResponseEntity<>("obradjeno", HttpStatus.OK);
    }

    @GetMapping(value = "/novi-termin", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Termin> noviTerminBezSaglasnosti() {
        Termin termin = terminService.dobaviTerminBezSaglasnosti();
        return new ResponseEntity<>(termin, HttpStatus.OK);
    }

//    @GetMapping("/test")
//    public ResponseEntity<String> testTriplet() {
//        crudrdfRepository.uploadTriplet("test", "korisnik/1", "cekaTermin", "2022-12-29");
//        crudrdfRepository.uploadTriplet("test", "korisnik/1", "ime", "imenko");
//        crudrdfRepository.uploadTriplet("test", "korisnik/1", "prezime", "prezic");
//        crudrdfRepository.uploadTriplet("test", "korisnik/1", "id", "455");
//        crudrdfRepository.uploadTriplet("test", "korisnik/1", "datum", "2022-12-28");
//        crudrdfRepository.uploadTriplet("test", "korisnik/1", "boolean", "true");
//
//        crudrdfRepository.uploadTriplet("test", "korisnik/2", "cekaTermin", "2022-12-29");
//        crudrdfRepository.uploadTriplet("test", "korisnik/2", "ime", "imenko");
//        crudrdfRepository.uploadTriplet("test", "korisnik/2", "prezime", "preric");
//        crudrdfRepository.uploadTriplet("test", "korisnik/2", "id", "455");
//        crudrdfRepository.uploadTriplet("test", "korisnik/2", "datum", "2022-12-28");
//        crudrdfRepository.uploadTriplet("test", "korisnik/2", "boolean", "true");
//
//        ResultSet rs = crudrdfRepository.findByObject("test", "2022-12-29");
//        rs.getResultVars();
//        String res = "";
//        for (ResultSet it = rs; it.hasNext(); ) {
//            QuerySolution s = it.next();
//            res += s.get("o") + " - " + s.get("s") + "\n";
//            System.out.println("s = " + s);
//        }
//
//        return new ResponseEntity<>("Triplet se resio \n" + res, HttpStatus.OK);
//    }
}
