package vaccineisportal.obrazac_saglasnosti.service;

import lombok.AllArgsConstructor;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
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
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.model.util.ResultSetConnection;
import zajednicko.repository.CRUDRDFRepository;
import zajednicko.service.MailService;
import zajednicko.service.MarshallingService;
import zajednicko.util.ZajednickoUtil;
import zajednicko.xmlTransformations.Xml2HtmlTransformer;
import zajednicko.xmlTransformations.Xml2PdfTransformer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
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
    private final MailService mailService;

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
    public Saglasnost update(String id, String xmlString) {
        return obrazacSaglasnostiExistRepository.update(id, xmlString);
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
        crudrdfRepository.uploadTriplet("rdf", ZajednickoUtil.XML_PREFIX + "saglasnost/" + saglasnost.getId(), "saglasnost_korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" + korisnik.getId() );

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

    public ResponseEntity<?> getPdf(String id) throws IOException {
//        mailService.sendMail("Skinut pdf", "Naslov", "Text text text text text text text text text text text text text");

        Saglasnost i = findOne(id);
        try {
            return this.getPdfDocument(i);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public String getRdfXml(String uuid) {
        ResultSetConnection resultSetConnection = crudrdfRepository.findBySubject("metadates", ZajednickoUtil.XML_PREFIX + "saglasnost/" + uuid);
        ResultSet resultSet = resultSetConnection.getResultSet();
        resultSetConnection.closeConnection();

        String asXml = ResultSetFormatter.asXMLString(resultSet);
        resultSetConnection.closeConnection();

        return asXml;
    }

    @Override
    public String getRdfJson(String uuid) {
        ResultSetConnection resultSetConnection = crudrdfRepository.findBySubject("metadates", ZajednickoUtil.XML_PREFIX + "saglasnost/" + uuid);
        ResultSet resultSet = resultSetConnection.getResultSet();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(out, resultSet);
        resultSetConnection.closeConnection();
        String finalString = out.toString();

        return finalString;
    }


    public ResponseEntity<?> getHtml(String id) throws IOException {
//        mailService.sendMail("Skinut html", "Naslov", "Text text text text text text text text text text text text text  ");

        Saglasnost i = findOne(id);
        return this.getHtmlDocument(i);
    }

    public ResponseEntity<?> getHtmlDocument(Saglasnost saglasnost) throws IOException {
        System.out.println("[INFO] " + Xml2HtmlTransformer.class.getSimpleName());

        String xmlString = marshallingService.marshall(saglasnost, Saglasnost.class);

        Xml2HtmlTransformer htmlTransformer = new Xml2HtmlTransformer();
        String htmlData = htmlTransformer.generateHTML(xmlString, ZajednickoUtil.SAGLASNOST_XSLT);
        byte[] arr = htmlData.getBytes(StandardCharsets.UTF_8);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentLength(arr.length);
        responseHeaders.setContentType(MediaType.valueOf("application/html"));
        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile.html"));
        return new ResponseEntity<>(arr, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<?> getPdfDocument(Saglasnost saglasnost) throws Exception {
        System.out.println("[INFO] " + Xml2HtmlTransformer.class.getSimpleName());

        String xmlString = marshallingService.marshall(saglasnost, Saglasnost.class);

        Xml2PdfTransformer pdfTransformer = new Xml2PdfTransformer(ZajednickoUtil.SAGLASNOST_PDF);
        byte[] arr =  pdfTransformer.generatePDF(xmlString);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentLength(arr.length);
        responseHeaders.setContentType(MediaType.valueOf("application/pdf"));
        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile.pdf"));
        return new ResponseEntity<>(arr, responseHeaders, HttpStatus.OK);
    }

    @Override
    public DocDatas getObrasciByUser(String uuid) {
        ResultSetConnection resultsCon = crudrdfRepository.findByPredicateAndObject("rdf", "saglasnost_korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" + uuid);
        ResultSet results = resultsCon.getResultSet();

        DocDatas a = new DocDatas();

        for (ResultSet it = results; it.hasNext(); ) {
            QuerySolution s = it.next();
            Saglasnost i = findOne(ZajednickoUtil.getIdFromUri(s.get("s").toString()));
            DocDatas.DocData data = new DocDatas.DocData();
            data.setId(i.getId());
            data.setNaziv("Saglasnost");
            data.setDatum(i.getPodaciPacijenta().getDatumSaglasnosti());
            data.setType("saglasnost");
            data.setUri(ZajednickoUtil.XML_PREFIX + "saglasnost/" + i.getId());
            data.setIme(i.getPodaciPacijenta().getIme());
            data.setPrezime(i.getPodaciPacijenta().getPrezime());
            a.getDocData().add(data);
        }

        resultsCon.closeConnection();

        return a;
    }

    @Override
    public Saglasnost findOne(String id) {
        return obrazacSaglasnostiExistRepository.findOne(id);
    }
}
