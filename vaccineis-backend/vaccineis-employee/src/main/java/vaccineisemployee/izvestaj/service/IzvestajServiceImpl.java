package vaccineisemployee.izvestaj.service;

import lombok.AllArgsConstructor;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import org.apache.jena.query.ResultSetFormatter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vaccineisemployee.authentication.service.AuthenticationService;
import vaccineisemployee.izvestaj.model.Izvjestaj;
import vaccineisemployee.izvestaj.repository.IzvestajExistRepository;
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.model.util.ResultSetConnection;
import zajednicko.repository.CRUDRDFRepository;
import zajednicko.service.MailService;
import zajednicko.service.MarshallingService;
import zajednicko.util.ZajednickoUtil;
import zajednicko.xmlTransformations.Xml2HtmlTransformer;
import zajednicko.xmlTransformations.Xml2PdfTransformer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@Service
public class IzvestajServiceImpl implements IzvestajService{
    private final IzvestajExistRepository izvestajExistRepository;
    private final AuthenticationService authenticationService;
    private final CRUDRDFRepository crudrdfRepository;
    private final MarshallingService marshallingService;
    private final MailService mailService;

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
    public ResponseEntity<?> getPdf(String id) throws IOException {
//        mailService.sendSomeMail("Skinut pdf", "Naslov", "Text text text text text text text text text text text text text");

        Izvjestaj i = findOne(id);
        try {
            return this.getPdfDocument(i);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getHtml(String id) throws IOException {
//        mailService.sendSomeMail("Skinut html", "Naslov", "Text text text text text text text text text text text text text  ");

        Izvjestaj i = findOne(id);
        return this.getHtmlDocument(i);
    }

    public ResponseEntity<?> getHtmlDocument(Izvjestaj izvjestaj) throws IOException {
        System.out.println("[INFO] " + Xml2HtmlTransformer.class.getSimpleName());

        String xmlString = marshallingService.marshall(izvjestaj, Izvjestaj.class);

        Xml2HtmlTransformer htmlTransformer = new Xml2HtmlTransformer();
        String htmlData = htmlTransformer.generateHTML(xmlString, ZajednickoUtil.IZVESTAJ_XSLT);
        byte[] arr = htmlData.getBytes(StandardCharsets.UTF_8);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentLength(arr.length);
        responseHeaders.setContentType(MediaType.valueOf("application/html"));
        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile.html"));
        return new ResponseEntity<>(arr, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<?> getPdfDocument(Izvjestaj izvjestaj) throws Exception {
        System.out.println("[INFO] " + Xml2HtmlTransformer.class.getSimpleName());

        String xmlString = marshallingService.marshall(izvjestaj, Izvjestaj.class);

        Xml2PdfTransformer pdfTransformer = new Xml2PdfTransformer(ZajednickoUtil.IZVESTAJ_PDF);
        byte[] arr =  pdfTransformer.generatePDF(xmlString);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentLength(arr.length);
        responseHeaders.setContentType(MediaType.valueOf("application/pdf"));
        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile.pdf"));
        return new ResponseEntity<>(arr, responseHeaders, HttpStatus.OK);
    }

    @Override
    public DocDatas getIzvjestajiByUser(String uuid) {
        ResultSetConnection resultsCon = crudrdfRepository.findByPredicateAndObject("metadates", "korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" + uuid);
        ResultSet results = resultsCon.getResultSet();

        DocDatas a = new DocDatas();

        for (ResultSet it = results; it.hasNext(); ) {
            QuerySolution s = it.next();
            Izvjestaj i = findOne(ZajednickoUtil.getIdFromUri(s.get("s").toString()));
            DocDatas.DocData data = new DocDatas.DocData();
            data.setId(i.getId());
            data.setNaziv("Izvjestaj");
            data.setDatum(i.getDatumIzdavanjaIzvjestaja());
            data.setType("izvjestaj");
            data.setUri(ZajednickoUtil.XML_PREFIX + "izvjestaj/" + i.getId());
            data.setIme("");
            data.setPrezime("");
            a.getDocData().add(data);
        }
        resultsCon.closeConnection();
        return a;
    }

    @Override
    public String naprednaPretraga(String query) {

        String where = "?s ?p ?o FILTER(" + query + ")";

        ResultSetConnection resultSetConnection = crudrdfRepository.findWhere("metadates", where);
        String xmlResult = ResultSetFormatter.asXMLString(resultSetConnection.getResultSet());
        resultSetConnection.closeConnection();
        return xmlResult;
    }
}
