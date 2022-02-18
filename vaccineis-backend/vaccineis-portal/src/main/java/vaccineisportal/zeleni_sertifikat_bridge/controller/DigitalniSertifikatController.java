package vaccineisportal.zeleni_sertifikat_bridge.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vaccineisportal.authentication.service.AuthenticationService;
import vaccineisportal.zeleni_sertifikat_bridge.service.DigitalniSertifikatService;
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/digitalni-sertifikat")
public class DigitalniSertifikatController {
    private final DigitalniSertifikatService digitalniSertifikatService;
    private final AuthenticationService authenticationService;

    //    @PreAuthorize("hasRole('GRADJANIN')")
    @GetMapping(value = "/get-pdf/{uuid}")
    public ResponseEntity<?> getSertifikatPdf(@PathVariable("uuid") String uuid) throws IOException {
        return digitalniSertifikatService.getPdf(uuid);
    }

    @GetMapping(value = "/get-html/{uuid}")
    public ResponseEntity<?> getSertifikatHtml(@PathVariable("uuid") String uuid) throws IOException {
        return digitalniSertifikatService.getHtml(uuid);
    }

    @GetMapping(value = "/moji-sertifikati", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<DocDatas> getMojiSertifikati() {
        Korisnik korisnik = authenticationService.getLoggedInUser();

        DocDatas sertifikati = digitalniSertifikatService.getSertifikatiByUser(korisnik.getId());

        return new ResponseEntity<>(sertifikati, HttpStatus.OK);
    }
}
