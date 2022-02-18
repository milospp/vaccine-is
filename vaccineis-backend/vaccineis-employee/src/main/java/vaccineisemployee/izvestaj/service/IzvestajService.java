package vaccineisemployee.izvestaj.service;

import org.springframework.http.ResponseEntity;
import vaccineisemployee.izvestaj.model.Izvjestaj;
import java.io.IOException;

public interface IzvestajService {
    Izvjestaj create(String xmlString);

    Izvjestaj findOne(String id);

    void extractMetadataIzvestaj(Izvjestaj izvjestaj);

    ResponseEntity<?> getPdf(int id) throws IOException;

    ResponseEntity<?> getHtml(int id) throws IOException;
}
