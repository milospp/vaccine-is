package vaccineisemployee.digitalni_sertifikat.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vaccineisemployee.digitalni_sertifikat.model.ZeleniSertifikat;
import zajednicko.repository.CRUDExistRepositoryImpl;
import zajednicko.service.MarshallingService;
import zajednicko.db.ExistManager;

@Repository
public class DigitalniSertifikatExistRepository extends CRUDExistRepositoryImpl<ZeleniSertifikat> {

    @Autowired
    public DigitalniSertifikatExistRepository(ExistManager existManager, MarshallingService marshallingService) {
        super("db/digitalniSertifikati", "schemas/digitalni-sertifikat.xsc", existManager, marshallingService);
    }

    @Override
    protected Class<ZeleniSertifikat> getEntityClass() {
        return ZeleniSertifikat.class;
    }
}
