package vaccineisemployee.zahtev_sertifikata_bridge.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vaccineisemployee.authentication.service.AuthenticationService;
import vaccineisemployee.zahtev_sertifikata_bridge.service.ZahtevSertifikataService;
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/digitalni-zeleni-sertifikat-zahtev")
public class ZahtevSertifikataController {
    private final ZahtevSertifikataService zahtevSertifikataService;
    private final AuthenticationService authenticationService;

    //    @PreAuthorize("hasRole('GRADJANIN')")
    @GetMapping(value = "/get-pdf/{uuid}")
    public ResponseEntity<?> getSaglasnostiPdf(@PathVariable("uuid") String uuid) throws IOException {
        return zahtevSertifikataService.getPdf(uuid);
    }

    @GetMapping(value = "/get-html/{uuid}")
    public ResponseEntity<?> getSaglasnostiHtml(@PathVariable("uuid") String uuid) throws IOException {
        return zahtevSertifikataService.getHtml(uuid);
    }

    @GetMapping(value = "/moji-zahtjevi", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<DocDatas> getMojiZahtjevi() {
        Korisnik korisnik = authenticationService.getLoggedInUser();

        DocDatas saglasnosti = zahtevSertifikataService.getZahtjeviByUser(korisnik.getId());

        return new ResponseEntity<>(saglasnosti, HttpStatus.OK);
    }
}
