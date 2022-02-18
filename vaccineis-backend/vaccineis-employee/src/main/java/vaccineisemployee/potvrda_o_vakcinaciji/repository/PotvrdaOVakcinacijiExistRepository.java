package vaccineisemployee.potvrda_o_vakcinaciji.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vaccineisemployee.potvrda_o_vakcinaciji.model.PotvrdaVakcinacije;
import zajednicko.db.ExistManager;
import zajednicko.repository.CRUDExistRepositoryImpl;
import zajednicko.service.impl.MarshallingServiceImpl;

@Repository
public class PotvrdaOVakcinacijiExistRepository extends CRUDExistRepositoryImpl<PotvrdaVakcinacije> {

    @Autowired
    public PotvrdaOVakcinacijiExistRepository(ExistManager existManager, MarshallingServiceImpl marshallingService) {
        super("db/potvrdeOVakcinaciji", "schemas/potvrda-o-vakcinaciji.xsd" ,existManager, marshallingService);
    }

    @Override
    protected Class<PotvrdaVakcinacije> getEntityClass() {
        return PotvrdaVakcinacije.class;
    }
}
