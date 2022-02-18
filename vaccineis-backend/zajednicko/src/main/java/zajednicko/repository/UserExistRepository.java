package zajednicko.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import zajednicko.exception.XMLSchemaValidationException;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.model.korisnik.STrola;
import zajednicko.service.MarshallingService;
import zajednicko.db.ExistManager;

import java.util.UUID;

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

    @Override
    public Korisnik create(String xmlString) {
        try {
            Korisnik entity = marshallingService.unmarshall(xmlString, getEntityClass(), schemaPath);

            String id = String.valueOf(UUID.randomUUID());
            entity.setId(id);

            String korisnik = marshallingService.marshall(entity, getEntityClass());
            System.out.println(korisnik);

            existManager.storeFromText(collectionId, id, korisnik);
            return entity;

        } catch (XMLDBException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new XMLSchemaValidationException(e.getMessage());
        }
    }

    public Korisnik findUserByEmail(String email) {
        var korisnik = new Korisnik();
        korisnik.setIme("marko");
        korisnik.setPrezime("markovic");
        korisnik.setEmail("marko@gmail.com");
        korisnik.setSifra("$2a$10$QD6Kbt74UFyyK9RIhXfZM.tmIZdGUGo82G6FFMfsES0P0/lLi3wuK");
        korisnik.setRola(STrola.GRADJANIN);
        return korisnik;
    }
}
