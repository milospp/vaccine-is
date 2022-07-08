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
import vaccineisportal.obrazac_saglasnosti.model.Saglasnost;
import vaccineisportal.zahtev_sertifikata.model.ZahtevZaSertifikatStatus;
import vaccineisportal.zahtev_sertifikata.model.Zahtjev;
import vaccineisportal.zahtev_sertifikata.repository.ZahtevSertifikataExistRepository;
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.model.util.ResultSetConnection;
import zajednicko.repository.CRUDRDFRepository;
import zajednicko.service.MailService;
import zajednicko.service.MarshallingService;
import zajednicko.util.ZajednickoUtil;
import zajednicko.xmlTransformations.Xml2HtmlTransformer;
import zajednicko.xmlTransformations.Xml2PdfTransformer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class ZahtevSertifikataServiceImpl implements ZahtevSertifikataService {

    private final ZahtevSertifikataExistRepository zahtevSertifikataExistRepository;
    private final CRUDRDFRepository crudrdfRepository;
    private final AuthenticationService authenticationService;
    private final MailService mailService;
    private final MarshallingService marshallingService;

    @Override
    public Zahtjev create(String xmlString) {
        Zahtjev zahtjev = zahtevSertifikataExistRepository.create(xmlString);
        extractMetadata(zahtjev);

        return zahtjev;
    }

    @Override
    public List<Zahtjev> findZahteviByStatusPodnet() {
        ResultSetConnection rsCon = crudrdfRepository.findByPredicateAndObject("rdf", "status", String.valueOf(ZahtevZaSertifikatStatus.PODNET));
        ResultSet rs = rsCon.getResultSet();

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

        rsCon.closeConnection();

        return retVal;
    }

    @Override
    public void extractMetadata(Zahtjev zahtevSertifikata) {
        Korisnik korisnik = authenticationService.getLoggedInUser();
        LocalDateTime localDateTime = LocalDateTime.now();
        crudrdfRepository.uploadTriplet("rdf", ZajednickoUtil.XML_PREFIX + "zahtev/" + zahtevSertifikata.getId(), "korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" + korisnik.getId() );
        crudrdfRepository.uploadTriplet("rdf", ZajednickoUtil.XML_PREFIX + "zahtev/" + zahtevSertifikata.getId(), "korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" + korisnik.getId() );
        crudrdfRepository.uploadTriplet("rdf", ZajednickoUtil.XML_PREFIX + "zahtev/" + zahtevSertifikata.getId(), "status", ZahtevZaSertifikatStatus.PODNET.name());
        crudrdfRepository.uploadTriplet("rdf", ZajednickoUtil.XML_PREFIX + "zahtev/" + zahtevSertifikata.getId(), "zahtev_korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" + korisnik.getId());

        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "zahtev/" + zahtevSertifikata.getId(), "korisnik", korisnik.getId() );
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "zahtev/" + zahtevSertifikata.getId(), "imePodnosioca", zahtevSertifikata.getPodnosilac().getIme() );
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "zahtev/" + zahtevSertifikata.getId(), "prezimePodnosioca", zahtevSertifikata.getPodnosilac().getPrezime() );
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "zahtev/" + zahtevSertifikata.getId(), "datumRodjenjaPodnosioca", String.valueOf(zahtevSertifikata.getPodnosilac().getDatumRodjenja()));
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "zahtev/" + zahtevSertifikata.getId(), "polPodnosioca", String.valueOf(zahtevSertifikata.getPodnosilac().getPol()));
        crudrdfRepository.uploadTriplet("metadates", ZajednickoUtil.XML_PREFIX + "zahtev/" + zahtevSertifikata.getId(), "datumIzdavanja", localDateTime.toString());
    }

    @Override
    public ResponseEntity<?> getPdf(String id) throws IOException {
//        mailService.sendMail("Skinut pdf", "Naslov", "Text text text text text text text text text text text text text");

        Zahtjev i = findOne(id);
        try {
            return this.getPdfDocument(i);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getHtml(String id) throws IOException {
//        mailService.sendMail("Skinut html", "Naslov", "Text text text text text text text text text text text text text  ");

        Zahtjev i = findOne(id);
        return this.getHtmlDocument(i);
    }

    public ResponseEntity<?> getHtmlDocument(Zahtjev zahtjev) throws IOException {
        System.out.println("[INFO] " + Xml2HtmlTransformer.class.getSimpleName());

        String xmlString = marshallingService.marshall(zahtjev, Zahtjev.class);

        Xml2HtmlTransformer htmlTransformer = new Xml2HtmlTransformer();
        String htmlData = htmlTransformer.generateHTML(xmlString, ZajednickoUtil.ZAHTEV_ZA_ZS_XSLT);
        byte[] arr = htmlData.getBytes(StandardCharsets.UTF_8);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentLength(arr.length);
        responseHeaders.setContentType(MediaType.valueOf("application/html"));
        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile.html"));
        return new ResponseEntity<>(arr, responseHeaders, HttpStatus.OK);
    }

    public ResponseEntity<?> getPdfDocument(Zahtjev saglasnost) throws Exception {
        System.out.println("[INFO] " + Xml2HtmlTransformer.class.getSimpleName());

        String xmlString = marshallingService.marshall(saglasnost, Zahtjev.class);

        Xml2PdfTransformer pdfTransformer = new Xml2PdfTransformer(ZajednickoUtil.ZAHTEV_ZA_ZS_PDF);
        byte[] arr =  pdfTransformer.generatePDF(xmlString);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentLength(arr.length);
        responseHeaders.setContentType(MediaType.valueOf("application/pdf"));
        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile.pdf"));
        return new ResponseEntity<>(arr, responseHeaders, HttpStatus.OK);
    }

    @Override
    public DocDatas getZahtjeviByUser(String uuid) {
            ResultSetConnection resultsCon = crudrdfRepository.findByPredicateAndObject("rdf", "zahtev_korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" + uuid);
            ResultSet results = resultsCon.getResultSet();
        try {

            DocDatas a = new DocDatas();

            for (ResultSet it = results; it.hasNext(); ) {
                QuerySolution s = it.next();
                Zahtjev i = findOne(ZajednickoUtil.getIdFromUri(s.get("s").toString()));
                DocDatas.DocData data = new DocDatas.DocData();
                data.setId(i.getId());
                data.setNaziv("Zahtjev");
                data.setDatum(i.getMjestoDatum().getDatum());
                data.setType("zahtjev");
                data.setUri(ZajednickoUtil.XML_PREFIX + "zahtev/" + i.getId());
                data.setIme(i.getPodnosilac().getIme());
                data.setPrezime(i.getPodnosilac().getPrezime());
                a.getDocData().add(data);
            }

            resultsCon.closeConnection();

            return a;
        } finally {
            resultsCon.closeConnection();
        }
    }

    @Override
    public void odbijZahtev(String uuid) {
//        String korisnik = crudrdfRepository.findFirstBySubjectAndPred("rdf", ZajednickoUtil.XML_PREFIX + "zahtev/" + uuid, "status");
//        List<String> splits = Arrays.asList(korisnik.split("/"));
//        String korisnikId = splits.get(splits.size() - 1);

        crudrdfRepository.deleteQuery("rdf", "?s <" + ZajednickoUtil.XML_PREFIX + "zahtev/" + uuid + ">  <" + ZajednickoUtil.RDF_PREDICATE + "status> ?o" );
        crudrdfRepository.uploadTriplet("rdf", ZajednickoUtil.XML_PREFIX + "zahtev/" + uuid, "status", ZahtevZaSertifikatStatus.ODBIJEN.name());

    }

    @Override
    public Zahtjev findOne(String id) {
        return zahtevSertifikataExistRepository.findOne(id);
    }
}
