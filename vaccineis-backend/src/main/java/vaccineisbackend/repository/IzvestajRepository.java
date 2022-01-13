package vaccineisbackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import vaccineisbackend.db.ExistManager;

import java.util.UUID;

@Repository
public class IzvestajRepository {
    private String collectionId = "db/izvestaji";

    @Autowired
    private ExistManager existManager;

    public void saveIzvestaj(String text) throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        existManager.storeFromText(collectionId, String.valueOf(UUID.randomUUID()), text);
    }

}
