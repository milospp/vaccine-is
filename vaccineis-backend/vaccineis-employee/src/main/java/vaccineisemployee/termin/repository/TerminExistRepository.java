package vaccineisemployee.termin.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vaccineisemployee.digitalni_sertifikat.model.ZeleniSertifikat;
import vaccineisemployee.termin.model.Termin;
import zajednicko.db.ExistManager;
import zajednicko.repository.CRUDRepositoryImpl;
import zajednicko.service.MarshallingService;

@Repository
public class TerminExistRepository extends CRUDRepositoryImpl<Termin> {

    @Autowired
    public TerminExistRepository(ExistManager existManager, MarshallingService marshallingService) {
        super("db/termin", existManager, marshallingService);
    }

    @Override
    protected Class<Termin> getEntityClass() {
        return Termin.class;
    }
}
