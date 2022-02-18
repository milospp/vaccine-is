package vaccineisportal.zahtev_sertifikata.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vaccineisportal.zahtev_sertifikata.model.Zahtjev;
import vaccineisportal.zahtev_sertifikata.service.ZahtevSertifikataService;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/digitalni-zeleni-sertifikat-zahtev")
public class ZahtevSertifikataController {

    private final ZahtevSertifikataService zahtevSertifikataService;

    @PreAuthorize("hasAnyAuthority('GRADJANIN')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Zahtjev> createInteresovanje(@RequestBody String zahtjev) {

        Zahtjev retVal = zahtevSertifikataService.create(zahtjev);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SLUZBENI')")
    @GetMapping(value = "/podneti-zahtevi")
    public ResponseEntity<List<Zahtjev>> getZahteviByStatusPodnet() {

        for (var el : zahtevSertifikataService.findZahteviByStatusPodnet())
            System.out.println(el.getId());

        return new ResponseEntity<>(null, HttpStatus.OK);
    }


//    @PreAuthorize("hasAnyAuthority('GRADJANIN')")
//    @GetMapping(value = "/moji-zahtevi")
//    public ResponseEntity<List<Zahtjev>> getZahteviByStatusPodnet() {
//
////        zahtevSertifikataService
//
//        return new ResponseEntity<>(null, HttpStatus.OK);
//    }


    @GetMapping(value = "/get-pdf")
    public ResponseEntity<byte[]> getInteresovanjePdf() throws IOException {
        return zahtevSertifikataService.getPdf(2); // id za dok
    }

    @GetMapping(value = "/get-html")
    public ResponseEntity<byte[]> getInteresovanjeHtml() throws IOException {
        return zahtevSertifikataService.getHtml(2);
    }
}
