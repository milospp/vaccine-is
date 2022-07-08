package vaccineisemployee.potvrda_o_vakcinaciji.service;

import org.springframework.http.ResponseEntity;
import vaccineisemployee.potvrda_o_vakcinaciji.model.PotvrdaVakcinacije;
import zajednicko.model.docdatas.DocDatas;

import java.io.IOException;
import java.util.List;

public interface PotvrdaService {
    PotvrdaVakcinacije create(String xmlString) throws IOException;

    PotvrdaVakcinacije findOne(String id);

    void extractMetadataPotvrda(PotvrdaVakcinacije potvrdaVakcinacije);

    ResponseEntity<?> getPdf(String id) throws IOException;

    PotvrdaVakcinacije getPoslednjuPotvrdu(String uuid);

    ResponseEntity<?> getHtml(String id) throws IOException;

    DocDatas getPotvrdeByUser(String uuid);

    String getRdfXml(String uuid);
    String getRdfJson(String uuid);
}
