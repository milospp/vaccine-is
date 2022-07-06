package zajednicko.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

public class ZajednickoUtil {
    public static String XML_PREFIX = "http://www.ftn.uns.ac.rs/";
    public static String RDF_PREDICATE = "http://www.ftn.uns.ac.rs/predicate/";

    public static String INTERESOVANJE_XSLT = "zajednicko/src/main/resources/data/xslt/interesovanje.xsl";
    public static String INTERESOVANJE_PDF = "zajednicko/src/main/resources/data/xsl-fo/interesovanje.xsl";

    public static String ZAHTEV_ZA_ZS_XSLT = "zajednicko/src/main/resources/data/xslt/zahtjev-za-sertifikat.xsl";
    public static String ZAHTEV_ZA_ZS_PDF = "zajednicko/src/main/resources/data/xsl-fo/interesovanje.xsl";

    public static String SAGLASNOST_XSLT = "zajednicko/src/main/resources/data/xslt/obrazac-o-saglasnosti.xsl";
    public static String SAGLASNOST_PDF = "zajednicko/src/main/resources/data/xsl-fo/obrazac-o-saglasnosti.xsl";

    public static String POTVRDA_XSLT = "zajednicko/src/main/resources/data/xslt/potvrda-o-vakcinaciji.xsl";
    public static String POTVRDA_PDF = "zajednicko/src/main/resources/data/xsl-fo/potvrda-o-vakcinaciji.xsl";

    public static String DIGITALNI_XSLT = "zajednicko/src/main/resources/data/xslt/digitalni-sertifikat.xsl";
    public static String DIGITALNI_PDF = "zajednicko/src/main/resources/data/xsl-fo/digitalni-sertifikat.xsl";

    public static String IZVESTAJ_XSLT = "zajednicko/src/main/resources/data/xslt/izvjestaj-o-imunizaciji.xsl";
    public static String IZVESTAJ_PDF = "zajednicko/src/main/resources/data/xsl-fo/izvjestaj-o-imunizaciji.xsl";

    public static String getIdFromUri(String uri) {
        uri = uri.strip();
        if (uri.lastIndexOf('/') == uri.length() - 1) uri = uri.substring(0, uri.length()-1);
        int first_index = uri.lastIndexOf('/');
        return uri.substring(first_index + 1);
    }

    public static String literalQuotes(String literal) {
        if (literal.startsWith("http://")) {
            return "<" + literal + ">";
        } else {
            return "\"" + literal + "\"";
        }
    }

    public static XMLGregorianCalendar localDateToGregorian(LocalDate date) {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(date.toString());
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
