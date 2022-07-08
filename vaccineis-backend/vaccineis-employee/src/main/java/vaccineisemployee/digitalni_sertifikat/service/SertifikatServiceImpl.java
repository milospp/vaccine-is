package vaccineisemployee.digitalni_sertifikat.service;

import lombok.AllArgsConstructor;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.jena.query.ResultSetFormatter;
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
import zajednicko.model.util.ResultSetConnection;
import zajednicko.repository.CRUDRDFRepository;
import zajednicko.service.MailService;
import zajednicko.service.MarshallingService;
import zajednicko.util.ZajednickoUtil;
import zajednicko.xmlTransformations.Xml2HtmlTransformer;
import zajednicko.xmlTransformations.Xml2PdfTransformer;
import vaccineisemployee.digitalni_sertifikat.model.CTinfoSertifikata;
import vaccineisemployee.digitalni_sertifikat.model.CTtest;
import vaccineisemployee.digitalni_sertifikat.model.CTvakcinacijaPodaciSertifikat;
import vaccineisemployee.potvrda_o_vakcinaciji.model.PotvrdaVakcinacije;
import vaccineisemployee.potvrda_o_vakcinaciji.service.PotvrdaService;
import zajednicko.model.CTadresa;
import zajednicko.model.CTpodnosilacZahtjeva;
import zajednicko.model.CTvakcinacijaPodaci;
import zajednicko.model.STpol;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

@AllArgsConstructor
@Service
public class SertifikatServiceImpl implements SertifikatService{
    private final DigitalniSertifikatExistRepository digitalniSertifikatExistRepository;
    private final CRUDRDFRepository crudrdfRepository;
    private final AuthenticationService authenticationService;
    private final MarshallingService marshallingService;
    private final MailService mailService;
    private final PotvrdaService potvrdaService;

    @Override
    public ZeleniSertifikat generisiSertifikat(String uuidZahteva) {
        CTinfoSertifikata cTinfoSertifikata = new CTinfoSertifikata();
        cTinfoSertifikata.setIzdavacSertifikata("FTN Novi Sad");
        cTinfoSertifikata.setDigitalniPotpis(getPotpis());

        ZeleniSertifikat zs = new ZeleniSertifikat();
        zs.setBrojSertifikata(RandomStringUtils.randomNumeric(10));
        zs.setDatumVrijemeIzdavanja(ZajednickoUtil.localDateToGregorian(LocalDate.now()));
        zs.setInfoSertifikata(cTinfoSertifikata);
        zs.setTestovi(getTestovi());
        zs.setQrKod("https://api.qrserver.com/v1/create-qr-code/?size=150x150&amp;data=http://localhost:9092/дигитални/" + zs.getBrojSertifikata());
        zs.setPodaciVakcinisanog(dobaviPodnosioca(uuidZahteva));
        zs.setVakcinacija(dobaviVakcinacije(zs.getPodaciVakcinisanog().getJmbg()));

        return zs;
    }

    private CTinfoSertifikata.DigitalniPotpis getPotpis() {
        CTinfoSertifikata.DigitalniPotpis dp = new CTinfoSertifikata.DigitalniPotpis();
        dp.setNazivUstanove("FTN Novi Sad");
        dp.setAdresaUstanove(new CTadresa("Dr Sime Miloševića", "2"));
        dp.setDatumIzdavanja(ZajednickoUtil.localDateToGregorian(LocalDate.of(1960,1,7)));
        return dp;
    }

    private CTpodnosilacZahtjeva dobaviPodnosioca(String uuidZahteva) {

        //TODO: Povezi se sa drugim bekendom i dobavi

        CTpodnosilacZahtjeva ct = new CTpodnosilacZahtjeva();
        ct.setId(UUID.randomUUID().toString());
        ct.setJmbg("2501000772026");
        ct.setBrojPasosa("123123123");
        ct.setDatumRodjenja(ZajednickoUtil.localDateToGregorian(LocalDate.of(2000,1,25)));
        ct.setIme("Milos");
        ct.setPrezime("Popovic");
        ct.setPol(STpol.МУШКО);

        return ct;
    }

    private CTvakcinacijaPodaciSertifikat dobaviVakcinacije(String jmbg) {
        CTvakcinacijaPodaciSertifikat podaci = new CTvakcinacijaPodaciSertifikat();

        PotvrdaVakcinacije vakcinacija = potvrdaService.getPoslednjuPotvrdu(jmbg);
        for (CTvakcinacijaPodaci vakcPodaci : vakcinacija.getPodaciVakcinacije().getVakcinaPodaci()){
            CTvakcinacijaPodaciSertifikat.Doza doza = new CTvakcinacijaPodaciSertifikat.Doza();
            doza.setProizvodjac(vakcPodaci.getNazivVakcine().value());
            doza.setSerijaVakcine(RandomStringUtils.randomAlphanumeric(3));
            doza.setVakcinaPodaci(vakcPodaci);
            podaci.getDoza().add(doza);
        }

        return podaci;
    }

    private CTtest getTestovi() {
        CTtest cTtest = new CTtest();

        CTtest.Test test1 = new CTtest.Test();
        test1.setTipTesta("N/A");
        test1.setLaboratorija("N/A");
        test1.setDatumVremeUzorkovanja(ZajednickoUtil.localDateToGregorian(LocalDate.now()));
        test1.setDatumVremeRezultata(ZajednickoUtil.localDateToGregorian(LocalDate.now()));
        test1.setProizvodjacTesta("N/A");
        test1.setRezultat("N/A");
        test1.setVrstaUzorka("N/A");

        return cTtest;
    }

    @Override
    public ResponseEntity<?> getPdf(String id) throws IOException {
//        mailService.sendSomeMail("Skinut pdf", "Naslov", "Text text text text text text text text text text text text text");

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
//        mailService.sendSomeMail("Skinut html", "Naslov", "Text text text text text text text text text text text text text  ");

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
        ResultSetConnection resultsCon = crudrdfRepository.findByPredicateAndObject("rdf", "korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" + uuid);
        ResultSet results = resultsCon.getResultSet();

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
        resultsCon.closeConnection();
        return a;
    }

    @Override
    public ZeleniSertifikat findOne(String id) {
        return digitalniSertifikatExistRepository.findOne(id);
    }

    @Override
    public String getRdfXml(String uuid) {
        ResultSetConnection resultSetConnection = crudrdfRepository.findBySubject("metadates", ZajednickoUtil.XML_PREFIX + "digitalni-sertifikat/" + uuid);
        ResultSet resultSet = resultSetConnection.getResultSet();
        String asXml = ResultSetFormatter.asXMLString(resultSet);
        resultSetConnection.closeConnection();

        return asXml;    }

    @Override
    public String getRdfJson(String uuid) {
        ResultSetConnection resultSetConnection = crudrdfRepository.findBySubject("metadates", ZajednickoUtil.XML_PREFIX + "digitalni-sertifikat/" + uuid);
        ResultSet resultSet = resultSetConnection.getResultSet();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(out, resultSet);
        resultSetConnection.closeConnection();
        String finalString = out.toString();

        return finalString;
    }

    @Override
    public void extractMetadata(ZeleniSertifikat zeleniSertifikat) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        Korisnik korisnik = authenticationService.getLoggedInUser();
        crudrdfRepository.uploadTriplet("rdf", ZajednickoUtil.XML_PREFIX + "digitalni-sertifikat/" + zeleniSertifikat.getId(), "korisnik", ZajednickoUtil.XML_PREFIX + "korisnik/" + korisnik.getId() );
    }
}
