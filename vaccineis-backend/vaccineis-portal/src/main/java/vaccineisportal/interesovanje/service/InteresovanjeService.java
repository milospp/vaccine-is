package vaccineisportal.interesovanje.service;

import org.springframework.http.ResponseEntity;

import org.xml.sax.SAXException;
import vaccineisportal.interesovanje.model.Interesovanje;
import zajednicko.model.docdatas.DocDatas;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface InteresovanjeService {

    Interesovanje create(String xmlString) throws IOException;

    List<Interesovanje> findAll();

    Interesovanje findOne(String id);

    void extractMetadataInteresovanje(Interesovanje interesovanje);

    ResponseEntity<?> getPdf(String id) throws IOException, ParserConfigurationException, SAXException;

    ResponseEntity<?> getHtml(String id) throws IOException, ParserConfigurationException, SAXException;

    DocDatas getInteresovanjaByUser(String uuid);

    void obradiTermine();

    String getRdfXml(String uuid);
    String getRdfJson(String uuid);
}