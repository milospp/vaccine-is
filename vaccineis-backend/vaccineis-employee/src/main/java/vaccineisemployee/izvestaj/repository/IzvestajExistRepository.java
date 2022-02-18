package vaccineisemployee.izvestaj.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import vaccineisemployee.izvestaj.model.Izvjestaj;
import zajednicko.db.ExistManager;
import zajednicko.repository.CRUDExistRepositoryImpl;
import zajednicko.service.impl.MarshallingServiceImpl;

import java.util.UUID;

@Repository
public class IzvestajExistRepository extends CRUDExistRepositoryImpl<Izvjestaj> {

    @Autowired
    public IzvestajExistRepository(ExistManager existManager, MarshallingServiceImpl marshallingService) {
        super("db/izvestaji", "schemas/izvjestaj-o-imunizaciji.xsd", existManager, marshallingService);
    }

    @Override
    protected Class<Izvjestaj> getEntityClass() {
        return Izvjestaj.class;
    }

}
