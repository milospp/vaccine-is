package vaccineisbackend.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vaccineisbackend.db.ExistManager;
import vaccineisbackend.model.CollectionId;
import vaccineisbackend.model.interesovanje.Interesovanje;
import vaccineisbackend.service.MarshallingService;

@Service
public class InteresovanjeRepository extends CRUDRepositoryImpl<Interesovanje> {

    @Autowired
    public InteresovanjeRepository(ExistManager existManager, MarshallingService marshallingService) {
        super("db/interesovanje", existManager, marshallingService);
    }

    @Override
    protected Class<Interesovanje> getEntityClass() {
        return Interesovanje.class;
    }
}
