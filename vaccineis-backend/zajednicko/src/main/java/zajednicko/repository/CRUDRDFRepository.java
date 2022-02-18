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
    ResultSet findWhere(String graphUri, String whereStatement);
    ResultSet findBySubject(String graphUri, String subject);
    ResultSet findByPredicate(String graphUri, String predicate);
    ResultSet findByObject(String graphUri, String object);

    ResultSet findByPredicateAndObject(String graphUri, String predicate, String object);

    void uploadTriplet(String graphUri, String resource, String property, String literal);
    void deleteTriplet(String graphUri, String resource, String property, String literal);
    void addStatement(String graphUri, Statement statement);
    void addStatement(String graphUri, Collection<Statement> statement);

    void dropRDF(String graphUri, String uri);
}
