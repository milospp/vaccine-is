package vaccineisportal.obrazac_saglasnosti.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter @Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "saglasnosti",
})
@XmlRootElement(name = "listasaglasnosti")
public class ListaSaglasnosti {

    @XmlElement(required = true)
    protected List<Saglasnost> saglasnosti;
}
