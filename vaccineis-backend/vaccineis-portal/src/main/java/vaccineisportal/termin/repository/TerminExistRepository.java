package vaccineisportal.termin.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vaccineisportal.termin.model.Termin;
import zajednicko.db.ExistManager;
import zajednicko.repository.CRUDExistRepositoryImpl;
import zajednicko.service.MarshallingService;

@Repository
public class TerminExistRepository extends CRUDExistRepositoryImpl<Termin> {

    @Autowired
    public TerminExistRepository(ExistManager existManager, MarshallingService marshallingService) {
        super("db/termin", "schemas/termin.xsd", existManager, marshallingService);
    }

    @Override
    protected Class<Termin> getEntityClass() {
        return Termin.class;
    }
}
