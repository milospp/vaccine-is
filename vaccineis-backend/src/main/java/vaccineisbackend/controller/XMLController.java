package vaccineisbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.xmldb.api.base.XMLDBException;
import vaccineisbackend.model.DocumentEntity;
import vaccineisbackend.service.XMLService;

@RestController
@RequestMapping(value = "api/xml", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class XMLController {

    private XMLService xmlService;

    public XMLController(XMLService xmlService) {
        super();
        this.xmlService = xmlService;
    }

    @PostMapping(value = "/izvestaj")
    public ResponseEntity<Void> addIzvestaj(@RequestBody DocumentEntity doc) throws XMLDBException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println(doc.getText());
        xmlService.saveIzvestajFileFromString(doc.getText());

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/digitalniSertifikat")
    public ResponseEntity<Void> addDDigitalniSertifikat(@RequestBody DocumentEntity doc) throws XMLDBException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        xmlService.saveDigitalniSertifikatFileFromString(doc.getText());

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/interesovanje")
    public ResponseEntity<Void> addInteresovanje(@RequestBody DocumentEntity doc) throws XMLDBException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        xmlService.saveInteresovanjeFileFromString(doc.getText());

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/obrazacSaglasnosti")
    public ResponseEntity<Void> addObrazacSaglasnosti(@RequestBody DocumentEntity doc) throws XMLDBException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        xmlService.saveObrazacSaglasnostiFileFromString(doc.getText());

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/potvrdaOVakcinaciji")
    public ResponseEntity<Void> addPotvrdaOVakcinaciji(@RequestBody DocumentEntity doc) throws XMLDBException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        xmlService.savePotvrdaOVakcinacijiFileFromString(doc.getText());

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/zahtevSertifikata")
    public ResponseEntity<Void> addZahtevSertifikata(@RequestBody DocumentEntity doc) throws XMLDBException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        xmlService.saveZahtevSertifikataFileFromString(doc.getText());

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
