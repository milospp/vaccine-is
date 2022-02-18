package vaccineisemployee.izvestaj.service;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.jena.base.Sys;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vaccineisemployee.authentication.service.AuthenticationService;
import vaccineisemployee.izvestaj.model.Izvjestaj;
import vaccineisemployee.izvestaj.repository.IzvestajExistRepository;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.repository.CRUDRDFRepository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

@AllArgsConstructor
@Service
public class IzvestajServiceImpl implements IzvestajService{
    private final IzvestajExistRepository izvestajExistRepository;
    private final AuthenticationService authenticationService;
    private final CRUDRDFRepository crudrdfRepository;

    @Override
    public Izvjestaj create(String xmlString) {
        Izvjestaj izvjestaj = izvestajExistRepository.create(xmlString);
        extractMetadataIzvestaj(izvjestaj);
        return izvjestaj;
    }

    @Override
    public Izvjestaj findOne(String id) {
        return izvestajExistRepository.findOne(id);
    }

    @Override
    public void extractMetadataIzvestaj(Izvjestaj izvjestaj) {
        Korisnik korisnik = authenticationService.getLoggedInUser();
        LocalDateTime localDateTime = LocalDateTime.now();
        crudrdfRepository.uploadTriplet("rdf", "izvjestaj/" + izvjestaj.getId(), "korisnik", localDateTime.toString() );
        crudrdfRepository.uploadTriplet("metadates", "izvjestaj/" + izvjestaj.getId(), "korisnik", korisnik.getId() );
    }

    @Override
    public ResponseEntity<?> getPdf(int id) throws IOException {
        return getDocument("pdf");
    }

    @Override
    public ResponseEntity<?> getHtml(int id) throws IOException {
        return getDocument("html");
    }

    private static ResponseEntity<?> getDocument(String type) throws IOException {
        File file = new File("./src/main/resources/data/gen/files/izvjestaj." + type);
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
