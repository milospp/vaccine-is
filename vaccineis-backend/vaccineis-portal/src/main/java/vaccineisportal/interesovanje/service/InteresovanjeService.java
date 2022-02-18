package vaccineisportal.interesovanje.service;

import org.springframework.http.ResponseEntity;

import org.xml.sax.SAXException;
import vaccineisportal.interesovanje.model.Interesovanje;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface InteresovanjeService {

    Interesovanje create(String xmlString);

    Interesovanje findOne(String id);

    void extractMetadataInteresovanje(Interesovanje interesovanje);

    ResponseEntity<?> getPdf(int id) throws IOException, ParserConfigurationException, SAXException;

    ResponseEntity<?> getHtml(int id) throws IOException, ParserConfigurationException, SAXException;

    void obradiTermine();
}