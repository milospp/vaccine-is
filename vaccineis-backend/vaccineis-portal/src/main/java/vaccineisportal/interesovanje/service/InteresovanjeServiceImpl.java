package vaccineisportal.interesovanje.service;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vaccineisportal.authentication.service.AuthenticationService;
import vaccineisportal.interesovanje.model.Interesovanje;
import vaccineisportal.interesovanje.repository.InteresovanjeExistRepository;
import vaccineisportal.termin.service.TerminService;
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.model.util.ResultSetConnection;
import zajednicko.repository.CRUDRDFRepository;
import zajednicko.service.MailService;
import zajednicko.service.MarshallingService;
import zajednicko.util.ZajednickoUtil;
import zajednicko.xmlTransformations.Xml2HtmlTransformer;
import zajednicko.xmlTransformations.Xml2PdfTransformer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
public class InteresovanjeServiceImpl implements InteresovanjeService {

    private final InteresovanjeExistRepository interesovanjeExistRepository;
    private final MailService mailService;
    private final CRUDRDFRepository crudrdfRepository;
    private final AuthenticationService authenticationService;
    private final TerminService terminService;
    private final MarshallingService marshallingService;

    @Override
    public Interesovanje create(String xmlString) throws IOException {

        Interesovanje interesovanje = interesovanjeExistRepository.create(xmlString);
        extractMetadataInteresovanje(interesovanje);

        Path path = Paths.get("interesovanje.pdf");
        Files.write(path, (byte[]) Objects.requireNonNull(getPdf(interesovanje.getId()).getBody()));

        try {
            mailService.sendMail(interesovanje.getLicneInformacije().getKontakt().getEmail(),
                    "Исказивање интересовања за вакцинисање против COVID-19",
                    "Успешно сте поднели захтев за вакцинисање против COVID-19.<br>" +
                            "Добићете први слободан термин чим буде био доступан.<br><br>" +
                    "У прилогу можете преузети поднети документ.",
                    "interesovanje.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }

        terminService.terminZaInteresovanje();
        return interesovanje;
    }

    @Override
    public List<Interesovanje> findAll() {
        return interesovanjeExistRepository.findAll();
    }

    @Override
    public Interesovanje findOne(String id) {
        return interesovanjeExistRepository.findOne(id);
    }

    @Override
    public void extractMetadataInteresovanje(Interesovanje interesovanje) {
        Korisnik korisnik = authenticationService.getLoggedInUser();
        LocalDateTime localDateTime = LocalDateTime.now();

        String uriResource = ZajednickoUtil.XML_PREFIX + "interesovanje/" + interesovanje.getId();
        crudrdfRepository.uploadTriplet("rdf", uriResource, "interesovanje_korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" + korisnik.getId());
        crudrdfRepository.uploadTriplet("rdf", ZajednickoUtil.XML_PREFIX + "korisnik/" + korisnik.getId(), "moja_interesovanja", uriResource);
        crudrdfRepository.uploadTriplet("rdf", uriResource, "ceka_od", localDateTime.toString() );

        crudrdfRepository.uploadTriplet("metadates", uriResource, "korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" + korisnik.getId() );
        crudrdfRepository.uploadTriplet("metadates", uriResource, "interesovanje_korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" + korisnik.getId());
        crudrdfRepository.uploadTriplet("metadates", uriResource, "ime", interesovanje.getLicneInformacije().getIme());
        crudrdfRepository.uploadTriplet("metadates", uriResource, "prezime", interesovanje.getLicneInformacije().getPrezime());
        crudrdfRepository.uploadTriplet("metadates", uriResource, "jmbg", interesovanje.getLicneInformacije().getJmbg());
        crudrdfRepository.uploadTriplet("metadates", uriResource, "datum", LocalDateTime.now().toString());

    }

    @Override
    public ResponseEntity<?> getPdf(String id) throws IOException {
//        mailService.sendMail("Skinut pdf", "Naslov", "Text text text text text text text text text text text text text");

        Interesovanje i = findOne(id);
        try {
            return this.getPdfDocument(i);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
//        return getDocument("pdf");
    }

    @Override
    public ResponseEntity<?> getHtml(String id) throws IOException {
//        mailService.sendMail("Skinut pdf", "Naslov", "Text text text text text text text text text text text text text  ");

        Interesovanje i = findOne(id);
        return this.getHtmlDocument(i);
    }

    @Override
    public String getRdfXml(String uuid) {
        ResultSetConnection resultSetConnection = crudrdfRepository.findBySubject("metadates", ZajednickoUtil.XML_PREFIX + "interesovanje/" + uuid);
        ResultSet resultSet = resultSetConnection.getResultSet();
        String asXml = ResultSetFormatter.asXMLString(resultSet);
        resultSetConnection.closeConnection();

        return asXml;

    }

    @Override
    public String getRdfJson(String uuid) {
        ResultSetConnection resultSetConnection = crudrdfRepository.findBySubject("metadates", ZajednickoUtil.XML_PREFIX + "interesovanje/" + uuid);
        ResultSet resultSet = resultSetConnection.getResultSet();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(out, resultSet);
        resultSetConnection.closeConnection();
        String finalString = out.toString();

        return finalString;
    }


//    public static ResponseEntity<?> getDocumentUUID(String uuid) throws IOException {
//        File file = new File("./src/main/resources/files/interesovanje." + type);
//
//
//        byte[] arr = FileUtils.readFileToByteArray(file);
//        String ret = new String(arr, StandardCharsets.UTF_8);
//
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.setContentLength(arr.length);
//        responseHeaders.setContentType(MediaType.valueOf("application/" + type));
//        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile." + type));
//        return new ResponseEntity<>(arr, responseHeaders, HttpStatus.OK);
//    }

    public ResponseEntity<?> getHtmlDocument(Interesovanje interesovanje) throws IOException {
        System.out.println("[INFO] " + Xml2HtmlTransformer.class.getSimpleName());

        String xmlString = marshallingService.marshall(interesovanje, Interesovanje.class);

        Xml2HtmlTransformer htmlTransformer = new Xml2HtmlTransformer();
        String htmlData = htmlTransformer.generateHTML(xmlString, ZajednickoUtil.INTERESOVANJE_XSLT);
        byte[] arr = htmlData.getBytes(StandardCharsets.UTF_8);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentLength(arr.length);
        responseHeaders.setContentType(MediaType.valueOf("application/html"));
        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile.html"));
        return new ResponseEntity<>(arr, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<?> getPdfDocument(Interesovanje interesovanje) throws Exception {
        System.out.println("[INFO] " + Xml2HtmlTransformer.class.getSimpleName());

        String xmlString = marshallingService.marshall(interesovanje, Interesovanje.class);

        Xml2PdfTransformer pdfTransformer = new Xml2PdfTransformer(ZajednickoUtil.INTERESOVANJE_PDF);
        byte[] arr =  pdfTransformer.generatePDF(xmlString);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentLength(arr.length);
        responseHeaders.setContentType(MediaType.valueOf("application/pdf"));
        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile.pdf"));
        return new ResponseEntity<>(arr, responseHeaders, HttpStatus.OK);
    }


    public static ResponseEntity<?> getDocument(String type) throws IOException {
        File file = new File("./src/main/resources/files/interesovanje." + type);
        byte[] arr = FileUtils.readFileToByteArray(file);
        String ret = new String(arr, StandardCharsets.UTF_8);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentLength(arr.length);
        responseHeaders.setContentType(MediaType.valueOf("application/" + type));
        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile." + type));
        return new ResponseEntity<>(arr, responseHeaders, HttpStatus.OK);
    }

    @Override
    public void obradiTermine() {

    }

    @Override
    public DocDatas getInteresovanjaByUser(String uuid) {
        ResultSetConnection resultsCon = crudrdfRepository.findByPredicateAndObject("rdf", "interesovanje_korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" +uuid);
        ResultSet results = resultsCon.getResultSet();

        DocDatas a = new DocDatas();

        for (ResultSet it = results; it.hasNext(); ) {
            QuerySolution s = it.next();
            Interesovanje i = findOne(ZajednickoUtil.getIdFromUri(s.get("s").toString()));
            DocDatas.DocData data = new DocDatas.DocData();
            data.setId(i.getId());
            data.setNaziv("Interesovanje");
            data.setDatum(i.getDatum());
            data.setType("interesovanje");
            data.setUri(ZajednickoUtil.XML_PREFIX + "interesovanje/" + i.getId());
            data.setIme(i.getLicneInformacije().getIme());
            data.setPrezime(i.getLicneInformacije().getPrezime());
            a.getDocData().add(data);
        }

        resultsCon.closeConnection();

        return a;
    }
}
