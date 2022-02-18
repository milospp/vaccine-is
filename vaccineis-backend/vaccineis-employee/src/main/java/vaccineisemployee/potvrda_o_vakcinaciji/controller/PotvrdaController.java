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

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/potvrda")
public class PotvrdaController {
    private final PotvrdaService potvrdaService;
    private final AuthenticationService authenticationService;

    @PreAuthorize("hasAuthority('SLUZBENIK')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<PotvrdaVakcinacije> createIzvjestaj(@RequestBody String potvrda) {
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

    @GetMapping(value = "/get-pdf/{uuid}")
    public ResponseEntity<?> getSertifikatiPdf(@PathVariable("uuid") String uuid) throws IOException {
        return potvrdaService.getPdf(uuid);
    }

    @GetMapping(value = "/get-html/{uuid}")
    public ResponseEntity<?> getSertifikatiHtml(@PathVariable("uuid") String uuid) throws IOException {
        return potvrdaService.getHtml(uuid);
    }
}
