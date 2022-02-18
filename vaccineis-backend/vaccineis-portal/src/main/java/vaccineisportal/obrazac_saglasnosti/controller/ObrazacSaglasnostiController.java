package vaccineisportal.obrazac_saglasnosti.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vaccineisportal.obrazac_saglasnosti.model.Saglasnost;
import vaccineisportal.obrazac_saglasnosti.service.ObrazacSaglasnostiService;
import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/saglasnost")
public class ObrazacSaglasnostiController {

    private final ObrazacSaglasnostiService obrazacSaglasnostiService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Saglasnost> createObrazacSaglasnosti(@RequestBody String obrazacSaglasnosti) {

        Saglasnost retVal = obrazacSaglasnostiService.create(obrazacSaglasnosti);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

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
