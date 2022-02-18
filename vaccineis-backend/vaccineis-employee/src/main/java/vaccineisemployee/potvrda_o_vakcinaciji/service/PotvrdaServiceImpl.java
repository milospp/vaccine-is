package vaccineisemployee.potvrda_o_vakcinaciji.service;

import lombok.AllArgsConstructor;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vaccineisemployee.authentication.service.AuthenticationService;
import vaccineisemployee.potvrda_o_vakcinaciji.model.PotvrdaVakcinacije;
import vaccineisemployee.potvrda_o_vakcinaciji.repository.PotvrdaOVakcinacijiExistRepository;
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.repository.CRUDRDFRepository;
import zajednicko.service.MailService;
import zajednicko.service.MarshallingService;
import zajednicko.util.ZajednickoUtil;
import zajednicko.xmlTransformations.Xml2HtmlTransformer;
import zajednicko.xmlTransformations.Xml2PdfTransformer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collections;

@AllArgsConstructor
@Service
public class PotvrdaServiceImpl implements PotvrdaService{
    private final PotvrdaOVakcinacijiExistRepository existRepository;
    private final AuthenticationService authenticationService;
    private final CRUDRDFRepository crudrdfRepository;
    private final MarshallingService marshallingService;
    private final MailService mailService;

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
        crudrdfRepository.uploadTriplet("rdf", "potvrda/" + potvrdaVakcinacije.getId(), "korisnik", localDateTime.toString());
        crudrdfRepository.uploadTriplet("metadates", "potvrda/" + potvrdaVakcinacije.getId(), "korisnik", korisnik.getId() );
    }

    @Override
    public ResponseEntity<?> getPdf(String id) throws IOException {
        mailService.sendSomeMail("Skinut pdf", "Naslov", "Text text text text text text text text text text text text text");

        PotvrdaVakcinacije i = findOne(id);
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

        PotvrdaVakcinacije i = findOne(id);
        return this.getHtmlDocument(i);
    }

    public ResponseEntity<?> getHtmlDocument(PotvrdaVakcinacije potvrdaVakcinacije) throws IOException {
        System.out.println("[INFO] " + Xml2HtmlTransformer.class.getSimpleName());

        String xmlString = marshallingService.marshall(potvrdaVakcinacije, PotvrdaVakcinacije.class);

        Xml2HtmlTransformer htmlTransformer = new Xml2HtmlTransformer();
        String htmlData = htmlTransformer.generateHTML(xmlString, ZajednickoUtil.POTVRDA_XSLT);
        byte[] arr = htmlData.getBytes(StandardCharsets.UTF_8);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentLength(arr.length);
        responseHeaders.setContentType(MediaType.valueOf("application/html"));
        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile.html"));
        return new ResponseEntity<>(arr, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<?> getPdfDocument(PotvrdaVakcinacije potvrdaVakcinacije) throws Exception {
        System.out.println("[INFO] " + Xml2HtmlTransformer.class.getSimpleName());

        String xmlString = marshallingService.marshall(potvrdaVakcinacije, PotvrdaVakcinacije.class);

        Xml2PdfTransformer pdfTransformer = new Xml2PdfTransformer(ZajednickoUtil.POTVRDA_PDF);
        byte[] arr =  pdfTransformer.generatePDF(xmlString);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentLength(arr.length);
        responseHeaders.setContentType(MediaType.valueOf("application/pdf"));
        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile.pdf"));
        return new ResponseEntity<>(arr, responseHeaders, HttpStatus.OK);
    }

    @Override
    public DocDatas getPotvrdeByUser(String uuid) {
        ResultSet results = crudrdfRepository.findByPredicateAndObject("metadates", "korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" + uuid);

        DocDatas a = new DocDatas();

        for (ResultSet it = results; it.hasNext(); ) {
            QuerySolution s = it.next();
            PotvrdaVakcinacije i = findOne(ZajednickoUtil.getIdFromUri(s.get("s").toString()));
            DocDatas.DocData data = new DocDatas.DocData();
            data.setId(i.getId());
            data.setNaziv("Potvrda");
            data.setDatum(i.getPodaciVakcinacije().getDatumIzdavanjaPotvrde());
            data.setType("potvrda-vakcinacije");
            data.setUri(ZajednickoUtil.XML_PREFIX + "potvrda/" + i.getId());
            data.setIme(i.getPodaciVakcinisanog().getIme());
            data.setPrezime(i.getPodaciVakcinisanog().getPrezime());
            a.getDocData().add(data);
        }

        return a;
    }
}
