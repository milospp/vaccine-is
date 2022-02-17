package vaccineisportal.interesovanje.service;

import org.springframework.http.ResponseEntity;

import vaccineisportal.interesovanje.model.Interesovanje;

import java.io.IOException;

public interface InteresovanjeService {

    Interesovanje create(String xmlString);

    ResponseEntity<byte[]> getPdf(int id) throws IOException;

    ResponseEntity<byte[]> getHtml(int id) throws IOException;
}