package vaccineisbackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import vaccineisbackend.db.ExistManager;
import vaccineisbackend.model.obrazac_saglasnosti.Saglasnost;
import vaccineisbackend.service.MarshallingService;

import java.util.UUID;

@Repository
public class ExistObrazacSaglasnostiRepository extends CRUDRepositoryImpl<Saglasnost> {

    @Autowired
    public ExistObrazacSaglasnostiRepository(ExistManager existManager, MarshallingService marshallingService) {
        super("db/obrasciSaglasnosti", existManager, marshallingService);
    }

    public void saveObrazacSaglasnosti(String text) throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        existManager.storeFromText(collectionId, String.valueOf(UUID.randomUUID()), text);
    }

    @Override
    protected Class<Saglasnost> getEntityClass() {
        return Saglasnost.class;
    }
}
