package vaccineisbackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import vaccineisbackend.db.ExistManager;
import vaccineisbackend.model.izvjestaj.Izvjestaj;
import vaccineisbackend.service.MarshallingService;

import java.util.UUID;

@Repository
public class ExistIzvestajRepository extends CRUDRepositoryImpl<Izvjestaj> {

    @Autowired
    public ExistIzvestajRepository(ExistManager existManager, MarshallingService marshallingService) {
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
