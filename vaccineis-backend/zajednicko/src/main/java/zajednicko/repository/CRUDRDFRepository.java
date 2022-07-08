package zajednicko.repository;

import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Statement;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import zajednicko.model.util.ResultSetConnection;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface CRUDRDFRepository {

    ResultSetConnection findAll(String graphUri);
    ResultSetConnection findWhere(String graphUri, String whereStatement);
    ResultSetConnection findBySubject(String graphUri, String subject);
    ResultSetConnection findByPredicate(String graphUri, String predicate);
    ResultSetConnection findByObject(String graphUri, String object);

    ResultSetConnection findByPredicateAndObject(String graphUri, String predicate, String object);
    ResultSetConnection findBySubjectAndPredicate(String graphUri, String subject, String predicate);

    String findFirstBySubjectAndPred(String graphUri, String subject, String predicate);

    void uploadTriplet(String graphUri, String resource, String property, String literal);
    void deleteTriplet(String graphUri, String resource, String property, String literal);
    void deleteQuery(String graphUri, String query);
    void addStatement(String graphUri, Statement statement);
    void addStatement(String graphUri, Collection<Statement> statement);

    void dropRDF(String graphUri, String uri);
}
