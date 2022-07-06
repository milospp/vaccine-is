package zajednicko.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import zajednicko.exception.BadRequestException;
import zajednicko.exception.XMLSchemaValidationException;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.service.MarshallingService;
import zajednicko.db.ExistManager;

import java.util.UUID;

@Repository
public class UserExistRepository extends CRUDExistRepositoryImpl<Korisnik> {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserExistRepository(ExistManager existManager, MarshallingService marshallingService, PasswordEncoder passwordEncoder) {
        super("db/korisnik", "schemas/korisnik.xsd", existManager, marshallingService);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected Class<Korisnik> getEntityClass() {
        return Korisnik.class;
    }

    @Override
    public Korisnik create(String xmlString) {
        try {
            Korisnik entity = marshallingService.unmarshall(xmlString, getEntityClass(), schemaPath);
            if (findUserByEmail(entity.getEmail()) != null)
                throw new BadRequestException("User with email: " + entity.getEmail() + " already exist");

            String id = String.valueOf(UUID.randomUUID());
            entity.setId(id);
            entity.setSifra(passwordEncoder.encode(entity.getSifra()));

            String korisnik = marshallingService.marshall(entity, getEntityClass());

            existManager.storeFromText(collectionId, id, korisnik);
            return entity;

        } catch (XMLDBException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new XMLSchemaValidationException(e.getMessage());
        }
    }

    public Korisnik findUserByEmail(String email) {
        return findAll().stream().filter(x -> x.getEmail().equalsIgnoreCase(email.trim())).findFirst().orElse(null);
    }
}
