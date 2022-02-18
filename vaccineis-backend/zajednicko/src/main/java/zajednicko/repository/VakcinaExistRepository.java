package zajednicko.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import zajednicko.db.ExistManager;
import zajednicko.exception.XMLSchemaValidationException;
import zajednicko.model.vakcina.Vakcina;
import zajednicko.service.MarshallingService;

@Repository
public class VakcinaExistRepository extends CRUDExistRepositoryImpl<Vakcina> {

    @Autowired
    public VakcinaExistRepository(ExistManager existManager, MarshallingService marshallingService, PasswordEncoder passwordEncoder) {
        super("db/vakcine", "schemas/vakcina.xsd", existManager, marshallingService);
    }

    @Override
    protected Class<Vakcina> getEntityClass() {
        return Vakcina.class;
    }

    public void save(Vakcina vakcina) {
        try {
            existManager.storeFromText(collectionId, vakcina.getId(), marshallingService.marshall(vakcina, getEntityClass()));
        } catch (XMLDBException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new XMLSchemaValidationException(e.getMessage());
        }
    }
}
