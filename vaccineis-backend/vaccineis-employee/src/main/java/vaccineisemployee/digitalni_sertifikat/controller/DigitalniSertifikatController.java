package vaccineisemployee.digitalni_sertifikat.controller;

import lombok.AllArgsConstructor;
import org.apache.jena.base.Sys;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import vaccineisemployee.authentication.dto.AuthenticationResponse.AuthenticationResponseDTO;
import vaccineisemployee.digitalni_sertifikat.dto.SertifikatResponseDTO;
import vaccineisemployee.digitalni_sertifikat.service.SertifikatService;

import java.io.IOException;
import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("/api/digitalni-sertifikat")
public class DigitalniSertifikatController {
    private final SertifikatService sertifikatService;
    private final RestTemplate restTemplate;

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

    @GetMapping(value = "/get-pdf")
    public ResponseEntity<?> getSertifikatPdf() throws IOException {
        return sertifikatService.getPdf(2);
    }

    @GetMapping(value = "/get-html")
    public ResponseEntity<?> getSertifikatHtml() throws IOException {
        return sertifikatService.getHtml(2);
    }

    @PostMapping(value = "/response-request", consumes = MediaType.APPLICATION_XML_VALUE)
    public void responseToRequest(@RequestBody SertifikatResponseDTO responseDTO) {
        System.out.println(responseDTO.getDecision());
        System.out.println(responseDTO.getMessage());
        System.out.println(responseDTO.getRequestId());
        // accept / decline request
    }
}
