package vaccineisportal.zahtev_sertifikata.model;

import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaZahteva", propOrder = {
        "zahtevi"
})
@XmlRootElement(name = "listaZahteva")
public class ListaZahtjeva {

    @XmlElement(required = true)
    List<Zahtjev> zahtevi;

    public List<Zahtjev> getZahtevi() {
        if (zahtevi == null) {
            zahtevi = new ArrayList<>();
        }
        return zahtevi;
    }
}