package zajednicko.repository;

import java.util.List;

public interface CRUDExistRepository<T> {

    List<T> findAll();

    T findOne(String id);

    List<T> findXpath(String xPath);

    T create(String xmlString);
}
