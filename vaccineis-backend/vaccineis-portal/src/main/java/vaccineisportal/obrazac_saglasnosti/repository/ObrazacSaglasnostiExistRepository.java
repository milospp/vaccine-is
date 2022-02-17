package vaccineisportal.obrazac_saglasnosti.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vaccineisportal.obrazac_saglasnosti.model.Saglasnost;
import zajednicko.db.ExistManager;
import zajednicko.repository.CRUDExistRepositoryImpl;
import zajednicko.service.MarshallingService;


@Repository
public class ObrazacSaglasnostiExistRepository extends CRUDExistRepositoryImpl<Saglasnost> {

    @Autowired
    public ObrazacSaglasnostiExistRepository(ExistManager existManager, MarshallingService marshallingService) {
        super("db/obrasciSaglasnosti", "schemas/obrazac_saglasnosti.xsc", existManager, marshallingService);
    }

    @Override
    protected Class<Saglasnost> getEntityClass() {
        return Saglasnost.class;
    }
}
