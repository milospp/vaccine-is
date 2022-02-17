package zajednicko.util;

import lombok.Getter;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;

@Getter
public class XMLSchemaValidationHandler implements ValidationEventHandler {

    private String message;

    public XMLSchemaValidationHandler(String defaultMessage) {
        this.message = defaultMessage;
    }

    public boolean handleEvent(ValidationEvent event) {

        if (event.getSeverity() != ValidationEvent.WARNING) {
            ValidationEventLocator validationEventLocator = event.getLocator();
            message = "ERROR: Line " + validationEventLocator.getLineNumber()
                    + ": Col" + validationEventLocator.getColumnNumber() + ": "
                    + event.getMessage();

            System.out.println(message);
            return false;
        }

        else {
            ValidationEventLocator validationEventLocator = event.getLocator();
            message = "WARNING: Line " + validationEventLocator.getLineNumber()
                    + ": Col" + validationEventLocator.getColumnNumber() + ": "
                    + event.getMessage();

            System.out.println(message);
            return true;
        }
    }
}
