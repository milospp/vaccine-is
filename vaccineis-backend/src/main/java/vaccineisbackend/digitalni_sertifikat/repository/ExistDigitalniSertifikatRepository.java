package vaccineisbackend.digitalni_sertifikat.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import vaccineisbackend.db.ExistManager;
import vaccineisbackend.model.digitalni_sertifikat.ZeleniSertifikat;
import vaccineisbackend.repository.CRUDRepositoryImpl;
import vaccineisbackend.service.MarshallingService;

import java.util.UUID;

@Repository
public class ExistDigitalniSertifikatRepository extends CRUDRepositoryImpl<ZeleniSertifikat> {

    @Autowired
    public ExistDigitalniSertifikatRepository(ExistManager existManager, MarshallingService marshallingService) {
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
