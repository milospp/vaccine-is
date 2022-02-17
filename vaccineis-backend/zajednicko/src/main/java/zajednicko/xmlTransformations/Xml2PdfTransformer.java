package zajednicko.xmlTransformations;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;

import net.sf.saxon.TransformerFactoryImpl;

public class Xml2PdfTransformer {
    private final FopFactory fopFactory;

    private final TransformerFactory transformerFactory;

    public static String INPUT_FILE = "./src/main/resources/data/xml/zahtjev-za-sertifikat.xml";

    public static String XSL_FILE = "./src/main/resources/data/xsl-fo/zahtjev-za-sertifikat.xsl";

    public static String OUTPUT_FILE = "./src/main/resources/data/gen/itext/zahtjev-za-sertifikat.pdf";

    public Xml2PdfTransformer(String inputXML, String inputXSL, String outputPDF) throws SAXException, IOException {

        // Initialize FOP factory object
        fopFactory = FopFactory.newInstance(new File("src/fop.xconf"));

        // Setup the XSLT transformer factory
        transformerFactory = new TransformerFactoryImpl();

        INPUT_FILE = inputXML;
        XSL_FILE = inputXSL;
        OUTPUT_FILE = outputPDF;
    }

    private void generatePDF() throws Exception {

        System.out.println("[INFO] " + Xml2PdfTransformer.class.getSimpleName());

        // Point to the XSL-FO file
        File xslFile = new File(XSL_FILE);

        // Create transformation source
        StreamSource transformSource = new StreamSource(xslFile);

        // Initialize the transformation subject
        StreamSource source = new StreamSource(new File(INPUT_FILE));

        // Initialize user agent needed for the transformation
        FOUserAgent userAgent = fopFactory.newFOUserAgent();

        // Create the output stream to store the results
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        // Initialize the XSL-FO transformer object
        Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);

        // Construct FOP instance with desired output format
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);

        // Resulting SAX events
        Result res = new SAXResult(fop.getDefaultHandler());

        // Start XSLT transformation and FOP processing
        xslFoTransformer.transform(source, res);

        // Generate PDF file
        File pdfFile = new File(OUTPUT_FILE);
        if (!pdfFile.getParentFile().exists()) {
            System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
            pdfFile.getParentFile().mkdir();
        }

        OutputStream out = new BufferedOutputStream(new FileOutputStream(pdfFile));
        out.write(outStream.toByteArray());

        System.out.println("[INFO] File \"" + pdfFile.getCanonicalPath() + "\" generated successfully.");
        out.close();

        System.out.println("[INFO] End.");

    }

    public static void main(String[] args) throws Exception {
        new Xml2PdfTransformer("./src/main/resources/data/xml/digitalni-sertifikat.xml", "./src/main/resources/data/xsl-fo/digitalni-sertifikat.xsl","./src/main/resources/data/gen/itext/digitalni-sertifikat.pdf").generatePDF();
    }

    /*
    "./src/main/resources/data/xml/zahtjev-za-sertifikat.xml", "./src/main/resources/data/xsl-fo/zahtjev-za-sertifikat.xsl","./src/main/resources/data/gen/itext/zahtjev-za-sertifikat.pdf"
    "./src/main/resources/data/xml/interesovanje.xml", "./src/main/resources/data/xsl-fo/interesovanje.xsl","./src/main/resources/data/gen/itext/interesovanje.pdf"
    "./src/main/resources/data/xml/digitalni-sertifikat.xml", "./src/main/resources/data/xsl-fo/digitalni-sertifikat.xsl","./src/main/resources/data/gen/itext/digitalni-sertifikat.pdf"
    "./src/main/resources/data/xml/izvjestaj-o-imunizaciji.xml", "./src/main/resources/data/xsl-fo/izvjestaj-o-imunizaciji.xsl","./src/main/resources/data/gen/itext/izvjestaj-o-imunizaciji.pdf"
    "./src/main/resources/data/xml/obrazac-saglasnosti.xml", "./src/main/resources/data/xsl-fo/obrazac-o-saglasnosti.xsl","./src/main/resources/data/gen/itext/obrazac-o-saglasnosti.pdf"
    "./src/main/resources/data/xml/potvrda-o-vakcinaciji.xml", "./src/main/resources/data/xsl-fo/potvrda-o-vakcinaciji.xsl","./src/main/resources/data/gen/itext/potvrda-o-vakcinaciji.pdf"
     */
}
