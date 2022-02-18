package vaccineisportal.obrazac_saglasnosti.service;

import org.springframework.http.ResponseEntity;
import org.xml.sax.SAXException;
import vaccineisportal.obrazac_saglasnosti.model.Saglasnost;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

public interface ObrazacSaglasnostiService {

    Saglasnost create(String xmlString);

    Saglasnost update(String xmlString);

    List<Saglasnost> findAll();

    void extractMetadata(Saglasnost saglasnost) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException;

    ResponseEntity<byte[]> getPdf(int id) throws IOException;

    ResponseEntity<byte[]> getHtml(int id) throws IOException;

    static ResponseEntity<byte[]> getDocument(String type) throws IOException {
        return null;
    }
}
