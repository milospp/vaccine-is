package vaccineisportal.obrazac_saglasnosti.service;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vaccineisportal.obrazac_saglasnosti.model.Saglasnost;
import vaccineisportal.obrazac_saglasnosti.repository.ObrazacSaglasnostiExistRepository;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

@AllArgsConstructor
@Service
public class ObrazacSaglasnostiServiceImpl implements ObrazacSaglasnostiService {

    private ObrazacSaglasnostiExistRepository obrazacSaglasnostiExistRepository;

    @Override
    public Saglasnost create(String xmlString) {
        return obrazacSaglasnostiExistRepository.create(xmlString);
    }

    public ResponseEntity<byte[]> getPdf(int id) throws IOException {
        return getDocument("pdf");
    }

    public ResponseEntity<byte[]> getHtml(int id) throws IOException {
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