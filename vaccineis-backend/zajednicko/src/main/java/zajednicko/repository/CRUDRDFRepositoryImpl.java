package zajednicko.repository;

import lombok.AllArgsConstructor;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;
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

@AllArgsConstructor
@Component
public abstract class CRUDRDFRepositoryImpl {
    protected final FusekiManager fusekiManager;

//    @Override
    public ResultSet findAll(String graphUri) {
        return fusekiManager.getAllRDF(graphUri);
    }

//    @Override
    public ResultSet findWhere(String whereStatement) {
        return null;
    }

//    @Override
    public ResultSet findBySubject(String graphUri, String subject) {
        return null;
    }

//    @Override
    public ResultSet findByPredicate(String graphUri, String predicate) {
        return null;
    }

//    @Override
    public ResultSet findByObject(String graphUri, String object) {
        return null;
    }

//    @Override
    public void dropRDF(String graphUri, String uri) {

    }

//    @Override
    public void addStatement(Statement statement) {
        Model model = fusekiManager.createRDFModel(statement);
        fusekiManager.uploadRDFModel(model);
    }

//    @Override
    public void addStatement(Collection<Statement> statement) {
        Model model = fusekiManager.createRDFModel(statement);
        fusekiManager.uploadRDFModel(model);
    }
}
