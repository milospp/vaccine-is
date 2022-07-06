package vaccineisemployee.vakcina.dto;

import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vakcinaKolicina", propOrder = {
        "vakcine"
})
@XmlRootElement(name = "vakcinaKolicina")
public class ListaVakcinaDTO {

    @XmlElement(required = true)
    List<VakcinaDTO> vakcine;

    public List<VakcinaDTO> getVakcine() {
        if (vakcine == null) {
            vakcine = new ArrayList<>();
        }
        return vakcine;
    }
}
