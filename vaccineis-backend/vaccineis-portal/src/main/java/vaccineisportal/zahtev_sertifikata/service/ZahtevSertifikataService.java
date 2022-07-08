package vaccineisportal.zahtev_sertifikata.service;

import vaccineisportal.zahtev_sertifikata.model.Zahtjev;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zajednicko.model.docdatas.DocDatas;

import java.io.IOException;
import java.util.List;

@Service
public interface ZahtevSertifikataService {

    Zahtjev create(String xmlString);

    Zahtjev findOne(String id);

    List<Zahtjev> findZahteviByStatusPodnet();

    void extractMetadata(Zahtjev zahtevSertifikata);

    ResponseEntity<?> getPdf(String id) throws IOException;

    ResponseEntity<?> getHtml(String id) throws IOException;

    DocDatas getZahtjeviByUser(String uuid);

    void odbijZahtev(String uuid);
}
