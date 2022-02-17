package vaccineisportal.zahtev_sertifikata.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vaccineisportal.zahtev_sertifikata.service.ZahtevSertifikataService;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/zahtev-sertifikata")
public class ZahtevSertifikataController {

    private final ZahtevSertifikataService zahtevSertifikataService;

    //@PreAuthorize("hasRole('PACIJENT')")
    @GetMapping(value = "/get-pdf")
    public ResponseEntity<byte[]> getInteresovanjePdf() throws IOException {
        return zahtevSertifikataService.getPdf(2); // id za dok
    }

    @GetMapping(value = "/get-html")
    public ResponseEntity<byte[]> getInteresovanjeHtml() throws IOException {
        return zahtevSertifikataService.getHtml(2);
    }
}
