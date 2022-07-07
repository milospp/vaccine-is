package vaccineisportal.potvrda_vakcinacije_bridge.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vaccineisportal.authentication.service.AuthenticationService;
import vaccineisportal.potvrda_vakcinacije_bridge.service.PotvrdaVakcinacijeService;
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.service.UserService;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/potvrda")
public class PotvrdaVakcinacijeController {
    private final AuthenticationService authenticationService;
    private final PotvrdaVakcinacijeService potvrdaVakcinacijeService;
    private final UserService userService;


    //    @PreAuthorize("hasRole('GRADJANIN')")
    @GetMapping(value = "/get-pdf/{uuid}")
    public ResponseEntity<?> getSertifikatPdf(@PathVariable("uuid") String uuid) throws IOException {
        return potvrdaVakcinacijeService.getPdf(uuid);
    }

    @GetMapping(value = "/get-html/{uuid}")
    public ResponseEntity<?> getSertifikatHtml(@PathVariable("uuid") String uuid) throws IOException {
        return potvrdaVakcinacijeService.getHtml(uuid);
    }

    @GetMapping(value = "/moje-potvrde", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<DocDatas> getMojiSertifikati() {
        Korisnik korisnik = authenticationService.getLoggedInUser();

        DocDatas potvrde = potvrdaVakcinacijeService.getPotvrdeByUser(korisnik.getId());

        return new ResponseEntity<>(potvrde, HttpStatus.OK);
    }
    @GetMapping(value = "/korisnik/{uuid}", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<DocDatas> getKorinsikSertifikati(@PathVariable("uuid") String uuid) {
        Korisnik korisnik = userService.findUserByUuid(uuid);

        DocDatas potvrde = potvrdaVakcinacijeService.getPotvrdeByUser(korisnik.getId());

        return new ResponseEntity<>(potvrde, HttpStatus.OK);
    }
}
