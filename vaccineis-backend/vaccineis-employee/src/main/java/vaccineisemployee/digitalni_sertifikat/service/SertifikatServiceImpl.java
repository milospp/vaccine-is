package vaccineisemployee.digitalni_sertifikat.service;

import lombok.AllArgsConstructor;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import vaccineisemployee.authentication.service.AuthenticationService;
import vaccineisemployee.digitalni_sertifikat.model.ZeleniSertifikat;
import vaccineisemployee.digitalni_sertifikat.repository.DigitalniSertifikatExistRepository;
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.repository.CRUDRDFRepository;
import zajednicko.service.MailService;
import zajednicko.service.MarshallingService;
import zajednicko.util.ZajednickoUtil;
import zajednicko.xmlTransformations.Xml2HtmlTransformer;
import zajednicko.xmlTransformations.Xml2PdfTransformer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@AllArgsConstructor
@Service
public class SertifikatServiceImpl implements SertifikatService{
    private final DigitalniSertifikatExistRepository digitalniSertifikatExistRepository;
    private final CRUDRDFRepository crudrdfRepository;
    private final AuthenticationService authenticationService;
    private final MarshallingService marshallingService;
    private final MailService mailService;

    @Override
    public ResponseEntity<?> getPdf(String id) throws IOException {
        mailService.sendSomeMail("Skinut pdf", "Naslov", "Text text text text text text text text text text text text text");

        ZeleniSertifikat i = findOne(id);
        try {
            return this.getPdfDocument(i);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getHtml(String id) throws IOException {
        mailService.sendSomeMail("Skinut html", "Naslov", "Text text text text text text text text text text text text text  ");

        ZeleniSertifikat i = findOne(id);
        return this.getHtmlDocument(i);
    }

    public ResponseEntity<?> getHtmlDocument(ZeleniSertifikat zeleniSertifikat) throws IOException {
        System.out.println("[INFO] " + Xml2HtmlTransformer.class.getSimpleName());

        String xmlString = marshallingService.marshall(zeleniSertifikat, ZeleniSertifikat.class);

        Xml2HtmlTransformer htmlTransformer = new Xml2HtmlTransformer();
        String htmlData = htmlTransformer.generateHTML(xmlString, ZajednickoUtil.DIGITALNI_XSLT);
        byte[] arr = htmlData.getBytes(StandardCharsets.UTF_8);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentLength(arr.length);
        responseHeaders.setContentType(MediaType.valueOf("application/html"));
        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile.html"));
        return new ResponseEntity<>(arr, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<?> getPdfDocument(ZeleniSertifikat zeleniSertifikat) throws Exception {
        System.out.println("[INFO] " + Xml2HtmlTransformer.class.getSimpleName());

        String xmlString = marshallingService.marshall(zeleniSertifikat, ZeleniSertifikat.class);

        Xml2PdfTransformer pdfTransformer = new Xml2PdfTransformer(ZajednickoUtil.DIGITALNI_PDF);
        byte[] arr =  pdfTransformer.generatePDF(xmlString);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentLength(arr.length);
        responseHeaders.setContentType(MediaType.valueOf("application/pdf"));
        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile.pdf"));
        return new ResponseEntity<>(arr, responseHeaders, HttpStatus.OK);
    }

    @Override
    public DocDatas getSertifikatiByUser(String uuid) {
        ResultSet results = crudrdfRepository.findByPredicateAndObject("rdf", "korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" + uuid);

        DocDatas a = new DocDatas();

        for (ResultSet it = results; it.hasNext(); ) {
            QuerySolution s = it.next();
            ZeleniSertifikat i = findOne(ZajednickoUtil.getIdFromUri(s.get("s").toString()));
            DocDatas.DocData data = new DocDatas.DocData();
            data.setId(i.getId());
            data.setNaziv("Sertifikat");
            data.setDatum(i.getDatumVrijemeIzdavanja());
            data.setType("digitalni-sertifikat");
            data.setUri(ZajednickoUtil.XML_PREFIX + "digitalni-sertifikat/" + i.getId());
            data.setIme(i.getPodaciVakcinisanog().getIme());
            data.setPrezime(i.getPodaciVakcinisanog().getPrezime());
            a.getDocData().add(data);
        }

        return a;
    }

    @Override
    public ZeleniSertifikat findOne(String id) {
        return digitalniSertifikatExistRepository.findOne(id);
    }

    @Override
    public void extractMetadata(ZeleniSertifikat zeleniSertifikat) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        Korisnik korisnik = authenticationService.getLoggedInUser();
        crudrdfRepository.uploadTriplet("rdf", ZajednickoUtil.XML_PREFIX + "digitalni-sertifikat/" + zeleniSertifikat.getId(), "korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" + korisnik.getId() );
    }
}
