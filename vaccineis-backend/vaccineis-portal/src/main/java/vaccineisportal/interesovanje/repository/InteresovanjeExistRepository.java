package vaccineisportal.interesovanje.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vaccineisportal.interesovanje.model.Interesovanje;
import zajednicko.db.ExistManager;
import zajednicko.repository.CRUDExistRepositoryImpl;
import zajednicko.service.MarshallingService;

@Repository
public class InteresovanjeExistRepository extends CRUDExistRepositoryImpl<Interesovanje> {

    @Autowired
    public InteresovanjeExistRepository(ExistManager existManager, MarshallingService marshallingService) {
        super("db/interesovanje", "schemas/interesovanje.xsd", existManager, marshallingService);
    }

    @Override
    protected Class<Interesovanje> getEntityClass() {
        return Interesovanje.class;
    }
}
