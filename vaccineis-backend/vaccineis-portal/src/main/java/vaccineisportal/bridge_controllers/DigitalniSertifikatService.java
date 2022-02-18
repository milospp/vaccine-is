package vaccineisportal.bridge_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class DigitalniSertifikatService {
    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<?> getPdf(int id) throws IOException {
        return getDocument("pdf");
    }

    public ResponseEntity<?> getHtml(int id) throws IOException {
        return getDocument("html");
    }

    private ResponseEntity<?> getDocument(String type) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        ResponseEntity<byte[]> response = restTemplate.exchange("http://localhost:8081/api/digitalni-sertifikat/get-" + type, HttpMethod.GET, request, byte[].class);
        return response;
    }
}
