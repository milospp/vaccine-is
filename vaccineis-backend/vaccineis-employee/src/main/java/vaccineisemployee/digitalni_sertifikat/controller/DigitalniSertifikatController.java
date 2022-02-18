package vaccineisemployee.digitalni_sertifikat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import vaccineisemployee.authentication.dto.AuthenticationResponse.AuthenticationResponseDTO;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/digitalni-sertifikat")
public class DigitalniSertifikatController {

    private final RestTemplate restTemplate;

    @Autowired
    public DigitalniSertifikatController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

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

}
