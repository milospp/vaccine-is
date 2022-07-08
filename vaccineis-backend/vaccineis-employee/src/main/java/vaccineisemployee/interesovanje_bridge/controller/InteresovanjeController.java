package vaccineisemployee.interesovanje_bridge.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import vaccineisemployee.authentication.service.AuthenticationService;
import vaccineisemployee.interesovanje_bridge.service.InteresovanjeService;
import zajednicko.exception.BadRequestException;
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;

import javax.annotation.security.PermitAll;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping("/api/interesovanje")
public class InteresovanjeController {

    private final InteresovanjeService interesovanjeService;
    private final AuthenticationService authenticationService;

    //    @PreAuthorize("hasRole('GRADJANIN')")
    @GetMapping(value = "/get-pdf/{uuid}")
    public ResponseEntity<?> getInteresovanjePdf(@PathVariable("uuid") String uuid) throws IOException {
        return interesovanjeService.getPdf(uuid);
    }

    @GetMapping(value = "/get-html/{uuid}")
    public ResponseEntity<?> getInteresovanjeHtml(@PathVariable("uuid") String uuid) throws IOException {
        return interesovanjeService.getHtml(uuid);
    }

    @GetMapping(value = "/moja-interesovanja", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<DocDatas> getMojaInteresovanja() {
        Korisnik korisnik = authenticationService.getLoggedInUser();

        DocDatas sertifikati = interesovanjeService.getSertifikatiByUser(korisnik.getId());

        return new ResponseEntity<>(sertifikati, HttpStatus.OK);
    }

    @GetMapping(value = "/broj-primljenih", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getMojaInteresovanja(@RequestParam String dateStart, @RequestParam String dateEnd) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/api/interesovanje/broj-primljenih?dateStart=" + dateStart + "&dateEnd=" + dateEnd;
        System.out.println(url);

        ResponseEntity<Integer> entity = restTemplate.getForEntity(url, Integer.class);
        if (entity.getBody() == null)
            throw new BadRequestException("Body is null");

        Integer retVal = entity.getBody();
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }
}
