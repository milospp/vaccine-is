package zajednicko.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import zajednicko.exception.XMLSchemaValidationException;
import org.w3c.dom.Node;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import zajednicko.service.MarshallingService;
import zajednicko.util.XMLSchemaValidationHandler;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

@Scope("prototype")
@Service
public class MarshallingServiceImpl implements MarshallingService {

    public <T> T unmarshall(String xmlString, Class<T> clazz, String schemaPath) {
        XMLSchemaValidationHandler eventHandler = null;

        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(schemaPath));

            eventHandler = new XMLSchemaValidationHandler("");
            unmarshaller.setSchema(schema);
            unmarshaller.setEventHandler(eventHandler);

            return clazz.cast(unmarshaller.unmarshal(new StringReader(xmlString)));

        } catch (JAXBException | SAXException e) {
            String message = (eventHandler != null) ? eventHandler.getMessage() : e.getMessage();
            throw new XMLSchemaValidationException(message);
        }
    }

    public <T> T unmarshall(XMLResource xmlResource, Class<T> clazz) {
        try {
            return unmarshall(xmlResource.getContentAsDOM(), clazz);
        } catch (XMLDBException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> T unmarshall(Node xmlDOM, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            System.out.println("xmlDOM = " + xmlDOM);
            var unmarshalledObject = unmarshaller.unmarshal(xmlDOM);
            return clazz.cast(unmarshalledObject);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> String marshall(T object, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(object, stringWriter);
            return stringWriter.toString();

        } catch (JAXBException e) {
            throw new XMLSchemaValidationException(e.getMessage());
        }
    }
}
