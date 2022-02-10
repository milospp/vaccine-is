package vaccineisbackend.potvrda_o_vakcinaciji.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import vaccineisbackend.db.ExistManager;
import vaccineisbackend.model.potvrda_o_vakcinaciji.PotvrdaVakcinacije;
import vaccineisbackend.repository.CRUDRepositoryImpl;
import vaccineisbackend.service.MarshallingService;

import java.util.UUID;

@Repository
public class ExistPotvrdaOVakcinacijiRepository extends CRUDRepositoryImpl<PotvrdaVakcinacije> {

    @Autowired
    public ExistPotvrdaOVakcinacijiRepository(ExistManager existManager, MarshallingService marshallingService) {
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
