package vaccineisemployee.izvestaj.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import vaccineisemployee.izvestaj.model.Izvjestaj;
import zajednicko.db.ExistManager;
import zajednicko.repository.CRUDRepositoryImpl;
import zajednicko.service.impl.MarshallingServiceImpl;

import java.util.UUID;

@Repository
public class IzvestajExistRepository extends CRUDRepositoryImpl<Izvjestaj> {

    @Autowired
    public IzvestajExistRepository(ExistManager existManager, MarshallingServiceImpl marshallingService) {
        super("db/izvestaji", existManager, marshallingService);
    }

    public void saveIzvestaj(String text) throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        existManager.storeFromText(collectionId, String.valueOf(UUID.randomUUID()), text);
    }

    @Override
    protected Class<Izvjestaj> getEntityClass() {
        return Izvjestaj.class;
    }

}
