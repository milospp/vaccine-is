package vaccineisbackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import vaccineisbackend.db.ExistManager;

@Repository
public class InteresovanjeRepository {
    private String collectionId = "db/interesovanja";

    @Autowired
    private ExistManager existManager;

    public void saveInteresovanje(String text) throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        existManager.storeFromText(collectionId, "Interesovanje X", text);
    }
}
