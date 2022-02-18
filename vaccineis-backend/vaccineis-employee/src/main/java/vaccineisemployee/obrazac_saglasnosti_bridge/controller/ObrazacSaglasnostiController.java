package vaccineisemployee.obrazac_saglasnosti_bridge.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import vaccineisemployee.authentication.service.AuthenticationService;
import vaccineisemployee.obrazac_saglasnosti_bridge.service.ObrazacSaglasnostiService;
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/saglasnost")
public class ObrazacSaglasnostiController {
    private final ObrazacSaglasnostiService saglasnostiService;
    private final AuthenticationService authenticationService;

    //    @PreAuthorize("hasRole('GRADJANIN')")
    @GetMapping(value = "/get-pdf/{uuid}")
    public ResponseEntity<?> getSaglasnostiPdf(@PathVariable("uuid") String uuid) throws IOException {
        return saglasnostiService.getPdf(uuid);
    }

    @GetMapping(value = "/get-html/{uuid}")
    public ResponseEntity<?> getSaglasnostiHtml(@PathVariable("uuid") String uuid) throws IOException {
        return saglasnostiService.getHtml(uuid);
    }

    @GetMapping(value = "/moje-saglasnosti", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<DocDatas> getMojeSaglasnosti() {
        Korisnik korisnik = authenticationService.getLoggedInUser();

        DocDatas saglasnosti = saglasnostiService.getSaglasnostiByUser(korisnik.getId());

        return new ResponseEntity<>(saglasnosti, HttpStatus.OK);
    }

    @GetMapping(value = "/podnete-saglasnosti")
    public ResponseEntity<String> getPodnetiObrasciSaglasnosti() {
        return saglasnostiService.getPodneteObrasce();
    }
}
