package vaccineisportal.obrazac_saglasnosti.service;

import lombok.AllArgsConstructor;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import vaccineisportal.authentication.service.AuthenticationService;
import vaccineisportal.obrazac_saglasnosti.model.Saglasnost;
import vaccineisportal.obrazac_saglasnosti.repository.ObrazacSaglasnostiExistRepository;
import vaccineisportal.potvrda_vakcinacije_bridge.model.CTvakcinacijaPodaciPotvrda;
import vaccineisportal.potvrda_vakcinacije_bridge.model.PotvrdaVakcinacije;
import vaccineisportal.util.dto.ListaVakcinaDTO;
import vaccineisportal.util.dto.VakcinaDTO;
import zajednicko.exception.BadRequestException;
import zajednicko.model.CTpodaciVakcinisanog;
import zajednicko.model.CTvakcinacijaPodaci;
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.model.util.ResultSetConnection;
import zajednicko.repository.CRUDRDFRepository;
import zajednicko.service.MailService;
import zajednicko.service.MarshallingService;
import zajednicko.util.ZajednickoUtil;
import zajednicko.xmlTransformations.Xml2HtmlTransformer;
import zajednicko.xmlTransformations.Xml2PdfTransformer;

import javax.xml.bind.JAXB;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
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
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

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
        Korisnik korisnik = authenticationService.getLoggedInUser();

        xmlString = xmlString.replace("<podaciPacijenta>", "<podaciPacijenta><id xmlns=\"http://www.ftn.uns.ac.rs/zajednicka\">" + korisnik.getId() + "</id>");
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
        Saglasnost saglasnost = obrazacSaglasnostiExistRepository.update(id, xmlString);
        try {
            PotvrdaVakcinacije createPotvrda = createPotvrda(saglasnost);

            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8081/api/potvrda";

//            StringWriter sw = new StringWriter();
//
//            JAXB.marshal(createPotvrda, sw);
//            String xmlStringPotvrda = sw.toString();

//            String str = marshallingService.marshall(createPotvrda, PotvrdaVakcinacije.class);

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(new MediaType("application","xml"));
            HttpEntity<PotvrdaVakcinacije> requestEntity = new HttpEntity<PotvrdaVakcinacije>(createPotvrda, requestHeaders);

            ResponseEntity<PotvrdaVakcinacije> entity = restTemplate.postForEntity(url, requestEntity, PotvrdaVakcinacije.class);



        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }


        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/api/vakcine";
        ResponseEntity<ListaVakcinaDTO> entity = restTemplate.getForEntity(url, ListaVakcinaDTO.class);

        var vakcinacija = saglasnost.getEvidencijaVakcinacije().getPodaciVakcinacija().getVakcinacija();
        if (entity.getBody() == null)
            throw new BadRequestException("Body is null");

        Optional<VakcinaDTO> vakcina = entity.getBody().getVakcine().stream().filter(v -> v.getNaziv().equals(vakcinacija.get(vakcinacija.size()-1).getNazivVakcine())).findFirst();
        if (vakcina.isEmpty())
            throw new BadRequestException("Vakcina is empty");

        restTemplate.put(url + "/smanjiKolicinu", vakcina.get());
        return saglasnost;
    }

    private PotvrdaVakcinacije createPotvrda(Saglasnost saglasnost) throws DatatypeConfigurationException {
//        TODO GET LAST

        PotvrdaVakcinacije potvrdaVakcinacije = new PotvrdaVakcinacije();

        CTpodaciVakcinisanog cTpodaciVakcinisanog = new CTpodaciVakcinisanog();
        cTpodaciVakcinisanog.setId(saglasnost.getPodaciPacijenta().getId());
        cTpodaciVakcinisanog.setIme(saglasnost.getPodaciPacijenta().getIme());
        cTpodaciVakcinisanog.setPrezime(saglasnost.getPodaciPacijenta().getPrezime());
        cTpodaciVakcinisanog.setDatumRodjenja(saglasnost.getPodaciPacijenta().getDatumRodjenja());
        cTpodaciVakcinisanog.setJmbg(saglasnost.getDrzavljanstvo().getJmbg());
        cTpodaciVakcinisanog.setPol(saglasnost.getPodaciPacijenta().getPol());
        potvrdaVakcinacije.setPodaciVakcinisanog(cTpodaciVakcinisanog);
        potvrdaVakcinacije.setSifra(getRandom8DIgit());
        potvrdaVakcinacije.setQRKod(String.valueOf(UUID.randomUUID()));

//        GregorianCalendar c = new GregorianCalendar();
//        c.setTimeZone(null);
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar("2022-07-19");

        CTvakcinacijaPodaciPotvrda cTvakcinacijaPodaciPotvrda = new CTvakcinacijaPodaciPotvrda();
        cTvakcinacijaPodaciPotvrda.setDatumIzdavanjaPotvrde(date2);

        List<CTvakcinacijaPodaci> cTvakcinacijaPodaciList = new ArrayList<>();

        CTvakcinacijaPodaci cTvakcinacijaPodaci = new CTvakcinacijaPodaci();
        cTvakcinacijaPodaci.setId(String.valueOf(UUID.randomUUID()));
        cTvakcinacijaPodaci.setBrojDoze(1);
        cTvakcinacijaPodaci.setDatumDavanjaDoze(saglasnost.getEvidencijaVakcinacije().getPodaciVakcinacija().getVakcinacija().get(0).getDatumDavanjaVakcine());
        cTvakcinacijaPodaci.setZdravstvenaUstanova(saglasnost.getEvidencijaVakcinacije().getZdravstvenaUstanova().getNazivZdravstveneUstanove());
        cTvakcinacijaPodaci.setNazivVakcine(saglasnost.getEvidencijaVakcinacije().getPodaciVakcinacija().getVakcinacija().stream().findFirst().get().getNazivVakcine());

        cTvakcinacijaPodaciList.add(cTvakcinacijaPodaci);

        cTvakcinacijaPodaciPotvrda.setVakcinaPodaci(cTvakcinacijaPodaciList);
        potvrdaVakcinacije.setPodaciVakcinacije(cTvakcinacijaPodaciPotvrda);

        return potvrdaVakcinacije;
    }


    private String getRandom8DIgit() {
        Random rnd = new Random();
        int number = rnd. nextInt(99999999 - 90000000) + 90000000;
        return "" + number;

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

        crudrdfRepository.deleteQuery("rdf", "?s <" + ZajednickoUtil.RDF_PREDICATE + "termin_ceka_potvrdu>  <" + ZajednickoUtil.XML_PREFIX + "korisnik/" + korisnik.getId() + ">");


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
