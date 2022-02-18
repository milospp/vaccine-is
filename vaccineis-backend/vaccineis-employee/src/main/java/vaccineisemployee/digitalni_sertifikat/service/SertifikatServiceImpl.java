package vaccineisemployee.digitalni_sertifikat.service;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;

@AllArgsConstructor
@Service
public class SertifikatServiceImpl implements SertifikatService{

    @Override
    public ResponseEntity<?> getPdf(int id) throws IOException {
        return getDocument("pdf");
    }

    @Override
    public ResponseEntity<?> getHtml(int id) throws IOException {
        return getDocument("html");
    }

    private static ResponseEntity<?> getDocument(String type) throws IOException {
        File file = new File("./src/main/resources/data/gen/files/digitalni-sertifikat." + type);
        byte[] arr = FileUtils.readFileToByteArray(file);
        System.out.println(Arrays.toString(arr));
        String ret = new String(arr, StandardCharsets.UTF_8);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentLength(arr.length);
        responseHeaders.setContentType(MediaType.valueOf("application/" + type));
        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile." + type));
        return new ResponseEntity<>(arr, responseHeaders, HttpStatus.OK);
    }
}
