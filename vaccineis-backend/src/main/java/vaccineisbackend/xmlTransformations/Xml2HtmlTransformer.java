package vaccineisbackend.xmlTransformations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import net.sf.saxon.TransformerFactoryImpl;
import com.itextpdf.text.DocumentException;

public class Xml2HtmlTransformer {
    private static final DocumentBuilderFactory documentFactory;

    private static final TransformerFactory transformerFactory;

    public static String INPUT_FILE = "./src/main/resources/data/xml/zahtjev-za-sertifikat.xml";

    public static String XSL_FILE = "./src/main/resources/data/xslt/zahtjev-za-sertifikat.xsl";

    public static String HTML_FILE = "./src/main/resources/data/gen/itext/zahtjev-za-sertifikat.html";

    static {

        /* Inicijalizacija DOM fabrike */
        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        /* Inicijalizacija Transformer fabrike */
        transformerFactory = TransformerFactory.newInstance();

    }

    public Xml2HtmlTransformer(String xmlInputFile, String xslInputFile, String htmlOutFile) {
        INPUT_FILE = xmlInputFile;
        XSL_FILE = xslInputFile;
        HTML_FILE = htmlOutFile;
    }

    public org.w3c.dom.Document buildDocument(String filePath) {

        org.w3c.dom.Document document = null;
        try {

            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            document = builder.parse(new File(filePath));

            if (document != null)
                System.out.println("[INFO] File parsed with no errors.");
            else
                System.out.println("[WARN] Document is null.");

        } catch (Exception e) {
            return null;

        }

        return document;
    }

    public void generateHTML(String xmlPath, String xslPath) throws FileNotFoundException {

        try {

            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File(xslPath));
            TransformerFactory factory = new TransformerFactoryImpl();
            Transformer transformer = factory.newTransformer(transformSource); //transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            // Transform DOM to HTML
            DOMSource source = new DOMSource(buildDocument(xmlPath));
            StreamResult result = new StreamResult(new FileOutputStream(HTML_FILE));
            transformer.transform(source, result);

        } catch (TransformerFactoryConfigurationError | TransformerException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException, DocumentException {

        System.out.println("[INFO] " + Xml2HtmlTransformer.class.getSimpleName());

        Xml2HtmlTransformer pdfTransformer = new Xml2HtmlTransformer("./src/main/resources/data/xml/potvrda-o-vakcinaciji.xml", "./src/main/resources/data/xslt/potvrda-o-vakcinaciji.xsl","./src/main/resources/data/gen/itext/potvrda-o-vakcinaciji.html");

        pdfTransformer.generateHTML(INPUT_FILE, XSL_FILE);

        System.out.println("[INFO] File \"" + HTML_FILE + "\" generated successfully.");
        System.out.println("[INFO] End.");
    }

    /*
    "./src/main/resources/data/xml/zahtjev-za-sertifikat.xml", "./src/main/resources/data/xslt/zahtjev-za-sertifikat.xsl","./src/main/resources/data/gen/itext/zahtjev-za-sertifikat.html"
    "./src/main/resources/data/xml/interesovanje.xml", "./src/main/resources/data/xslt/interesovanje.xsl","./src/main/resources/data/gen/itext/interesovanje.html"
    "./src/main/resources/data/xml/digitalni-sertifikat.xml", "./src/main/resources/data/xslt/digitalni-sertifikat.xsl","./src/main/resources/data/gen/itext/digitalni-sertifikat.html"
    "./src/main/resources/data/xml/izvjestaj-o-imunizaciji.xml", "./src/main/resources/data/xslt/izvjestaj-o-imunizaciji.xsl","./src/main/resources/data/gen/itext/izvjestaj-o-imunizaciji.html"
    "./src/main/resources/data/xml/obrazac-o-saglasnosti.xml", "./src/main/resources/data/xslt/obrazac-o-saglasnosti.xsl","./src/main/resources/data/gen/itext/obrazac-o-saglasnosti.html"
    "./src/main/resources/data/xml/potvrda-o-vakcinaciji.xml", "./src/main/resources/data/xslt/potvrda-o-vakcinaciji.xsl","./src/main/resources/data/gen/itext/potvrda-o-vakcinaciji.html"
     */
}
