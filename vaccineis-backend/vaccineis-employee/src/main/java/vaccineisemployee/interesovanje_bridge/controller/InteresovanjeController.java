package vaccineisemployee.interesovanje_bridge.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vaccineisemployee.authentication.service.AuthenticationService;
import vaccineisemployee.interesovanje_bridge.service.InteresovanjeService;
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/interesovanje")
public class InteresovanjeController {
    private final InteresovanjeService interesovanjeService;
    private final AuthenticationService authenticationService;

    //    @PreAuthorize("hasRole('GRADJANIN')")
    @GetMapping(value = "/get-pdf/{uuid}")
    public ResponseEntity<?> getInteresovanjePdf(@PathVariable("uuid") String uuid) throws IOException {
        return interesovanjeService.getPdf(uuid);
    }

    @GetMapping(value = "/get-html/{uuid}")
    public ResponseEntity<?> getInteresovanjeHtml(@PathVariable("uuid") String uuid) throws IOException {
        return interesovanjeService.getHtml(uuid);
    }

    @GetMapping(value = "/moja-interesovanja", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<DocDatas> getMojaInteresovanja() {
        Korisnik korisnik = authenticationService.getLoggedInUser();

        DocDatas sertifikati = interesovanjeService.getSertifikatiByUser(korisnik.getId());

        return new ResponseEntity<>(sertifikati, HttpStatus.OK);
    }
}
