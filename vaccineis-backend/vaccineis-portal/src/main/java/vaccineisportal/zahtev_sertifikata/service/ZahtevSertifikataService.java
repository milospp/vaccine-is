package vaccineisportal.zahtev_sertifikata.service;

import vaccineisportal.zahtev_sertifikata.model.Zahtjev;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ZahtevSertifikataService {

    Zahtjev create(String xmlString);

    List<Zahtjev> findZahteviByStatusPodnet();

    void extractMetadata(Zahtjev zahtevSertifikata);

    ResponseEntity<byte[]> getPdf(int id) throws IOException;

    ResponseEntity<byte[]> getHtml(int id) throws IOException;
}
