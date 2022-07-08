package vaccineisemployee.izvestaj.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vaccineisemployee.authentication.service.AuthenticationService;
import vaccineisemployee.digitalni_sertifikat.model.ZeleniSertifikat;
import vaccineisemployee.izvestaj.model.Izvjestaj;
import vaccineisemployee.izvestaj.service.IzvestajService;
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/izvestaj")
public class IzvestajController {
    private final IzvestajService izvestajService;
    private final AuthenticationService authenticationService;

    @PreAuthorize("hasAuthority('SLUZBENIK')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Izvjestaj> createIzvjestaj(@RequestBody String izvjestaj) {
        Izvjestaj izvjestaj1 = izvestajService.create(izvjestaj);
        return new ResponseEntity<>(izvjestaj1, HttpStatus.OK);
    }

    @GetMapping(value = "/moji-izvjestaji", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<DocDatas> getMojiIzvjestaji() {
        Korisnik korisnik = authenticationService.getLoggedInUser();

        DocDatas sertifikati = izvestajService.getIzvjestajiByUser(korisnik.getId());

        return new ResponseEntity<>(sertifikati, HttpStatus.OK);
    }

    @GetMapping(value = "/get-pdf/{uuid}")
    public ResponseEntity<?> getIzvjestajPdf(@PathVariable("uuid") String uuid) throws IOException {
        return izvestajService.getPdf(uuid);
    }

    @GetMapping(value = "/get-html/{uuid}")
    public ResponseEntity<?> getIzvjestajHtml(@PathVariable("uuid") String uuid) throws IOException {
        return izvestajService.getHtml(uuid);
    }

    @GetMapping(value = "/napredna")
    public ResponseEntity<?> getNaprednaPretraga(@RequestParam("query") String query) {

        String xmlResult = izvestajService.naprednaPretraga(query);

        return new ResponseEntity(xmlResult, HttpStatus.OK);
    }
}
