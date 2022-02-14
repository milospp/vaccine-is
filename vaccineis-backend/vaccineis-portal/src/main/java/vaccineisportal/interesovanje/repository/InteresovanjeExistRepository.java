package vaccineisportal.interesovanje.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vaccineisemployee.interesovanje.model.Interesovanje;
import vaccineisemployee.zajednicko.db.ExistManager;
import vaccineisemployee.zajednicko.repository.CRUDRepositoryImpl;
import vaccineisemployee.zajednicko.service.MarshallingService;

@Repository
public class InteresovanjeExistRepository extends CRUDRepositoryImpl<Interesovanje> {

    @Autowired
    public InteresovanjeExistRepository(ExistManager existManager, MarshallingService marshallingService) {
        super("db/interesovanje", existManager, marshallingService);
    }

    @Override
    protected Class<Interesovanje> getEntityClass() {
        return Interesovanje.class;
    }
}
