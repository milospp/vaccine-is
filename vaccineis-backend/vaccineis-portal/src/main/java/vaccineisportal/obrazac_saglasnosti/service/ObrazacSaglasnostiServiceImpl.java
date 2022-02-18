package vaccineisportal.obrazac_saglasnosti.service;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.xerces.dom.ElementNSImpl;
import org.apache.xerces.dom.TextImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import vaccineisportal.authentication.service.AuthenticationService;
import vaccineisportal.obrazac_saglasnosti.model.Saglasnost;
import vaccineisportal.obrazac_saglasnosti.repository.ObrazacSaglasnostiExistRepository;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.repository.CRUDRDFRepository;
import zajednicko.service.MarshallingService;
import zajednicko.util.ZajednickoUtil;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class ObrazacSaglasnostiServiceImpl implements ObrazacSaglasnostiService {

    private final ObrazacSaglasnostiExistRepository obrazacSaglasnostiExistRepository;
    private final CRUDRDFRepository crudrdfRepository;
    private final AuthenticationService authenticationService;
    private final MarshallingService marshallingService;

    @Override
    public Saglasnost create(String xmlString) {
        Saglasnost saglasnost = obrazacSaglasnostiExistRepository.create(xmlString);
        try {
            extractMetadata(saglasnost);
        } catch (XPathExpressionException | ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        return saglasnost;
    }

    @Override
    public Saglasnost update(String xmlString) {
        return null;
    }

    @Override
    public List<Saglasnost> findAll() {
        return obrazacSaglasnostiExistRepository.findAll();
    }

    @Override
    public void extractMetadata(Saglasnost saglasnost) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        String xmlString = marshallingService.marshall(saglasnost, Saglasnost.class);



//        String xml = "<resp><status>good</status><msg>hi</msg></resp>";

        InputSource source = new InputSource(new StringReader(xmlString));

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(source);

        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();

        String metaIme = xpath.evaluate("//ime", document);
        String metaPrezime = xpath.evaluate("//prezime", document);
        String metaImeRoditelja = xpath.evaluate("//ulica", document);



        Korisnik korisnik = authenticationService.getLoggedInUser();
        LocalDateTime localDateTime = LocalDateTime.now();
        crudrdfRepository.uploadTriplet("rdf", ZajednickoUtil.XML_PREFIX + "saglasnost/" + saglasnost.getId(), "korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" + korisnik.getId() );

        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "saglasnost/" + "saglasnost/" + saglasnost.getId(), "korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" + korisnik.getId() );

        if (saglasnost.getDrzavljanstvo().getDrzavljanstvoSrbije() != null)
            crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "saglasnost/" + "saglasnost/" + saglasnost.getId(), "jmbgPodnosioca", saglasnost.getDrzavljanstvo().getJmbg());
        else
            crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "saglasnost/" + "saglasnost/" + saglasnost.getId(), "ebs", saglasnost.getDrzavljanstvo().getEBS());

        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "saglasnost/" + saglasnost.getId(), "imePodnosioca", metaIme);
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "saglasnost/" + saglasnost.getId(), "prezimePodnosioca", metaPrezime);
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "saglasnost/" + saglasnost.getId(), "imeRoditeljaPodnosioca", metaImeRoditelja);
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "saglasnost/" + saglasnost.getId(), "polPodnosioca", saglasnost.getPodaciPacijenta().getPol().value());
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "saglasnost/" + saglasnost.getId(), "datumRodjenjaPodnosioca", String.valueOf(saglasnost.getPodaciPacijenta().getDatumRodjenja()));
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "saglasnost/" + saglasnost.getId(), "mestoRodjenjaPodnosioca", String.valueOf(saglasnost.getPodaciPacijenta().getPodaciMjesta().getMjestoRodjenja()));
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "saglasnost/" + saglasnost.getId(), "adresaUlicaPodnosioca", String.valueOf(saglasnost.getPodaciPacijenta().getPodaciMjesta().getAdresa().getUlica().toString()));
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "saglasnost/" + saglasnost.getId(), "adresaBrojPodnosioca", String.valueOf(saglasnost.getPodaciPacijenta().getPodaciMjesta().getAdresa().getBroj()));
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "saglasnost/" + saglasnost.getId(), "mestoNaseljePodnosioca", String.valueOf(saglasnost.getPodaciPacijenta().getPodaciMjesta().getMjestoNaselje()));
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "saglasnost/" + saglasnost.getId(), "opstinaGradPodnosioca", String.valueOf(saglasnost.getPodaciPacijenta().getPodaciMjesta().getOpstinaGrad()));
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "saglasnost/" + saglasnost.getId(), "fiksniPodnosioca", saglasnost.getPodaciPacijenta().getKontaktPodaci().getFiksniTelefon());
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "saglasnost/" + saglasnost.getId(), "mobilniPodnosioca", saglasnost.getPodaciPacijenta().getKontaktPodaci().getMobilniTelefon());
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "saglasnost/" + saglasnost.getId(), "emailPodnosioca", saglasnost.getPodaciPacijenta().getKontaktPodaci().getEmail());
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "saglasnost/" + saglasnost.getId(), "datumIzdavanja", String.valueOf(localDateTime));
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
