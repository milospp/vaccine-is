package zajednicko.util;

public class ZajednickoUtil {
    public static String XML_PREFIX = "http://www.ftn.uns.ac.rs/";
    public static String RDF_PREDICATE = "http://www.ftn.uns.ac.rs/predicate/";


    public static String getIdFromUri(String uri) {
        uri = uri.strip();
        if (uri.lastIndexOf('/') == uri.length() - 1) uri = uri.substring(0, uri.length()-1);
        int first_index = uri.lastIndexOf('/');
        return uri.substring(first_index + 1);
    }
}
