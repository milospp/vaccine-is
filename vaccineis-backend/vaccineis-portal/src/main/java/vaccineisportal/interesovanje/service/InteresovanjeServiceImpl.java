package vaccineisportal.interesovanje.service;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vaccineisportal.interesovanje.model.Interesovanje;
import vaccineisportal.interesovanje.repository.InteresovanjeExistRepository;
import zajednicko.service.MailService;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@AllArgsConstructor
@Service
public class InteresovanjeServiceImpl implements InteresovanjeService {

    private final InteresovanjeExistRepository interesovanjeExistRepository;
    private final MailService mailService;

    @Override
    public Interesovanje create(String xmlString) {
        return interesovanjeExistRepository.create(xmlString);
    }

    @Override
    public ResponseEntity<?> getPdf(int id) throws IOException {
        mailService.sendSomeMail("Skinut pdf", "Naslov", "Text text text text text text text text text text text text text");
        return getDocument("pdf");
    }

    @Override
    public ResponseEntity<?> getHtml(int id) throws IOException {
        mailService.sendSomeMail("Skinut pdf", "Naslov", "Text text text text text text text text text text text text text  ");
        return getDocument("html");
    }

    public static ResponseEntity<?> getDocument(String type) throws IOException {
        File file = new File("./src/main/resources/files/interesovanje." + type);
        byte[] arr = FileUtils.readFileToByteArray(file);
        String ret = new String(arr, StandardCharsets.UTF_8);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentLength(arr.length);
        responseHeaders.setContentType(MediaType.valueOf("application/" + type));
        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile." + type));
        return new ResponseEntity<>(arr, responseHeaders, HttpStatus.OK);
    }
}
