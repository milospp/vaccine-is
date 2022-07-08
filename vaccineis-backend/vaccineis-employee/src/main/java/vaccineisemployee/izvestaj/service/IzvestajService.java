package vaccineisemployee.izvestaj.service;

import org.springframework.http.ResponseEntity;
import org.xml.sax.SAXException;
import vaccineisemployee.digitalni_sertifikat.model.ZeleniSertifikat;
import vaccineisemployee.izvestaj.model.Izvjestaj;
import zajednicko.model.docdatas.DocDatas;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.Collection;

public interface IzvestajService {
    Izvjestaj create(String xmlString);

    Izvjestaj findOne(String id);

    void extractMetadataIzvestaj(Izvjestaj izvjestaj);

    ResponseEntity<?> getPdf(String id) throws IOException;

    ResponseEntity<?> getHtml(String id) throws IOException;

    DocDatas getIzvjestajiByUser(String uuid);

    String naprednaPretraga(String query);
}
