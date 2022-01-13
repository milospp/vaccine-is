package vaccineisbackend.controller;

import lombok.AllArgsConstructor;
import org.exist.http.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;
import vaccineisbackend.service.XMLService;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping(value = "api/xml", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
public class XMLController {

    private final XMLService xmlService;

    @GetMapping(value = "/izvestaj", consumes = "*/*")
    public ResponseEntity<String> getIzvestaj() throws XMLDBException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {

        return new ResponseEntity<>(xmlService.findIzvestaji().toString(), HttpStatus.OK);
    }

    @PostMapping(value = "/izvestaj")
    public ResponseEntity<Void> addIzvestaj(@RequestBody String izvjestaj) throws XMLDBException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        xmlService.saveIzvestajFileFromString(izvjestaj);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping(value="/rdfUpis")
    public ResponseEntity<Void> upisRdfBaza() throws IOException {
        xmlService.writeToRdfDatabase();

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/digitalniSertifikat")
    public ResponseEntity<String> getDDigitalniSertifikat() throws XMLDBException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {

        return new ResponseEntity<>(xmlService.findDigitlniSertifikat().toString(), HttpStatus.OK);
    }

    @PostMapping("/digitalniSertifikat")
    public ResponseEntity<Void> addDDigitalniSertifikat(@RequestBody String digitalniSertifikat) throws XMLDBException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        xmlService.saveDigitalniSertifikatFileFromString(digitalniSertifikat);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/interesovanje")
    public ResponseEntity<Void> addInteresovanje(@RequestBody String interesovanje) throws XMLDBException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        xmlService.saveInteresovanjeFileFromString(interesovanje);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/obrazacSaglasnosti")
    public ResponseEntity<Void> addObrazacSaglasnosti(@RequestBody String obrazacSaglasnosti) throws XMLDBException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        xmlService.saveObrazacSaglasnostiFileFromString(obrazacSaglasnosti);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/potvrdaOVakcinaciji")
    public ResponseEntity<Void> addPotvrdaOVakcinaciji(@RequestBody String potvrdaVakcinacije) throws XMLDBException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        xmlService.savePotvrdaOVakcinacijiFileFromString(potvrdaVakcinacije);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/zahtevSertifikata")
    public ResponseEntity<Void> addZahtevSertifikata(@RequestBody String zahtjev) throws XMLDBException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        xmlService.saveZahtevSertifikataFileFromString(zahtjev);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
