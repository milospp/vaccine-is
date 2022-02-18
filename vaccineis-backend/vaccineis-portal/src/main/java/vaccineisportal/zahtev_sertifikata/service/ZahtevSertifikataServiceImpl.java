package vaccineisportal.zahtev_sertifikata.service;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vaccineisportal.authentication.service.AuthenticationService;
import vaccineisportal.zahtev_sertifikata.model.ZahtevZaSertifikatStatus;
import vaccineisportal.zahtev_sertifikata.model.Zahtjev;
import vaccineisportal.zahtev_sertifikata.repository.ZahtevSertifikataExistRepository;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.repository.CRUDRDFRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class ZahtevSertifikataServiceImpl implements ZahtevSertifikataService {

    private final ZahtevSertifikataExistRepository zahtevSertifikataExistRepository;
    private final CRUDRDFRepository crudrdfRepository;
    private final AuthenticationService authenticationService;

    @Override
    public Zahtjev create(String xmlString) {
        Zahtjev zahtjev = zahtevSertifikataExistRepository.create(xmlString);
        extractMetadata(zahtjev);

        return zahtjev;
    }

    @Override
    public List<Zahtjev> findZahteviByStatusPodnet() {
        ResultSet rs = crudrdfRepository.findByPredicateAndObject("rdf", "status", String.valueOf(ZahtevZaSertifikatStatus.PODNET));
        rs.getResultVars();

        List<String> ids = new ArrayList<>();
        while (rs.hasNext()) {
            QuerySolution s = rs.next();
            String[] arr = s.get("s").toString().split("/");
            String id = arr[arr.length-1];

            ids.add(id);
        }

        var retVal = new ArrayList<Zahtjev>();
        ids.forEach(id -> retVal.add(zahtevSertifikataExistRepository.findOne(id)));

        return retVal;
    }

    @Override
    public void extractMetadata(Zahtjev zahtevSertifikata) {
        Korisnik korisnik = authenticationService.getLoggedInUser();
        LocalDateTime localDateTime = LocalDateTime.now();
        crudrdfRepository.uploadTriplet("rdf", "zahtev/" + zahtevSertifikata.getId(), "korisnik", korisnik.getId() );
        crudrdfRepository.uploadTriplet("rdf", "zahtev/" + zahtevSertifikata.getId(), "status", ZahtevZaSertifikatStatus.PODNET.name());

        crudrdfRepository.uploadTriplet("metadates", "zahtev/" + zahtevSertifikata.getId(), "korisnik", korisnik.getId() );
        crudrdfRepository.uploadTriplet("metadates", "zahtev/" + zahtevSertifikata.getId(), "imePodnosioca", zahtevSertifikata.getPodnosilac().getIme() );
        crudrdfRepository.uploadTriplet("metadates", "zahtev/" + zahtevSertifikata.getId(), "prezimePodnosioca", zahtevSertifikata.getPodnosilac().getPrezime() );
        crudrdfRepository.uploadTriplet("metadates", "zahtev/" + zahtevSertifikata.getId(), "datumRodjenjaPodnosioca", String.valueOf(zahtevSertifikata.getPodnosilac().getDatumRodjenja()));
        crudrdfRepository.uploadTriplet("metadates", "zahtev/" + zahtevSertifikata.getId(), "polPodnosioca", String.valueOf(zahtevSertifikata.getPodnosilac().getPol()));
        crudrdfRepository.uploadTriplet("metadates", "zahtev/" + zahtevSertifikata.getId(), "datumIzdavanja", localDateTime.toString());
    }

    @Override
    public ResponseEntity<byte[]> getPdf(int id) throws IOException {
        return getDocument("pdf");
    }

    @Override
    public ResponseEntity<byte[]> getHtml(int id) throws IOException {
        return getDocument("html");
    }

    public static ResponseEntity<byte[]> getDocument(String type) throws IOException {
        File file = new File("./src/main/resources/files/interesovanje." + type);
        byte[] arr = FileUtils.readFileToByteArray(file);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentLength(arr.length);
        responseHeaders.setContentType(MediaType.valueOf("application/" + type));
        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile." + type));
        return new ResponseEntity<byte[]>(arr, responseHeaders, HttpStatus.OK);
    }
}
