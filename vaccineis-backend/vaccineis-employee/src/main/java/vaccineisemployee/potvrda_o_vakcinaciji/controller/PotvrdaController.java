package vaccineisemployee.potvrda_o_vakcinaciji.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vaccineisemployee.authentication.service.AuthenticationService;
import vaccineisemployee.potvrda_o_vakcinaciji.model.PotvrdaVakcinacije;
import vaccineisemployee.potvrda_o_vakcinaciji.service.PotvrdaService;
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.service.UserService;

import javax.annotation.security.PermitAll;
import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/potvrda")
public class PotvrdaController {
    private final PotvrdaService potvrdaService;
    private final AuthenticationService authenticationService;
    private final UserService userService;

//    @PreAuthorize("hasAuthority('SLUZBENIK')")
    @PermitAll
    @PostMapping(value = "", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<PotvrdaVakcinacije> createIzvjestaj(@RequestBody String potvrda) throws IOException {
        PotvrdaVakcinacije potvrdaVakcinacije = potvrdaService.create(potvrda);
        return new ResponseEntity<>(potvrdaVakcinacije, HttpStatus.OK);
    }

    @GetMapping(value = "/moje-potvrde", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<DocDatas> getMojiSertifikati() {
        Korisnik korisnik = authenticationService.getLoggedInUser();

        DocDatas sertifikati = potvrdaService.getPotvrdeByUser(korisnik.getId());

        return new ResponseEntity<>(sertifikati, HttpStatus.OK);
    }

    @PermitAll
    @GetMapping(value = "/get-pdf/{uuid}")
    public ResponseEntity<?> getSertifikatiPdf(@PathVariable("uuid") String uuid) throws IOException {
        return potvrdaService.getPdf(uuid);
    }

    @PermitAll
    @GetMapping(value = "/get-html/{uuid}")
    public ResponseEntity<?> getSertifikatiHtml(@PathVariable("uuid") String uuid) throws IOException {
        return potvrdaService.getHtml(uuid);
    }

    @PermitAll
    @GetMapping(value = "/get-rdf/{uuid}")
    public ResponseEntity<?> getInteresovanjeRdf(@PathVariable("uuid") String uuid) {
        String xml = potvrdaService.getRdfXml(uuid);
        return new ResponseEntity(xml, HttpStatus.OK);
    }


    @PermitAll
    @GetMapping(value = "/get-json/{uuid}")
    public ResponseEntity<?> getInteresovanjeJson(@PathVariable("uuid") String uuid) {
        String json = potvrdaService.getRdfJson(uuid);
        return new ResponseEntity(json, HttpStatus.OK);
    }


    @PermitAll
    @GetMapping(value = "/korisnik/{uuid}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<DocDatas> getKorisnikInteresovanja (@PathVariable("uuid") String uuid) {
        DocDatas interesovanjes = potvrdaService.getPotvrdeByUser(uuid);

        return new ResponseEntity<>(interesovanjes, HttpStatus.OK);
    }
}
