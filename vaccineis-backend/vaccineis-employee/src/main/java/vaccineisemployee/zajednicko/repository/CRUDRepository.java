package vaccineisemployee.zajednicko.repository;

import java.util.List;
import java.util.UUID;

public interface CRUDRepository<T> {

    List<T> findAll();
    T findOne(UUID id);
    T save(T entity);
    void delete();
}
