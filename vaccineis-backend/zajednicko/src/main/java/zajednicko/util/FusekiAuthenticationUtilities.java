package zajednicko.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zajednicko.db.AuthenticationManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * Utilities to support and simplify examples.
 */
@Component
public class FusekiAuthenticationUtilities {
	/**
	 * ExampleProperties represents the configuration for the examples.
	 */

	public String endpoint;

	public String dataset;

	public String queryEndpoint;

	public String updateEndpoint;

	public String dataEndpoint;


	public final AuthenticationManager authenticationManager;

	@Autowired
	public FusekiAuthenticationUtilities(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;

		dataset = this.authenticationManager.getFusekiDataset();
		endpoint = this.authenticationManager.getFusekiEndpoint();

		queryEndpoint = String.join("/", endpoint, dataset, this.authenticationManager.getFusekiQuery());
		updateEndpoint = String.join("/", endpoint, dataset, this.authenticationManager.getFusekiUpdate());
		dataEndpoint = String.join("/", endpoint, dataset, this.authenticationManager.getFusekiData());
	}


	/**
	 * Read the configuration properties for the example.
	 * 
	 * @return the configuration object
	 */


	/**
	 * Read a resource for an example.
	 * 
	 * @param fileName
	 *            the name of the resource
	 * @return an input stream for the resource
9	 */
//	public static InputStream openStream(String fileName) throws IOException {
//		return AuthenticationUtilities.class.getClassLoader().getResourceAsStream(fileName);
//	}
}
