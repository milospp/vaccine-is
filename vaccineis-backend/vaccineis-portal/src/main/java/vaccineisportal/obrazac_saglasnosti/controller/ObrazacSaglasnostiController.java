package vaccineisportal.obrazac_saglasnosti.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vaccineisportal.obrazac_saglasnosti.service.ObrazacSaglasnostiService;
import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/saglasnost")
public class ObrazacSaglasnostiController {

    private final ObrazacSaglasnostiService obrazacSaglasnostiService;

    //@PreAuthorize("hasRole('PACIJENT')")
    @GetMapping(value = "/get-pdf")
    public ResponseEntity<byte[]> getInteresovanjePdf() throws IOException {
        return obrazacSaglasnostiService.getPdf(2);
    }

    @GetMapping(value = "/get-html")
    public ResponseEntity<byte[]> getInteresovanjeHtml() throws IOException {
        return obrazacSaglasnostiService.getHtml(2);
    }
}
