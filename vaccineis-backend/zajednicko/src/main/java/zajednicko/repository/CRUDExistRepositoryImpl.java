package zajednicko.repository;

import org.springframework.stereotype.Repository;
import zajednicko.db.ExistManager;
import lombok.AllArgsConstructor;
import org.xmldb.api.base.XMLDBException;
import zajednicko.exception.XMLSchemaValidationException;
import zajednicko.service.MarshallingService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public abstract class CRUDExistRepositoryImpl<T> implements CRUDExistRepository<T> {

    protected final String collectionId;
    protected final String schemaPath;
    protected final ExistManager existManager;
    protected final MarshallingService marshallingService;

    protected abstract Class<T> getEntityClass();

    @Override
    public List<T> findAll() {
        return new ArrayList<>();
    }

    @Override
    public T findOne(UUID id) {
        return null;
    }

    @Override
    public T create(String xmlString) {
        try {
            T entity = marshallingService.unmarshall(xmlString, getEntityClass(), schemaPath);
            existManager.storeFromText(collectionId, String.valueOf(UUID.randomUUID()), marshallingService.marshall(entity, getEntityClass()));
            return entity;

        } catch (XMLDBException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new XMLSchemaValidationException(e.getMessage());
        }
    }
}
