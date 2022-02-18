package vaccineisemployee.izvestaj.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vaccineisemployee.izvestaj.model.Izvjestaj;
import vaccineisemployee.izvestaj.service.IzvestajService;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/izvestaj")
public class IzvestajController {
    private final IzvestajService izvestajService;

    @PreAuthorize("hasAuthority('SLUZBENIK')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Izvjestaj> createIzvjestaj(@RequestBody String izvjestaj) {
        Izvjestaj izvjestaj1 = izvestajService.create(izvjestaj);
        return new ResponseEntity<>(izvjestaj1, HttpStatus.OK);
    }

    @GetMapping(value = "/get-pdf")
    public ResponseEntity<?> getIzvjestajPdf() throws Exception {
        return izvestajService.getPdf(2);
    }

    @GetMapping(value = "/get-html")
    public ResponseEntity<?> getIzvjestajHtml() throws IOException {
        return izvestajService.getHtml(2);
    }
}
