package vaccineisportal.zahtev_sertifikata.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vaccineisportal.zahtev_sertifikata.model.Zahtjev;
import zajednicko.db.ExistManager;
import zajednicko.repository.CRUDExistRepositoryImpl;
import zajednicko.service.MarshallingService;

@Repository
public class ZahtevSertifikataExistRepository extends CRUDExistRepositoryImpl<Zahtjev> {

    @Autowired
    public ZahtevSertifikataExistRepository(ExistManager existManager, MarshallingService marshallingService) {
        super("db/zahteviSertifikata", "schemas/zahtjev-za-sertifikat.xsd", existManager, marshallingService);
    }

    @Override
    protected Class<Zahtjev> getEntityClass() {
        return Zahtjev.class;
    }
}
