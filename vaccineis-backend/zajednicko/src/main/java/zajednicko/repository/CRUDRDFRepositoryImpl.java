package zajednicko.repository;

import lombok.AllArgsConstructor;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import zajednicko.db.ExistManager;
import zajednicko.db.FusekiManager;
import zajednicko.service.MarshallingService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

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
