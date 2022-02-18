package vaccineisportal.obrazac_saglasnosti.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import vaccineisportal.authentication.service.AuthenticationService;
import vaccineisportal.obrazac_saglasnosti.model.Saglasnost;
import vaccineisportal.obrazac_saglasnosti.service.ObrazacSaglasnostiService;
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/saglasnost")
public class ObrazacSaglasnostiController {

    private final ObrazacSaglasnostiService obrazacSaglasnostiService;
    private final AuthenticationService authenticationService;

    @PreAuthorize("hasAnyAuthority('GRADJANIN')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Saglasnost> createObrazacSaglasnosti(@RequestBody String obrazacSaglasnosti) {

        Saglasnost retVal = obrazacSaglasnostiService.create(obrazacSaglasnosti);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('PACIJENT')")
    @GetMapping(value = "/get-pdf/{uuid}")
    public ResponseEntity<?> getInteresovanjePdf(@PathVariable("uuid") String uuid) throws IOException {
        return obrazacSaglasnostiService.getPdf(uuid);
    }

    @GetMapping(value = "/get-html/{uuid}")
    public ResponseEntity<?> getInteresovanjeHtml(@PathVariable("uuid") String uuid) throws IOException {
        return obrazacSaglasnostiService.getHtml(uuid);
    }

    @GetMapping(value = "/moji-obrasci", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<DocDatas> getMojeSaglasnosti() {
        Korisnik korisnik = authenticationService.getLoggedInUser();

        DocDatas interesovanjes = obrazacSaglasnostiService.getObrasciByUser(korisnik.getId());

        return new ResponseEntity<>(interesovanjes, HttpStatus.OK);
    }
}
