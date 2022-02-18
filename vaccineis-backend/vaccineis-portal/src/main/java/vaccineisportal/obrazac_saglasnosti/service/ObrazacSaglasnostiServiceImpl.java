package vaccineisportal.obrazac_saglasnosti.service;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vaccineisportal.authentication.service.AuthenticationService;
import vaccineisportal.obrazac_saglasnosti.model.Saglasnost;
import vaccineisportal.obrazac_saglasnosti.repository.ObrazacSaglasnostiExistRepository;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.repository.CRUDRDFRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;

@AllArgsConstructor
@Service
public class ObrazacSaglasnostiServiceImpl implements ObrazacSaglasnostiService {

    private final ObrazacSaglasnostiExistRepository obrazacSaglasnostiExistRepository;
    private final CRUDRDFRepository crudrdfRepository;
    private final AuthenticationService authenticationService;

    @Override
    public Saglasnost create(String xmlString) {
        Saglasnost saglasnost = obrazacSaglasnostiExistRepository.create(xmlString);
        extractMetadata(saglasnost);

        return saglasnost;
    }

    @Override
    public Saglasnost update(String xmlString) {
        return null;
    }

    @Override
    public void extractMetadata(Saglasnost saglasnost) {
        Korisnik korisnik = authenticationService.getLoggedInUser();
        LocalDateTime localDateTime = LocalDateTime.now();
        crudrdfRepository.uploadTriplet("rdf", "saglasnost/" + saglasnost.getId(), "korisnik", korisnik.getId() );

        crudrdfRepository.uploadTriplet("metadates", "saglasnost/" + saglasnost.getId(), "korisnik", korisnik.getId() );

        if (saglasnost.getDrzavljanstvo().getDrzavljanstvoSrbije() != null)
            crudrdfRepository.uploadTriplet("metadates", "saglasnost/" + saglasnost.getId(), "jmbgPodnosioca", saglasnost.getDrzavljanstvo().getJmbg());
        else
            crudrdfRepository.uploadTriplet("metadates", "saglasnost/" + saglasnost.getId(), "ebs", saglasnost.getDrzavljanstvo().getEBS());

        crudrdfRepository.uploadTriplet("metadates", "saglasnost/" + saglasnost.getId(), "imePodnosioca", saglasnost.getPodaciPacijenta().getIme());
        crudrdfRepository.uploadTriplet("metadates", "saglasnost/" + saglasnost.getId(), "prezimePodnosioca", saglasnost.getPodaciPacijenta().getPrezime());
        crudrdfRepository.uploadTriplet("metadates", "saglasnost/" + saglasnost.getId(), "imeRoditeljaPodnosioca", (String) saglasnost.getPodaciPacijenta().getImeRoditelja());
        crudrdfRepository.uploadTriplet("metadates", "saglasnost/" + saglasnost.getId(), "polPodnosioca", saglasnost.getPodaciPacijenta().getPol().value());
        crudrdfRepository.uploadTriplet("metadates", "saglasnost/" + saglasnost.getId(), "datumRodjenjaPodnosioca", String.valueOf(saglasnost.getPodaciPacijenta().getDatumRodjenja()));
        crudrdfRepository.uploadTriplet("metadates", "saglasnost/" + saglasnost.getId(), "mestoRodjenjaPodnosioca", String.valueOf(saglasnost.getPodaciPacijenta().getPodaciMjesta().getMjestoRodjenja()));
        crudrdfRepository.uploadTriplet("metadates", "saglasnost/" + saglasnost.getId(), "adresaUlicaPodnosioca", String.valueOf(saglasnost.getPodaciPacijenta().getPodaciMjesta().getAdresa().getUlica().toString()));
        crudrdfRepository.uploadTriplet("metadates", "saglasnost/" + saglasnost.getId(), "adresaBrojPodnosioca", String.valueOf(saglasnost.getPodaciPacijenta().getPodaciMjesta().getAdresa().getBroj()));
        crudrdfRepository.uploadTriplet("metadates", "saglasnost/" + saglasnost.getId(), "mestoNaseljePodnosioca", String.valueOf(saglasnost.getPodaciPacijenta().getPodaciMjesta().getMjestoNaselje()));
        crudrdfRepository.uploadTriplet("metadates", "saglasnost/" + saglasnost.getId(), "opstinaGradPodnosioca", String.valueOf(saglasnost.getPodaciPacijenta().getPodaciMjesta().getOpstinaGrad()));
        crudrdfRepository.uploadTriplet("metadates", "saglasnost/" + saglasnost.getId(), "fiksniPodnosioca", saglasnost.getPodaciPacijenta().getKontaktPodaci().getFiksniTelefon());
        crudrdfRepository.uploadTriplet("metadates", "saglasnost/" + saglasnost.getId(), "mobilniPodnosioca", saglasnost.getPodaciPacijenta().getKontaktPodaci().getMobilniTelefon());
        crudrdfRepository.uploadTriplet("metadates", "saglasnost/" + saglasnost.getId(), "emailPodnosioca", saglasnost.getPodaciPacijenta().getKontaktPodaci().getEmail());
        crudrdfRepository.uploadTriplet("metadates", "saglasnost/" + saglasnost.getId(), "datumIzdavanja", String.valueOf(localDateTime));
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
