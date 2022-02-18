package zajednicko.service;

import org.w3c.dom.Node;
import org.xmldb.api.modules.XMLResource;


public interface MarshallingService {

    <T> T unmarshall(String xmlString, Class<T> clazz, String schemaPath);
    <T> T unmarshall(XMLResource xmlResource, Class<T> clazz);
    <T> T unmarshall(Node xmlDOM, Class<T> clazz);

    <T> String marshall(T object, Class<T> clazz);
}