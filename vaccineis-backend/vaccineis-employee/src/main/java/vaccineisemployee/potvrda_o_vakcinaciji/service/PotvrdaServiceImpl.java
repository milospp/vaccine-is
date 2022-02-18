package vaccineisemployee.potvrda_o_vakcinaciji.service;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vaccineisemployee.authentication.service.AuthenticationService;
import vaccineisemployee.digitalni_sertifikat.model.CTtest;
import vaccineisemployee.potvrda_o_vakcinaciji.model.PotvrdaVakcinacije;
import vaccineisemployee.potvrda_o_vakcinaciji.repository.PotvrdaOVakcinacijiExistRepository;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.repository.CRUDRDFRepository;
import zajednicko.util.ZajednickoUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class PotvrdaServiceImpl implements PotvrdaService{
    private final PotvrdaOVakcinacijiExistRepository existRepository;
    private final AuthenticationService authenticationService;
    private final CRUDRDFRepository crudrdfRepository;

    @Override
    public PotvrdaVakcinacije create(String xmlString) {
        PotvrdaVakcinacije potvrdaVakcinacije = existRepository.create(xmlString);
        extractMetadataPotvrda(potvrdaVakcinacije);
        return potvrdaVakcinacije;
    }

    @Override
    public PotvrdaVakcinacije findOne(String id) {
        return existRepository.findOne(id);
    }

    @Override
    public void extractMetadataPotvrda(PotvrdaVakcinacije potvrdaVakcinacije) {
        Korisnik korisnik = authenticationService.getLoggedInUser();
        LocalDateTime localDateTime = LocalDateTime.now();

        cleanLastJmbg(potvrdaVakcinacije.getPodaciVakcinisanog().getJmbg());
        crudrdfRepository.uploadTriplet("rdf", "potvrda/" + potvrdaVakcinacije.getId(), "korisnik", localDateTime.toString());
        crudrdfRepository.uploadTriplet("rdf", "potvrda/" + potvrdaVakcinacije.getId(), "potvrda_jmbg", potvrdaVakcinacije.getPodaciVakcinisanog().getJmbg());
        crudrdfRepository.uploadTriplet("rdf", "potvrda/" + potvrdaVakcinacije.getId(), "poslednja_potvrda_jmbg", potvrdaVakcinacije.getPodaciVakcinisanog().getJmbg());
        crudrdfRepository.uploadTriplet("metadates", "potvrda/" + potvrdaVakcinacije.getId(), "korisnik", korisnik.getId() );
    }

    @Override
    public ResponseEntity<?> getPdf(int id) throws IOException {
        return getDocument("pdf");
    }

    @Override
    public ResponseEntity<?> getHtml(int id) throws IOException {
        return getDocument("html");
    }

    @Override
    public PotvrdaVakcinacije getPoslednjuPotvrdu(String uuid) {

        ResultSet results = crudrdfRepository.findByPredicateAndObject("rdf", "potvrda_jmbg", ZajednickoUtil.XML_PREFIX + "korisnik/" + uuid);

        for (ResultSet it = results; it.hasNext(); ) {
            QuerySolution s = it.next();
            PotvrdaVakcinacije i = findOne(ZajednickoUtil.getIdFromUri(s.get("s").toString()));
            return i;
        }

        return null;
    }

    private void cleanLastJmbg(String uuid){
        ResultSet results = crudrdfRepository.findByPredicateAndObject("rdf", "potvrda_jmbg", ZajednickoUtil.XML_PREFIX + "korisnik/" + uuid);
        for (ResultSet it = results; it.hasNext(); ) {
            QuerySolution s = it.next();
            String sub = s.get("s").toString();
            crudrdfRepository.deleteTriplet("rdf", sub, "poslednja_potvrda_jmbg", uuid);
        }

    }

    private static ResponseEntity<?> getDocument(String type) throws IOException {
        File file = new File("./src/main/resources/data/gen/files/potvrda-o-vakcinaciji" + type);
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
