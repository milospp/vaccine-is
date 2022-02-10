package vaccineisbackend.interesovanje.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vaccineisbackend.db.ExistManager;
import vaccineisbackend.model.interesovanje.Interesovanje;
import vaccineisbackend.repository.CRUDRepositoryImpl;
import vaccineisbackend.service.MarshallingService;

@Repository
public class ExistInteresovanjeRepository extends CRUDRepositoryImpl<Interesovanje> {

    @Autowired
    public ExistInteresovanjeRepository(ExistManager existManager, MarshallingService marshallingService) {
        super("db/interesovanje", existManager, marshallingService);
    }

    @Override
    protected Class<Interesovanje> getEntityClass() {
        return Interesovanje.class;
    }
}
