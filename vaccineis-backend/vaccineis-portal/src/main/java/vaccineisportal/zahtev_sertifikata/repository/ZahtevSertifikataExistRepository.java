package vaccineisportal.zahtev_sertifikata.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import vaccineisportal.zahtev_sertifikata.model.Zahtjev;
import zajednicko.db.ExistManager;
import zajednicko.repository.CRUDRepositoryImpl;
import zajednicko.service.MarshallingService;

import java.util.UUID;

@Repository
public class ZahtevSertifikataExistRepository extends CRUDRepositoryImpl<Zahtjev> {

    @Autowired
    public ZahtevSertifikataExistRepository(ExistManager existManager, MarshallingService marshallingService) {
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
