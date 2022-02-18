package vaccineisemployee.potvrda_o_vakcinaciji.service;

import org.springframework.http.ResponseEntity;
import vaccineisemployee.potvrda_o_vakcinaciji.model.PotvrdaVakcinacije;
import zajednicko.model.docdatas.DocDatas;

import java.io.IOException;

public interface PotvrdaService {
    PotvrdaVakcinacije create(String xmlString);

    PotvrdaVakcinacije findOne(String id);

    void extractMetadataPotvrda(PotvrdaVakcinacije potvrdaVakcinacije);

    ResponseEntity<?> getPdf(String id) throws IOException;

    ResponseEntity<?> getHtml(String id) throws IOException;

    DocDatas getPotvrdeByUser(String uuid);
}
