package vaccineisportal.obrazac_saglasnosti.service;

import org.springframework.http.ResponseEntity;
import vaccineisportal.obrazac_saglasnosti.model.Saglasnost;

import java.io.IOException;

public interface ObrazacSaglasnostiService {

    Saglasnost create(String xmlString);

    ResponseEntity<byte[]> getPdf(int id) throws IOException;

    ResponseEntity<byte[]> getHtml(int id) throws IOException;

    static ResponseEntity<byte[]> getDocument(String type) throws IOException {
        return null;
    }
}
