package vaccineisportal.obrazac_saglasnosti.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import vaccineisportal.obrazac_saglasnosti.model.Saglasnost;
import zajednicko.db.ExistManager;
import zajednicko.repository.CRUDRepositoryImpl;
import zajednicko.service.MarshallingService;

import java.util.UUID;

@Repository
public class ObrazacSaglasnostiExistRepository extends CRUDRepositoryImpl<Saglasnost> {

    @Autowired
    public ObrazacSaglasnostiExistRepository(ExistManager existManager, MarshallingService marshallingService) {
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
