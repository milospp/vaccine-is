package zajednicko.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.model.korisnik.STrola;
import zajednicko.service.MarshallingService;
import zajednicko.db.ExistManager;

@Repository
public class UserExistRepository extends CRUDExistRepositoryImpl<Korisnik> {

    @Autowired
    public UserExistRepository(ExistManager existManager, MarshallingService marshallingService) {
        super("db/korisnici", "schemas/korisnik.xsd", existManager, marshallingService);
    }

    @Override
    protected Class<Korisnik> getEntityClass() {
        return Korisnik.class;
    }


    public Korisnik findUserByEmail(String email) {
        var korisnik = new Korisnik();
        korisnik.setIme("marko");
        korisnik.setPrezime("markovic");
        korisnik.setJmbg("2006999183897");
        korisnik.setEmail("marko@gmail.com");
        korisnik.setSifra("$2a$10$QD6Kbt74UFyyK9RIhXfZM.tmIZdGUGo82G6FFMfsES0P0/lLi3wuK");
        korisnik.setRola(STrola.GRADJANIN);
        return korisnik;
    }
}
