package vaccineisemployee.zajednicko.rdf;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.modify.UpdateProcessRemote;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import vaccineisemployee.zajednicko.util.AuthenticationUtilities;
import vaccineisemployee.zajednicko.util.AuthenticationUtilities.ConnectionProperties;
import vaccineisemployee.zajednicko.util.SparqlUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FusekiWriter {
    private static final String DIGITALNI_SERTIFIKAT_GRAPH_URI = "/digitalnisertifikat/metadata";

    public static void main(String[] args) throws Exception {
        run(AuthenticationUtilities.loadProperties());
    }

    public static void run(ConnectionProperties conn) throws IOException {

        System.out.println("[INFO] Loading triples from an RDF/XML to a model...");

        // RDF triples which are to be loaded into the model
        String rdfFilePath = "./src/main/resources/data/gen/grddl_metadata.rdf";

        // Creates a default model
        Model model = ModelFactory.createDefaultModel();
        model.read(rdfFilePath);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        model.write(out, SparqlUtil.NTRIPLES);

        System.out.println("[INFO] Rendering model as RDF/XML...");
        model.write(System.out, SparqlUtil.RDF_XML);

        // Delete all of the triples in all of the named graphs
        UpdateRequest request = UpdateFactory.create();
        request.add(SparqlUtil.dropAll());

        HttpContext httpContext = new BasicHttpContext();
        CredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(new AuthScope(AuthScope.ANY_HOST,
                AuthScope.ANY_PORT), new UsernamePasswordCredentials("admin", "admin"));
        httpContext.setAttribute(ClientContext.CREDS_PROVIDER, provider);
        /*
         * Create UpdateProcessor, an instance of execution of an UpdateRequest.
         * UpdateProcessor sends update request to a remote SPARQL update service.
         */
        UpdateProcessor processor = new UpdateProcessRemote(request, conn.updateEndpoint, null); //UpdateExecutionFactory.createRemote(request, conn.updateEndpoint);
        ((UpdateProcessRemote)processor).setHttpContext(httpContext);
        processor.execute();

        // Creating the first named graph and updating it with RDF data
        System.out.println("[INFO] Writing the triples to a named graph \"" + DIGITALNI_SERTIFIKAT_GRAPH_URI + "\".");
        String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + DIGITALNI_SERTIFIKAT_GRAPH_URI, new String(out.toByteArray()));
        System.out.println(sparqlUpdate);

        // UpdateRequest represents a unit of execution
        UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        //processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
        //processor.execute();
        processor = new UpdateProcessRemote(update, conn.updateEndpoint, null); //UpdateExecutionFactory.createRemote(request, conn.updateEndpoint);
        ((UpdateProcessRemote)processor).setHttpContext(httpContext);
        processor.execute();

        System.out.println("[INFO] End.");
    }
}
