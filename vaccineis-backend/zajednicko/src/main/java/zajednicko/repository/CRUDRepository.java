package zajednicko.repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface CRUDRepository<T> {

    List<T> findAll();
    T findOne(String id);
    List<T> findXpath(String xPath);
    T save(T entity);
    void delete();
}
