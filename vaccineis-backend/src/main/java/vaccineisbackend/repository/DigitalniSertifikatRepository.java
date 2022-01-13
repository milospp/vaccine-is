package vaccineisbackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import vaccineisbackend.db.ExistManager;

import java.util.UUID;

@Repository
public class DigitalniSertifikatRepository {
    private String collectionId = "db/digitalniSertifikati";

    @Autowired
    private ExistManager existManager;

    public void saveDigitalniSertifikat(String text) throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        existManager.storeFromText(collectionId, String.valueOf(UUID.randomUUID()), text);
    }

    public ResourceSet findAll() throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return existManager.retrieve(collectionId, "/");
    }
}
