package vaccineisemployee.digitalni_sertifikat.service;

import org.springframework.http.ResponseEntity;
import org.xml.sax.SAXException;
import vaccineisemployee.digitalni_sertifikat.model.ZeleniSertifikat;
import zajednicko.model.docdatas.DocDatas;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

public interface SertifikatService {

    ResponseEntity<?> getPdf(String id) throws IOException;

    ZeleniSertifikat generisiSertifikat(String uuidZahteva);

    ResponseEntity<?> getHtml(String id) throws IOException;

    DocDatas getSertifikatiByUser(String uuid);

    ZeleniSertifikat findOne(String id);

    void extractMetadata(ZeleniSertifikat zeleniSertifikat) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException;

    String getRdfXml(String uuid);
    String getRdfJson(String uuid);
}
