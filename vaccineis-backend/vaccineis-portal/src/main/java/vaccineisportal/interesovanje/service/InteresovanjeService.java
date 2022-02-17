package vaccineisportal.interesovanje.service;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.jena.rdf.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zajednicko.db.ExistManager;
import zajednicko.db.FusekiManager;
import zajednicko.repository.CRUDRDFRepository;
import zajednicko.repository.CRUDRDFRepositoryImpl;
import zajednicko.repository.CRUDRepository;
import zajednicko.service.MailService;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

@Service
@AllArgsConstructor
public class InteresovanjeService {

    protected final FusekiManager fusekiManager;
    protected final ExistManager existManager;
    protected final CRUDRDFRepository crudrdfRepository;
    protected MailService mailService;

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public void writeSomething() {
        crudrdfRepository.uploadTriplet("test", "http://www.ftn.uns.ac.rs/rdf/examples/person/Petar_Petrovic", "radi", "voli");
//        fusekiManager.testUpload();
    }

    public ResponseEntity<byte[]> getPdf(int id) throws IOException {
        mailService.sendSomeMail("Skinut pdf", "Naslov", "Text text text text text text text text text text text text text");
        return getDocument("pdf");
    }

    public ResponseEntity<byte[]> getHtml(int id) throws IOException {
        mailService.sendSomeMail("Skinut pdf", "Naslov", "Text text text text text text text text text text text text text  ");
        return getDocument("html");
    }

    public static ResponseEntity<byte[]> getDocument(String type) throws IOException {
        File file = new File("./src/main/resources/files/interesovanje."+type);
        byte[] arr = FileUtils.readFileToByteArray(file);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentLength(arr.length);
        responseHeaders.setContentType(MediaType.valueOf("application/" + type));
        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile."+type));
        return new ResponseEntity<byte[]> (arr, responseHeaders, HttpStatus.OK);
    }
}
