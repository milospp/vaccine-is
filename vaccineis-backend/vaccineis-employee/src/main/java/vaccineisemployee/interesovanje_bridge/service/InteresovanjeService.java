package vaccineisemployee.interesovanje_bridge.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import zajednicko.model.docdatas.DocDatas;

import java.io.IOException;

@AllArgsConstructor
@Service
public class InteresovanjeService {
    private RestTemplate restTemplate;

    public ResponseEntity<?> getPdf(String id) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        ResponseEntity<byte[]> response = restTemplate.exchange("http://localhost:8081/api/interesovanje/get-pdf/" + id, HttpMethod.GET, request, byte[].class);
        return response;
    }

    public ResponseEntity<?> getHtml(String id) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        ResponseEntity<byte[]> response = restTemplate.exchange("http://localhost:8081/api/interesovanje/get-html/" + id, HttpMethod.GET, request, byte[].class);
        return response;
    }

    public DocDatas getSertifikatiByUser(String uuid) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        ResponseEntity<DocDatas> response = restTemplate.exchange("http://localhost:8081/api/interesovanje/moja-interesovanja", HttpMethod.GET, request, DocDatas.class);
        return response.getBody();
    }
}
