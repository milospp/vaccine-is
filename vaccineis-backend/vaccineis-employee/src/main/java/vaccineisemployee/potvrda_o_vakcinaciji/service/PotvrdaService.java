package vaccineisemployee.potvrda_o_vakcinaciji.service;

import org.springframework.http.ResponseEntity;
import vaccineisemployee.potvrda_o_vakcinaciji.model.PotvrdaVakcinacije;

import java.io.IOException;

public interface PotvrdaService {
    PotvrdaVakcinacije create(String xmlString);

    PotvrdaVakcinacije findOne(String id);

    void extractMetadataPotvrda(PotvrdaVakcinacije potvrdaVakcinacije);

    ResponseEntity<?> getPdf(int id) throws IOException;

    ResponseEntity<?> getHtml(int id) throws IOException;
}
