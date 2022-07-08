package vaccineisemployee.digitalni_sertifikat.controller;

import lombok.AllArgsConstructor;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import vaccineisemployee.authentication.dto.AuthenticationResponse.AuthenticationResponseDTO;
import vaccineisemployee.authentication.service.AuthenticationService;
import vaccineisemployee.digitalni_sertifikat.dto.SertifikatResponseDTO;
import vaccineisemployee.digitalni_sertifikat.model.ZeleniSertifikat;
import vaccineisemployee.digitalni_sertifikat.service.SertifikatService;
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;

import javax.annotation.security.PermitAll;
import java.io.IOException;
import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("/api/digitalni-sertifikat")
public class DigitalniSertifikatController {
    private final SertifikatService sertifikatService;
    private final RestTemplate restTemplate;
    private final AuthenticationService authenticationService;

    @GetMapping(value = "/zahtevi", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ArrayList> getZahteviWithStatusPredat() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/xml");
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        var jwt = restTemplate.exchange("http://localhost:8082/api/authentication/authority", HttpMethod.GET, httpEntity, AuthenticationResponseDTO.class).getBody().getJwt();

        headers = new HttpHeaders();
        headers.add("Content-Type", "application/xml");
        headers.add("Authorization", "Bearer " + jwt);
        httpEntity = new HttpEntity<>(headers);

        var zahtevi = restTemplate.exchange("http://localhost:8082/api/digitalni-zeleni-sertifikat-zahtev/podneti-zahtevi", HttpMethod.GET, httpEntity, ArrayList.class).getBody();
        return new ResponseEntity<ArrayList>(zahtevi, HttpStatus.OK);
    }

    @PostMapping(value = "/response-request", consumes = MediaType.APPLICATION_XML_VALUE)
    public void responseToRequest(@RequestBody SertifikatResponseDTO responseDTO) {
        System.out.println(responseDTO.getDecision());
        System.out.println(responseDTO.getMessage());
        System.out.println(responseDTO.getRequestId());
        // accept / decline request
    }

    @GetMapping(value = "/get-one/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZeleniSertifikat> getOneById(@PathVariable("id") String id) {
        return new ResponseEntity<>(sertifikatService.findOne(id), HttpStatus.OK);
    }

    @GetMapping(value = "/moji-sertifikati", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<DocDatas> getMojiSertifikati() {
        Korisnik korisnik = authenticationService.getLoggedInUser();

        DocDatas sertifikati = sertifikatService.getSertifikatiByUser(korisnik.getId());

        return new ResponseEntity<>(sertifikati, HttpStatus.OK);
    }

    @GetMapping(value = "/get-pdf/{uuid}")
    public ResponseEntity<?> getSertifikatiPdf(@PathVariable("uuid") String uuid) throws IOException {
        return sertifikatService.getPdf(uuid);
    }

    @GetMapping(value = "/get-html/{uuid}")
    public ResponseEntity<?> getSertifikatiHtml(@PathVariable("uuid") String uuid) throws IOException {
        return sertifikatService.getHtml(uuid);
    }

    @PermitAll
    @GetMapping(value = "/get-rdf/{uuid}")
    public ResponseEntity<?> getInteresovanjeRdf(@PathVariable("uuid") String uuid) {
        String xml = sertifikatService.getRdfXml(uuid);
        return new ResponseEntity(xml, HttpStatus.OK);
    }


    @PermitAll
    @GetMapping(value = "/get-json/{uuid}")
    public ResponseEntity<?> getInteresovanjeJson(@PathVariable("uuid") String uuid) {
        String json = sertifikatService.getRdfJson(uuid);
        return new ResponseEntity(json, HttpStatus.OK);
    }


    @PermitAll
    @GetMapping(value = "/korisnik/{uuid}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<DocDatas> getKorisnikInteresovanja (@PathVariable("uuid") String uuid) {
        DocDatas interesovanjes = sertifikatService.getSertifikatiByUser(uuid);

        return new ResponseEntity<>(interesovanjes, HttpStatus.OK);
    }

}
