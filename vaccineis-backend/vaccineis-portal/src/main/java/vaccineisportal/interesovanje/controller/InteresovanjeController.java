package vaccineisportal.interesovanje.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import vaccineisportal.interesovanje.model.Interesovanje;
import vaccineisportal.interesovanje.service.InteresovanjeService;

import org.springframework.web.bind.annotation.GetMapping;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/interesovanje")
public class InteresovanjeController {

    private final InteresovanjeService interesovanjeService;

    @PreAuthorize("hasRole('ROLE_GRADJANIN')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Interesovanje> createInteresovanje(@RequestBody String interesovanje) {

        Interesovanje retVal = interesovanjeService.create(interesovanje);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }


//    @PreAuthorize("hasRole('GRADJANIN')")
    @GetMapping(value = "/get-pdf")
    public ResponseEntity<?> getInteresovanjePdf() throws IOException, ParserConfigurationException, SAXException {
        return interesovanjeService.getPdf(2); // id za dok
    }

//    @PreAuthorize("hasRole('GRADJANIN')")
    @GetMapping(value = "/get-html")
    public ResponseEntity<?> getInteresovanjeHtml() throws IOException, ParserConfigurationException, SAXException {
        return interesovanjeService.getHtml(2);
    }
}
