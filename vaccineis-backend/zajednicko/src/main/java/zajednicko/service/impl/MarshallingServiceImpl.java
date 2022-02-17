package zajednicko.service.impl;

import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import zajednicko.service.MarshallingService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarshallingServiceImpl implements MarshallingService {

    public <T> T unmarshall(String xmlString, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            StringReader stringReader = new StringReader(xmlString);
            var unmarshalledObject = unmarshaller.unmarshal(stringReader);
            return clazz.cast(unmarshalledObject);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T unmarshall(XMLResource xmlResource, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            var unmarshalledObject = unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            return clazz.cast(unmarshalledObject);
        } catch (JAXBException | XMLDBException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public <T> T unmarshallList(XMLResource xmlResource, Class<ArrayList<T>> clazz) {
//        try {
//            JAXBContext context = JAXBContext.newInstance(clazz);
//            Unmarshaller unmarshaller = context.createUnmarshaller();
//
//            var unmarshalledObject = unmarshaller.unmarshal(xmlResource.getContentAsDOM());
//            return clazz.cast(unmarshalledObject);
//        } catch (JAXBException | XMLDBException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public <T> String marshall(T object, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(object, stringWriter);
            return stringWriter.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
