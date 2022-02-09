package vaccineisbackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import vaccineisbackend.db.ExistManager;
import vaccineisbackend.model.zahtjevsertifikata.Zahtjev;
import vaccineisbackend.service.MarshallingService;

import java.util.UUID;

@Repository
public class ExistZahtevSertifikataRepository extends CRUDRepositoryImpl<Zahtjev> {

    @Autowired
    public ExistZahtevSertifikataRepository(ExistManager existManager, MarshallingService marshallingService) {
        super("db/zahteviSertifikata", existManager, marshallingService);
    }

    public void saveZahtevSertifikata(String text) throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        existManager.storeFromText(collectionId, String.valueOf(UUID.randomUUID()), text);
    }

    @Override
    protected Class<Zahtjev> getEntityClass() {
        return Zahtjev.class;
    }
}
