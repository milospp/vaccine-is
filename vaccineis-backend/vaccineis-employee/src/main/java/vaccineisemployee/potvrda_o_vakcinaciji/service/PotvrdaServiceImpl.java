package vaccineisemployee.potvrda_o_vakcinaciji.service;

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
import vaccineisemployee.potvrda_o_vakcinaciji.model.PotvrdaVakcinacije;
import vaccineisemployee.potvrda_o_vakcinaciji.repository.PotvrdaOVakcinacijiExistRepository;
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.model.util.ResultSetConnection;
import zajednicko.repository.CRUDRDFRepository;
import zajednicko.service.MailService;
import zajednicko.service.MarshallingService;
import zajednicko.service.UserService;
import zajednicko.util.ZajednickoUtil;
import zajednicko.xmlTransformations.Xml2HtmlTransformer;
import zajednicko.xmlTransformations.Xml2PdfTransformer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;

@AllArgsConstructor
@Service
public class PotvrdaServiceImpl implements PotvrdaService{
    private final PotvrdaOVakcinacijiExistRepository existRepository;
    private final AuthenticationService authenticationService;
    private final CRUDRDFRepository crudrdfRepository;
    private final MarshallingService marshallingService;
    private final MailService mailService;
    private final UserService userService;

    @Override
    public PotvrdaVakcinacije create(String xmlString) throws IOException {
        PotvrdaVakcinacije potvrdaVakcinacije = existRepository.create(xmlString);
        extractMetadataPotvrda(potvrdaVakcinacije);


        Path path = Paths.get("potvrda.pdf");
        Files.write(path, (byte[]) Objects.requireNonNull(getPdf(potvrdaVakcinacije.getId()).getBody()));

        try {
            mailService.sendMail("gradjanin@maildrop.cc",
                    "Потврда за вакцинисање против COVID-19",
                    "Успешно сте добили потврду за вакцинисање против COVID-19.<br><br>" +
                            "У прилогу можете преузети поднети документ.",
                    "potvrda.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return potvrdaVakcinacije;
    }

    @Override
    public PotvrdaVakcinacije findOne(String id) {
        return existRepository.findOne(id);
    }

    @Override
    public void extractMetadataPotvrda(PotvrdaVakcinacije potvrdaVakcinacije) {
        LocalDateTime localDateTime = LocalDateTime.now();

        cleanLastJmbg(potvrdaVakcinacije.getPodaciVakcinisanog().getJmbg());

        crudrdfRepository.deleteQuery("rdf", "?s <" + ZajednickoUtil.RDF_PREDICATE + "potvrda_korisnik_last>  <" + ZajednickoUtil.XML_PREFIX + "korisnik/" + potvrdaVakcinacije.getPodaciVakcinisanog().getId() + ">");

        crudrdfRepository.uploadTriplet("rdf", ZajednickoUtil.XML_PREFIX + "potvrda/" + potvrdaVakcinacije.getId(), "korisnik", localDateTime.toString());
        crudrdfRepository.uploadTriplet("rdf", ZajednickoUtil.XML_PREFIX + "potvrda/" + potvrdaVakcinacije.getId(), "potvrda_jmbg", potvrdaVakcinacije.getPodaciVakcinisanog().getJmbg());
        crudrdfRepository.uploadTriplet("rdf", ZajednickoUtil.XML_PREFIX + "potvrda/" + potvrdaVakcinacije.getId(), "poslednja_potvrda_jmbg", potvrdaVakcinacije.getPodaciVakcinisanog().getJmbg());
        crudrdfRepository.uploadTriplet("rdf", ZajednickoUtil.XML_PREFIX + "potvrda/" + potvrdaVakcinacije.getId(), "potvrda_korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" + potvrdaVakcinacije.getPodaciVakcinisanog().getId() );
        crudrdfRepository.uploadTriplet("rdf", ZajednickoUtil.XML_PREFIX + "potvrda/" + potvrdaVakcinacije.getId(), "potvrda_korisnik_last", ZajednickoUtil.XML_PREFIX + "korisnik/" + potvrdaVakcinacije.getPodaciVakcinisanog().getId() );
//        crudrdfRepository.uploadTriplet("rdf", ZajednickoUtil.XML_PREFIX + "potvrda/" + potvrdaVakcinacije.getId(), "korisnik_potvrda", ZajednickoUtil.XML_PREFIX + "korisnik/" + potvrdaVakcinacije.getPodaciVakcinisanog().getId());
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "potvrda/" + potvrdaVakcinacije.getId(), "korisnik", potvrdaVakcinacije.getPodaciVakcinisanog().getId() );
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "potvrda/" + potvrdaVakcinacije.getId(), "ime", potvrdaVakcinacije.getPodaciVakcinisanog().getIme() );
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "potvrda/" + potvrdaVakcinacije.getId(), "prezime", potvrdaVakcinacije.getPodaciVakcinisanog().getPrezime() );
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "potvrda/" + potvrdaVakcinacije.getId(), "datum", LocalDateTime.now().toString() );
    }

    @Override
    public ResponseEntity<?> getPdf(String id) throws IOException {
//        mailService.sendSomeMail("Skinut pdf", "Naslov", "Text text text text text text text text text text text text text");

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
//        mailService.sendSomeMail("Skinut html", "Naslov", "Text text text text text text text text text text text text text  ");

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
        byte[] arr = pdfTransformer.generatePDF(xmlString);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentLength(arr.length);
        responseHeaders.setContentType(MediaType.valueOf("application/pdf"));
        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile.pdf"));
        return new ResponseEntity<>(arr, responseHeaders, HttpStatus.OK);
    }

    @Override
    public PotvrdaVakcinacije getPoslednjuPotvrdu(String uuid) {

        ResultSetConnection resultsCon = crudrdfRepository.findByPredicateAndObject("rdf", "potvrda_jmbg", ZajednickoUtil.XML_PREFIX + "korisnik/" + uuid);
        ResultSet results = resultsCon.getResultSet();

        for (ResultSet it = results; it.hasNext(); ) {
            QuerySolution s = it.next();
            PotvrdaVakcinacije i = findOne(ZajednickoUtil.getIdFromUri(s.get("s").toString()));
            resultsCon.closeConnection();
            return i;
        }
        resultsCon.closeConnection();

        return null;
    }

    private void cleanLastJmbg(String uuid){
        ResultSetConnection resultsCon = crudrdfRepository.findByPredicateAndObject("rdf", "potvrda_jmbg", ZajednickoUtil.XML_PREFIX + "korisnik/" + uuid);
        ResultSet results = resultsCon.getResultSet();

        for (ResultSet it = results; it.hasNext(); ) {
            QuerySolution s = it.next();
            String sub = s.get("s").toString();
            crudrdfRepository.deleteTriplet("rdf", sub, "poslednja_potvrda_jmbg", uuid);
        }

        resultsCon.closeConnection();

    }

    @Override
    public DocDatas getPotvrdeByUser(String uuid) {
        ResultSetConnection resultsCon = crudrdfRepository.findByPredicateAndObject("rdf", "potvrda_korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" + uuid);
        ResultSet results = resultsCon.getResultSet();
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

        resultsCon.closeConnection();
        return a;
    }

    @Override
    public String getRdfXml(String uuid) {
        ResultSetConnection resultSetConnection = crudrdfRepository.findBySubject("metadates", ZajednickoUtil.XML_PREFIX + "potvrda/" + uuid);
        ResultSet resultSet = resultSetConnection.getResultSet();
        String asXml = ResultSetFormatter.asXMLString(resultSet);
        resultSetConnection.closeConnection();

        return asXml;
    }

    @Override
    public String getRdfJson(String uuid) {
        ResultSetConnection resultSetConnection = crudrdfRepository.findBySubject("metadates", ZajednickoUtil.XML_PREFIX + "potvrda/" + uuid);
        ResultSet resultSet = resultSetConnection.getResultSet();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(out, resultSet);
        resultSetConnection.closeConnection();
        String finalString = out.toString();

        return finalString;
    }
}
