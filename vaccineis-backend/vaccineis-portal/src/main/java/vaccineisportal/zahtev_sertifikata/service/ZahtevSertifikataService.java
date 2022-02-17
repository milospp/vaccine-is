package vaccineisportal.zahtev_sertifikata.service;

import vaccineisportal.zahtev_sertifikata.model.Zahtjev;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ZahtevSertifikataService {
    Zahtjev save(Zahtjev zahtjev);

    public ResponseEntity<byte[]> getPdf(int id) throws IOException;

    public ResponseEntity<byte[]> getHtml(int id) throws IOException;
}
