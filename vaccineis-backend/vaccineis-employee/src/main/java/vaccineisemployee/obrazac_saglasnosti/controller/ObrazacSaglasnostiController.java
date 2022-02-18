package vaccineisemployee.obrazac_saglasnosti.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@RestController
@RequestMapping("/api/obrasci-saglasnosti")
public class ObrazacSaglasnostiController {

    private final RestTemplate restTemplate;

    @GetMapping(value = "/podneti")
    public ResponseEntity<String> getPodnetiObrasciSaglasnosti() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8082/api/obrasci-saglasnosti-bridge/podneti", HttpMethod.GET, request, String.class);

        System.out.println(response.getBody());

        return response;
    }
}
