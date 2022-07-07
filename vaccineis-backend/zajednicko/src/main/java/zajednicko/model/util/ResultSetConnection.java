package zajednicko.model.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.ResultSet;

@Getter
@Setter
@AllArgsConstructor
public class ResultSetConnection {
    private ResultSet resultSet;
    private QueryExecution queryExecution;

    public void closeConnection() {
        this.queryExecution.close();
    }

}
