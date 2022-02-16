package vaccineisemployee.digitalni_sertifikat.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vaccineisemployee.digitalni_sertifikat.model.ZeleniSertifikat;
import zajednicko.repository.CRUDRepositoryImpl;
import zajednicko.service.MarshallingService;
import zajednicko.db.ExistManager;

@Repository
public class DigitalniSertifikatExistRepository extends CRUDRepositoryImpl<ZeleniSertifikat> {

    @Autowired
    public DigitalniSertifikatExistRepository(ExistManager existManager, MarshallingService marshallingService) {
        super("db/digitalniSertifikati", existManager, marshallingService);
    }

    @Override
    protected Class<ZeleniSertifikat> getEntityClass() {
        return ZeleniSertifikat.class;
    }
}
