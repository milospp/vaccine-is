package zajednicko.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.service.MarshallingService;
import zajednicko.db.ExistManager;

@Repository
public class UserExistRepository extends CRUDRepositoryImpl<Korisnik> {

    @Autowired
    public UserExistRepository(ExistManager existManager, MarshallingService marshallingService) {
        super("db/korisnici", existManager, marshallingService);
    }

    @Override
    protected Class<Korisnik> getEntityClass() {
        return Korisnik.class;
    }


    public Korisnik findUserByEmail(String email) {
        return null;
    }
}
