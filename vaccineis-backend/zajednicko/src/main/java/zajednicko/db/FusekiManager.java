package zajednicko.db;

import lombok.Synchronized;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.*;
import org.apache.jena.sparql.modify.UpdateProcessRemote;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zajednicko.model.util.ResultSetConnection;
import zajednicko.util.FusekiAuthenticationUtilities;
import zajednicko.util.SparqlUtil;
import zajednicko.util.ZajednickoUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;


@Component
public class FusekiManager {
    private static final String PERSON_NAMED_GRAPH_URI = "/example/person/metadata";


    private HttpContext httpContext;

    private final FusekiAuthenticationUtilities conn;

    @Autowired
    public FusekiManager(FusekiAuthenticationUtilities conn) throws IOException {
        this.conn = conn;
        this.httpContext = getCredentialsContext();
    }

    public HttpContext getCredentialsContext() {
        HttpContext httpContext = new BasicHttpContext();
        CredentialsProvider provider = new BasicCredentialsProvider();
        //  TODO: Load from properties
        provider.setCredentials(new AuthScope(AuthScope.ANY_HOST,
                AuthScope.ANY_PORT), new UsernamePasswordCredentials("admin", "admin"));
        httpContext.setAttribute(ClientContext.CREDS_PROVIDER, provider);
        return httpContext;
    }

    public void uploadRDFModel(String graphUri, Model model) {
        if (graphUri != null && !graphUri.startsWith("/")) graphUri = "/" + graphUri;

        model.write(System.out, SparqlUtil.RDF_XML);

        // Issuing the SPARQL update...
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        model.write(out, SparqlUtil.NTRIPLES);

        // Updating the named graph with the triples from RDF model
        String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + graphUri, out.toString());

        // UpdateRequest represents a unit of execution
        UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        // UpdateProcessor sends update request to a remote SPARQL update service.
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
        ((UpdateProcessRemote)processor).setHttpContext(httpContext);
        processor.execute();
    }

    public void deleteRDFModel(String graphUri, Model model) {
        if (graphUri != null && !graphUri.startsWith("/")) graphUri = "/" + graphUri;

        model.write(System.out, SparqlUtil.RDF_XML);

        // Issuing the SPARQL update...
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        model.write(out, SparqlUtil.NTRIPLES);

        // Updating the named graph with the triples from RDF model
        String sparqlUpdate = SparqlUtil.deleteData(conn.dataEndpoint + graphUri, out.toString());

        // UpdateRequest represents a unit of execution
        UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        // UpdateProcessor sends update request to a remote SPARQL update service.
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
        ((UpdateProcessRemote)processor).setHttpContext(httpContext);
        processor.execute();
    }

    public void deleteRDFQuery(String graphUri, String query) {
        if (graphUri != null && !graphUri.startsWith("/")) graphUri = "/" + graphUri;

        String sparqlUpdate = SparqlUtil.deleteWhere(conn.dataEndpoint + graphUri, query);

        UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        // UpdateProcessor sends update request to a remote SPARQL update service.
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
        ((UpdateProcessRemote)processor).setHttpContext(httpContext);
        processor.execute();

    }

    public Model createRDFModel (Statement statement) {
        Model model = ModelFactory.createDefaultModel();
        model.setNsPrefix("pred", ZajednickoUtil.RDF_PREDICATE);

        model.add(statement);

        return model;
    }


    public Model createRDFModel (Collection<Statement> statements) {
        Model model = ModelFactory.createDefaultModel();
        model.setNsPrefix("pred", ZajednickoUtil.RDF_PREDICATE);

        for(Statement statement : statements) {
            model.add(statement);
        }

        return model;
    }

    public Model createRDFModel (String resource, String propery, String literal) {

        Model model = ModelFactory.createDefaultModel();
        model.setNsPrefix("pred", ZajednickoUtil.RDF_PREDICATE);

        Resource resourceData = model.createResource(resource);

        Property property1 = model.createProperty(ZajednickoUtil.RDF_PREDICATE,propery);

        Statement statement;
        if (literal.startsWith("http://")) {
            Resource resource2 = model.createResource(literal);
            statement = model.createStatement(resourceData, property1, resource2);
        } else {
            Literal literal1 = model.createLiteral(literal);
            statement = model.createStatement(resourceData, property1, literal1);
        }

        model.add(statement);

        return model;
    }

    public ResultSetConnection getAllRDF(String uri) {
        return queryRDF(uri, "?s ?p ?o");

    }

    @Synchronized
    public ResultSetConnection queryRDF(String uri, String whereQuery) {
        if (uri != null && !uri.startsWith("/")) uri = "/" + uri;

        String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + uri, whereQuery);

        // Create a QueryExecution that will access a SPARQL service over HTTP
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

        // Query the SPARQL endpoint, iterate over the result set...

        System.out.println("START query = " + whereQuery);
        ResultSet results = query.execSelect();
        System.out.println("END query = " + whereQuery);
        return new ResultSetConnection(results, query);

    }

    public void testUpload() {
        HttpContext httpContext = new BasicHttpContext();
        CredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(new AuthScope(AuthScope.ANY_HOST,
                AuthScope.ANY_PORT), new UsernamePasswordCredentials("admin", "admin"));
        httpContext.setAttribute(ClientContext.CREDS_PROVIDER, provider);

        System.out.println("[INFO] Loading UPDATE triples from an RDF/XML to a model...");

        // RDF triples which are to be loaded into the model
        String rdfFilePath = "data/rdf/person_update.rdf";

        // Creates a default model
        Model model = ModelFactory.createDefaultModel();
        model.setNsPrefix("pred", ZajednickoUtil.RDF_PREDICATE);

        // Loading the changes from an RDF/XML
//        model.read(rdfFilePath);

        // Making the changes manually
        Resource resource = model.createResource("http://www.ftn.uns.ac.rs/rdf/examples/person/Petar_Petrovic");

        Property property1 = model.createProperty(ZajednickoUtil.RDF_PREDICATE, "livesIn");
        Literal literal1 = model.createLiteral("Novi Sad");

        Property property2 = model.createProperty(ZajednickoUtil.RDF_PREDICATE, "profession");
        Literal literal2 = model.createLiteral("lawyer");

        Property property3 = model.createProperty(ZajednickoUtil.RDF_PREDICATE, "hobby");
        Literal literal3 = model.createLiteral("hiking");

        // Adding the statements to the model
        Statement statement1 = model.createStatement(resource, property1, literal1);
        Statement statement2 = model.createStatement(resource, property2, literal2);
        Statement statement3 = model.createStatement(resource, property3, literal3);

        model.add(statement1);
        model.add(statement2);
        model.add(statement3);

        System.out.println("[INFO] Rendering the UPDATE model as RDF/XML...");
        model.write(System.out, SparqlUtil.RDF_XML);

        // Issuing the SPARQL update...
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        model.write(out, SparqlUtil.NTRIPLES);

        // Updating the named graph with the triples from RDF model
        System.out.println("[INFO] Inserting the triples to a named graph \"" + PERSON_NAMED_GRAPH_URI + "\".");
        String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + PERSON_NAMED_GRAPH_URI, new String(out.toByteArray()));
        System.out.println(sparqlUpdate);

        // UpdateRequest represents a unit of execution
        UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        // UpdateProcessor sends update request to a remote SPARQL update service.
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
        ((UpdateProcessRemote)processor).setHttpContext(httpContext);
        processor.execute();
    }
}
