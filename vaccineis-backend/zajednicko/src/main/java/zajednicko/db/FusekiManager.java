package zajednicko.db;

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
import org.springframework.stereotype.Component;
import zajednicko.util.AuthenticationUtilities;
import zajednicko.util.SparqlUtil;

import javax.swing.plaf.nimbus.State;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;


@Component
public class FusekiManager {
    private static final String PERSON_NAMED_GRAPH_URI = "/example/person/metadata";
    private static final String PREDICATE_NAMESPACE = "http://www.ftn.uns.ac.rs/rdf/examples/predicate/";


    private HttpContext httpContext;
    AuthenticationUtilities.ConnectionProperties conn;


    public FusekiManager() throws IOException {
        this.httpContext = getCredentialsContext();
        this.conn = AuthenticationUtilities.loadProperties();
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

    public void uploadRDFModel(Model model) {
        model.write(System.out, SparqlUtil.RDF_XML);

        // Issuing the SPARQL update...
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        model.write(out, SparqlUtil.NTRIPLES);

        // Updating the named graph with the triples from RDF model
        String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + PERSON_NAMED_GRAPH_URI, new String(out.toByteArray()));

        // UpdateRequest represents a unit of execution
        UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        // UpdateProcessor sends update request to a remote SPARQL update service.
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
        ((UpdateProcessRemote)processor).setHttpContext(httpContext);
        processor.execute();
    }

    public Model createRDFModel (Statement statement) {
        Model model = ModelFactory.createDefaultModel();
        model.setNsPrefix("pred", PREDICATE_NAMESPACE);

        model.add(statement);

        return model;
    }


    public Model createRDFModel (Collection<Statement> statements) {
        Model model = ModelFactory.createDefaultModel();
        model.setNsPrefix("pred", PREDICATE_NAMESPACE);

        for(Statement statement : statements) {
            model.add(statement);
        }

        return model;
    }

    public ResultSet getAllRDF(String uri) {
        String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + PERSON_NAMED_GRAPH_URI, "?s ?p ?o");

        // Create a QueryExecution that will access a SPARQL service over HTTP
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

        // Query the SPARQL endpoint, iterate over the result set...
        ResultSet results = query.execSelect();

        return results;

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
        model.setNsPrefix("pred", PREDICATE_NAMESPACE);

        // Loading the changes from an RDF/XML
//        model.read(rdfFilePath);

        // Making the changes manually
        Resource resource = model.createResource("http://www.ftn.uns.ac.rs/rdf/examples/person/Petar_Petrovic");

        Property property1 = model.createProperty(PREDICATE_NAMESPACE, "livesIn");
        Literal literal1 = model.createLiteral("Novi Sad");

        Property property2 = model.createProperty(PREDICATE_NAMESPACE, "profession");
        Literal literal2 = model.createLiteral("lawyer");

        Property property3 = model.createProperty(PREDICATE_NAMESPACE, "hobby");
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
