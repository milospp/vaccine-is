package vaccineisportal.obrazac_saglasnosti.service;

import org.springframework.http.ResponseEntity;
import org.xml.sax.SAXException;
import vaccineisportal.obrazac_saglasnosti.model.Saglasnost;
import zajednicko.model.docdatas.DocDatas;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface ObrazacSaglasnostiService {

    Saglasnost create(String xmlString);

    Saglasnost update(String id, String xmlString);

    Saglasnost findOne(String id);

    List<Saglasnost> findAll();

    void extractMetadata(Saglasnost saglasnost) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException;

    ResponseEntity<?> getPdf(String id) throws IOException;

    ResponseEntity<?> getHtml(String id) throws IOException;

    DocDatas getObrasciByUser(String uuid);

    String getRdfXml(String uuid);
    String getRdfJson(String uuid);
}
