package vaccineisportal.potvrda_vakcinacije_bridge.service;

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
public class PotvrdaVakcinacijeService {
    private RestTemplate restTemplate;

    public ResponseEntity<?> getPdf(String id) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        ResponseEntity<byte[]> response = restTemplate.exchange("http://localhost:8081/api/potvrda/get-pdf/" + id, HttpMethod.GET, request, byte[].class);
        return response;
    }

    public ResponseEntity<?> getHtml(String id) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        ResponseEntity<byte[]> response = restTemplate.exchange("http://localhost:8081/api/potvrda/get-html/" + id, HttpMethod.GET, request, byte[].class);
        return response;
    }

    public DocDatas getPotvrdeByUser(String uuid) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        try {
            ResponseEntity<DocDatas> response = restTemplate.exchange("http://localhost:8081/api/potvrda/korisnik/" + uuid, HttpMethod.GET, request, DocDatas.class);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
