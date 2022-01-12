package vaccineisbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import vaccineisbackend.service.XMLService;

@RestController
@RequestMapping(value = "api/xml", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class XMLController {

    private XMLService xmlService;

    public XMLController(XMLService xmlService) {
        super();
        this.xmlService = xmlService;
    }
//
//    @PostMapping("/xonomy")
//    public ResponseEntity<Void> addDocument(@RequestBody Document doc) {
//        xmlService.saveFileFromString(doc.getTextContent());
//
//        return new ResponseEntity<Void>(HttpStatus.OK);
//    }

}
