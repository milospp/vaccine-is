package vaccineisportal.zahtev_sertifikata.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vaccineisportal.authentication.service.AuthenticationService;
import vaccineisportal.zahtev_sertifikata.model.ListaZahtjeva;
import vaccineisportal.zahtev_sertifikata.model.Zahtjev;
import vaccineisportal.zahtev_sertifikata.service.ZahtevSertifikataService;
import org.springframework.web.bind.annotation.*;
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;

import javax.annotation.security.PermitAll;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/digitalni-zeleni-sertifikat-zahtev")
public class ZahtevSertifikataController {

    private final ZahtevSertifikataService zahtevSertifikataService;
    private final AuthenticationService authenticationService;

//    @PreAuthorize("hasAnyAuthority('GRADJANIN')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Zahtjev> createInteresovanje(@RequestBody String zahtjev) {

        Zahtjev retVal = zahtevSertifikataService.create(zahtjev);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }


    @PermitAll
    @GetMapping(value = "/podneti-zahtevi", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ListaZahtjeva> getZahteviByStatusPodnet() {

        List<Zahtjev> zahtevi = zahtevSertifikataService.findZahteviByStatusPodnet();
        var retVal = new ListaZahtjeva();
        retVal.setZahtevi(zahtevi);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }


    @PermitAll
    @GetMapping(value = "/odbij-zahtev/{uuid}")
    public ResponseEntity<?> odbijZahtev(@PathVariable("uuid") String uuid) {

        zahtevSertifikataService.odbijZahtev(uuid);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PermitAll
    @GetMapping(value = "/get-pdf/{uuid}")
    public ResponseEntity<?> getInteresovanjePdf(@PathVariable("uuid") String uuid) throws IOException {
        return zahtevSertifikataService.getPdf(uuid); // id za dok
    }

    @PermitAll
    @GetMapping(value = "/get-html/{uuid}")
    public ResponseEntity<?> getInteresovanjeHtml(@PathVariable("uuid") String uuid) throws IOException {
        return zahtevSertifikataService.getHtml(uuid);
    }

    @PermitAll
    @GetMapping(value = "/moji-zahtjevi", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<DocDatas> getMojeZahteve() {
        Korisnik korisnik = authenticationService.getLoggedInUser();

        DocDatas zahtjevi = zahtevSertifikataService.getZahtjeviByUser(korisnik.getId());

        return new ResponseEntity<>(zahtjevi, HttpStatus.OK);
    }

    @PermitAll
    @GetMapping(value = "/korisnik-zahtjevi/{uuid}", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<DocDatas> getKorisnikZahteve(@PathVariable("uuid") String uuid) {

        DocDatas zahtjevi = zahtevSertifikataService.getZahtjeviByUser(uuid);

        return new ResponseEntity<>(zahtjevi, HttpStatus.OK);
    }
}
