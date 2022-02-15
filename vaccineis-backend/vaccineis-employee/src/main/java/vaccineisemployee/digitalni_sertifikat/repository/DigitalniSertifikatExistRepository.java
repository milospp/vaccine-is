package vaccineisemployee.digitalni_sertifikat.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import vaccineisemployee.digitalni_sertifikat.model.ZeleniSertifikat;
import zajednicko.db.ExistManager;
import zajednicko.repository.CRUDRepositoryImpl;
import zajednicko.service.MarshallingService;

import java.util.UUID;

@Repository
public class DigitalniSertifikatExistRepository extends CRUDRepositoryImpl<ZeleniSertifikat> {

    @Autowired
    public DigitalniSertifikatExistRepository(ExistManager existManager, MarshallingService marshallingService) {
        super("db/digitalniSertifikati", existManager, marshallingService);
    }

    public void saveDigitalniSertifikat(String text) throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        existManager.storeFromText(collectionId, String.valueOf(UUID.randomUUID()), text);
    }

    @Override
    protected Class getEntityClass() {
        return ZeleniSertifikat.class;
    }

}
