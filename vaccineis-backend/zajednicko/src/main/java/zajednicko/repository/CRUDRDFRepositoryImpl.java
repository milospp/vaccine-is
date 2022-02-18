package zajednicko.repository;

import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zajednicko.db.FusekiManager;

import java.util.Collection;

@Component
public class CRUDRDFRepositoryImpl implements CRUDRDFRepository {

    protected final FusekiManager fusekiManager;

    @Autowired
    public CRUDRDFRepositoryImpl(FusekiManager fusekiManager) {
        this.fusekiManager = fusekiManager;
    }

    @Override
    public ResultSet findAll(String graphUri) {
        return fusekiManager.getAllRDF(graphUri);
    }

    @Override
    public ResultSet findWhere(String graphUri, String whereStatement) {
        return null;
    }

    @Override
    public ResultSet findBySubject(String graphUri, String subject) {
        return null;
    }

    @Override
    public ResultSet findByPredicate(String graphUri, String predicate) {
        return null;
    }

    @Override
    public ResultSet findByObject(String graphUri, String object) {
        return null;
    }

    @Override
    public void dropRDF(String graphUri, String uri) {

    }

    @Override
    public void uploadTriplet(String graphUri, String resource, String property, String literal) {
        Model model = fusekiManager.createRDFModel(resource, property, literal);
        fusekiManager.uploadRDFModel(graphUri, model);
    }

    @Override
    public void addStatement(String graphUri, Statement statement) {
        Model model = fusekiManager.createRDFModel(statement);
        fusekiManager.uploadRDFModel(graphUri, model);
    }

    @Override
    public void addStatement(String graphUri, Collection<Statement> statement) {
        Model model = fusekiManager.createRDFModel(statement);
        fusekiManager.uploadRDFModel(graphUri, model);
    }
}
