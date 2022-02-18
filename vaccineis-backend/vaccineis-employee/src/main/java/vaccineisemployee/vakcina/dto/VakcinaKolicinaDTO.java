package vaccineisemployee.vakcina.dto;

import lombok.Getter;

import javax.xml.bind.annotation.*;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "nazivVakcine",
        "kolicina"
})
@XmlRootElement(name = "vakcinaKolicina")
public class VakcinaKolicinaDTO {

    @XmlElement(required = true)
    protected String nazivVakcine;
    @XmlElement(required = true)
    protected Integer kolicina;
}
