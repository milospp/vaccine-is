package zajednicko.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManager {

    @Value("${conn.user}")
    private String user;
    @Value("${conn.password}")
    private String password;
    @Value("${conn.host}")
    private String host;
    @Value("${conn.port}")
    private String port;
    @Value("${conn.driver}")
    private String driver;
    @Value("${conn.uri}")
    private String uri;

    @Value("${fuseki.endpoint}")
    public String fusekiEndpoint;

    @Value("${fuseki.dataset}")
    public String fusekiDataset;

    @Value("${fuseki.query}")
    public String fusekiQuery;

    @Value("${fuseki.update}")
    public String fusekiUpdate;

    @Value("${fuseki.data}")
    public String fusekiData;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getFusekiEndpoint() {
        return fusekiEndpoint;
    }

    public void setFusekiEndpoint(String fusekiEndpoint) {
        this.fusekiEndpoint = fusekiEndpoint;
    }

    public String getFusekiDataset() {
        return fusekiDataset;
    }

    public void setFusekiDataset(String fusekiDataset) {
        this.fusekiDataset = fusekiDataset;
    }

    public String getFusekiQuery() {
        return fusekiQuery;
    }

    public void setFusekiQuery(String fusekiQuery) {
        this.fusekiQuery = fusekiQuery;
    }

    public String getFusekiUpdate() {
        return fusekiUpdate;
    }

    public void setFusekiUpdate(String fusekiUpdate) {
        this.fusekiUpdate = fusekiUpdate;
    }

    public String getFusekiData() {
        return fusekiData;
    }

    public void setFusekiData(String fusekiData) {
        this.fusekiData = fusekiData;
    }
}
