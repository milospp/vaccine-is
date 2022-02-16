package vaccineisemployee.potvrda_o_vakcinaciji.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import vaccineisemployee.potvrda_o_vakcinaciji.model.PotvrdaVakcinacije;
import zajednicko.db.ExistManager;
import zajednicko.repository.CRUDRepositoryImpl;
import zajednicko.service.impl.MarshallingServiceImpl;

import java.util.UUID;

@Repository
public class PotvrdaOVakcinacijiExistRepository extends CRUDRepositoryImpl<PotvrdaVakcinacije> {

    @Autowired
    public PotvrdaOVakcinacijiExistRepository(ExistManager existManager, MarshallingServiceImpl marshallingService) {
        super("db/potvrdeOVakcinaciji", existManager, marshallingService);
    }

    public void savePotvrdaOVakcinaciji(String text) throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        existManager.storeFromText(collectionId, String.valueOf(UUID.randomUUID()), text);
    }

    @Override
    protected Class<PotvrdaVakcinacije> getEntityClass() {
        return PotvrdaVakcinacije.class;
    }
}
