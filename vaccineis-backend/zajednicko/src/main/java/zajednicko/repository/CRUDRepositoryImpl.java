package zajednicko.repository;

import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.base.Resource;
import zajednicko.db.ExistManager;
import lombok.AllArgsConstructor;
import org.xmldb.api.base.XMLDBException;
import zajednicko.model.BaseModel;
import zajednicko.service.MarshallingService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.beans.ExceptionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Repository
public abstract class CRUDRepositoryImpl<T extends BaseModel> implements CRUDRepository<T> {

    protected final String collectionId;
    protected final ExistManager existManager;
    protected final MarshallingService marshallingService;

    protected abstract Class<T> getEntityClass();

    @Override
    public List<T> findAll() {
        List<T> entities = new ArrayList<>();

        List<Node> nodes;
        try {
            nodes =  existManager.loadAll(collectionId);
        } catch (XMLDBException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return entities;
        }

        for (Node node : nodes){
            T entity = marshallingService.unmarshall(node, getEntityClass());
            entities.add(entity);
        }

        return entities;
    }

    @Override
    public T findOne(String id) {
        Node resource;
        try {
            resource = existManager.loadDOM(collectionId, id);
            System.out.println("resource = " + resource);
        } catch (XMLDBException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

        T entity = marshallingService.unmarshall(resource, getEntityClass());
        return entity;
    }

    @Override
    public List<T> findXpath(String xPath) {
        ResourceSet resources;
        try {
            resources = existManager.loadXPath(collectionId, xPath);
        } catch (XMLDBException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

        ArrayList<T> arrayList = new ArrayList<>();

        ResourceIterator i = null;
        try {
            i = resources.getIterator();

            Resource res = null;

            while (i.hasMoreResources()) {

                try {
                    res = i.nextResource();
                    T entity = marshallingService.unmarshall((XMLResource) res, getEntityClass());

                    arrayList.add(entity);
                    System.out.println(res.getContent());

                } catch (XMLDBException e) {
                    return null;
                } finally {

                    // don't forget to cleanup resources
                    try {
                        ((EXistResource) res).freeResources();
                    } catch (XMLDBException xe) {
                        xe.printStackTrace();
                    }
                }
            }
        } catch (XMLDBException e) {

        }

        return arrayList;
    }

    @Override
    public void delete() {
        return;
    }

    @Override
    public T save(T entity) {
        try {
            String uuid = String.valueOf(UUID.randomUUID());
            entity.setId(uuid);

            existManager.storeFromText(collectionId, uuid, marshallingService.marshall(entity, getEntityClass()));
            return entity;
        } catch (XMLDBException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            return null;
        }
    }
}
