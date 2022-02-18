package zajednicko.repository;

import lombok.extern.slf4j.Slf4j;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zajednicko.db.FusekiManager;
import zajednicko.util.ZajednickoUtil;

import java.util.Collection;

@Slf4j
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
        return fusekiManager.queryRDF(graphUri, whereStatement);
    }

    @Override
    public ResultSet findBySubject(String graphUri, String subject) {
        String query = "<" + subject + ">" + " ?p ?o";
        return fusekiManager.queryRDF(graphUri, query);
    }

    @Override
    public ResultSet findByPredicate(String graphUri, String predicate) {
        String query =  "?s " + "<" + predicate + ">" + " ?o";
        return fusekiManager.queryRDF(graphUri, query);
    }

    @Override
    public ResultSet findByObject(String graphUri, String object) {
        String query = "?s ?p " + "\"" + object + "\"";
        return fusekiManager.queryRDF(graphUri, query);
    }

    @Override
    public void dropRDF(String graphUri, String uri) {

    }

    @Override
    public void uploadTriplet(String graphUri, String resource, String property, String literal) {
        Model model = fusekiManager.createRDFModel(ZajednickoUtil.XML_PREFIX + resource, property, literal);
        fusekiManager.uploadRDFModel(graphUri, model);
    }

    @Override
    public void addStatement(String graphUri, Statement statement) {
        Model model = fusekiManager.createRDFModel(statement);
        fusekiManager.uploadRDFModel(ZajednickoUtil.XML_PREFIX + graphUri, model);
    }

    @Override
    public void addStatement(String graphUri, Collection<Statement> statement) {
        Model model = fusekiManager.createRDFModel(statement);
        fusekiManager.uploadRDFModel(ZajednickoUtil.XML_PREFIX + graphUri, model);
    }
}
