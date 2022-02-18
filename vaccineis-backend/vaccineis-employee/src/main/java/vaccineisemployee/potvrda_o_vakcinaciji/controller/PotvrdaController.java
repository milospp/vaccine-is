package vaccineisemployee.potvrda_o_vakcinaciji.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vaccineisemployee.potvrda_o_vakcinaciji.model.PotvrdaVakcinacije;
import vaccineisemployee.potvrda_o_vakcinaciji.service.PotvrdaService;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/potvrda")
public class PotvrdaController {
    private final PotvrdaService potvrdaService;

    @PreAuthorize("hasAuthority('SLUZBENIK')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<PotvrdaVakcinacije> createIzvjestaj(@RequestBody String potvrda) {
        PotvrdaVakcinacije potvrdaVakcinacije = potvrdaService.create(potvrda);
        return new ResponseEntity<>(potvrdaVakcinacije, HttpStatus.OK);
    }

    @GetMapping(value = "/get-pdf")
    public ResponseEntity<?> getIzvjestajPdf() throws Exception {
        return potvrdaService.getPdf(2);
    }

    @GetMapping(value = "/get-html")
    public ResponseEntity<?> getIzvjestajHtml() throws IOException {
        return potvrdaService.getHtml(2);
    }
}
