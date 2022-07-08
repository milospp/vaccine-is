package vaccineisportal.obrazac_saglasnosti.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import vaccineisportal.obrazac_saglasnosti.model.Saglasnost;
import zajednicko.db.ExistManager;
import zajednicko.exception.XMLSchemaValidationException;
import zajednicko.repository.CRUDExistRepositoryImpl;
import zajednicko.service.MarshallingService;

import java.util.UUID;


@Repository
public class ObrazacSaglasnostiExistRepository extends CRUDExistRepositoryImpl<Saglasnost> {

    @Autowired
    public ObrazacSaglasnostiExistRepository(ExistManager existManager, MarshallingService marshallingService) {
        super("db/obrasciSaglasnosti", "schemas/obrazac-saglasnosti.xsd", existManager, marshallingService);
    }

    @Override
    protected Class<Saglasnost> getEntityClass() {
        return Saglasnost.class;
    }

    @Override
    public Saglasnost create(String xmlString) {
        try {
            Saglasnost entity = this.marshallingService.unmarshall(xmlString, getEntityClass(), schemaPath);
            entity.setId(String.valueOf(UUID.randomUUID()));

            existManager.storeFromText(collectionId, entity.getId(), marshallingService.marshall(entity, getEntityClass()));
            return entity;

        } catch (XMLDBException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new XMLSchemaValidationException(e.getMessage());
        }
    }

    public Saglasnost update(String id, String xmlString) {
        try {
            Saglasnost entity = this.marshallingService.unmarshall(xmlString, getEntityClass(), schemaPath);
            entity.setId(id);
            existManager.storeFromText(collectionId, id, marshallingService.marshall(entity, getEntityClass()));
            return entity;

        } catch (XMLDBException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new XMLSchemaValidationException(e.getMessage());
        }
    }
}
