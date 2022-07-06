package vaccineisemployee.vakcina.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zajednicko.model.STtipVakcine;

import javax.xml.bind.annotation.*;

@NoArgsConstructor @AllArgsConstructor @Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vakcinaDTO", propOrder = {
        "id",
        "naziv",
        "kolicina"
})
@XmlRootElement(name = "vakcinaKolicina")
public class VakcinaDTO {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected STtipVakcine naziv;
    @XmlElement(required = true)
    protected Integer kolicina;
}
