package vaccineisemployee.digitalni_sertifikat.service;

import org.springframework.http.ResponseEntity;
import vaccineisemployee.digitalni_sertifikat.model.ZeleniSertifikat;

import java.io.IOException;

public interface SertifikatService {
    ZeleniSertifikat generisiSertifikat(String uuidZahteva);

    ResponseEntity<?> getPdf(int id) throws IOException;

    ResponseEntity<?> getHtml(int id) throws IOException;
}
