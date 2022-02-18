package vaccineisemployee.digitalni_sertifikat.service;

import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface SertifikatService {
    ResponseEntity<?> getPdf(int id) throws IOException;

    ResponseEntity<?> getHtml(int id) throws IOException;
}
