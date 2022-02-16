package zajednicko.repository;

import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Statement;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface CRUDRDFRepository {

    ResultSet findAll(String graphUri);
    ResultSet findWhere(String whereStatement);
    ResultSet findBySubject(String graphUri, String subject);
    ResultSet findByPredicate(String graphUri, String predicate);
    ResultSet findByObject(String graphUri, String object);

    void addStatement(Statement statement);
    void addStatement(Collection<Statement> statement);

    void dropRDF(String graphUri, String uri);
}
