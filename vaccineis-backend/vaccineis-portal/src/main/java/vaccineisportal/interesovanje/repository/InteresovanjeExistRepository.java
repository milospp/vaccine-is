package vaccineisportal.interesovanje.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vaccineisportal.interesovanje.model.Interesovanje;
import zajednicko.db.ExistManager;
import zajednicko.repository.CRUDRepositoryImpl;
import zajednicko.service.MarshallingService;

@Repository
public class InteresovanjeExistRepository extends CRUDRepositoryImpl<Interesovanje> {

    @Autowired
    public InteresovanjeExistRepository(ExistManager existManager, MarshallingService marshallingService) {
        super("db/interesovanje", existManager, marshallingService);
        System.out.println("InteresovanjeExistRepository.InteresovanjeExistRepository");
    }

    @Override
    protected Class<Interesovanje> getEntityClass() {
        return Interesovanje.class;
    }
}
