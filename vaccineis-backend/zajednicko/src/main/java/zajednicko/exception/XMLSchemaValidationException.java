package zajednicko.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class XMLSchemaValidationException extends RuntimeException {
    public XMLSchemaValidationException(String message) {
        super(message);
    }
}
