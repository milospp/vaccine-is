package vaccineisbackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import vaccineisbackend.db.ExistManager;

import java.util.UUID;

@Repository
public class ObrazacSaglasnostiRepository {
    private String collectionId = "db/obrasciSaglasnosti";

    @Autowired
    private ExistManager existManager;

    public void saveObrazacSaglasnosti(String text) throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        existManager.storeFromText(collectionId, String.valueOf(UUID.randomUUID()), text);
    }

}
