package vaccineisbackend.service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MarshallingService {

    public <T> T unmarshall(Class<T> clazz, String packagePath, String inputFilePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(packagePath);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            return clazz.cast(unmarshaller.unmarshal(new File(inputFilePath)));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> void marshall(Class<T> clazz, String packagePath, String outputFilePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(packagePath);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            T object = clazz.newInstance();
            marshaller.marshal(object, System.out);
            marshaller.marshal(object, new FileOutputStream(outputFilePath));

        } catch (JAXBException | FileNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
