package zajednicko.service;

import org.w3c.dom.Node;
import org.xmldb.api.modules.XMLResource;

import java.util.ArrayList;
import java.util.List;

public interface MarshallingService {

    <T> T unmarshall(String xmlString, Class<T> clazz);
    <T> T unmarshall(XMLResource xmlResource, Class<T> clazz);
    <T> T unmarshall(Node xmlDOM, Class<T> clazz);
//    <T> T unmarshallList(XMLResource xmlResource, Class<ArrayList<T>> clazz);

    <T> String marshall(T object, Class<T> clazz);
}