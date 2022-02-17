package zajednicko.repository;

import java.util.List;
import java.util.UUID;

public interface CRUDExistRepository<T> {

    List<T> findAll();
    T findOne(UUID id);
    T create(String xmlString);
}
