package zajednicko.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor @Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "xmllist", propOrder = {
        "elements"
})
@XmlRootElement(name = "xmllist")
public class XMLList<T> {

    @XmlElement(required = true)
    private List<T> elements;

    public List<T> getElements() {
        if (elements == null) {
            elements = new ArrayList<>();
        }
        return elements;
    }
}
