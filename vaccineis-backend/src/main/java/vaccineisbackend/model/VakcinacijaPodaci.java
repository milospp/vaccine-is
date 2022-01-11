package vaccineisbackend.model;


import javax.xml.bind.annotation.*;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vakcinacijaPodaci")
@XmlType(name = "CTvakcinacijaPodaci", propOrder = {"datumDavanjaDoze", "brojDoze", "nazivVakcine", "zdravstvenaUstanova"})
public class VakcinacijaPodaci {

    @XmlElement(name = "datumDavanjaDoze", required = true)
    private LocalDate datumDavanjaDoze;

    @XmlElement(name = "brojDoze", required = true)
    private Integer brojDoze;

    @XmlElement(name = "nazivVakcine", required = true)
    private String nazivVakcine;

    @XmlElement(name = "zdravstvenaUstanova", required = true)
    private String zdravstvenaUstanova;
}
