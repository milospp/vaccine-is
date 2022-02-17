package zajednicko.repository;

import java.util.List;

public interface CRUDExistRepository<T> {

    T findOne(String id);

    List<T> findXpath(String xPath);

    T create(String xmlString);
}
