package vaccineisportal.zahtev_sertifikata.dto;

import lombok.Getter;
import lombok.Setter;
import vaccineisportal.zahtev_sertifikata.model.Zahtjev;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

@Getter @Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "ime",
        "prezime",
        "pol",
        "jmbg",
        "datumRodjenja",
        "brojPasosa",
        "razlog",
        "mjestoDatum"
})
@XmlRootElement(name = "zahtjev")
public class ZahtjevDTO {

    @XmlElement(required = true)
    protected String ime;
    @XmlElement(required = true)
    protected String prezime;
    @XmlElement(required = true)
    protected String pol;
    @XmlElement(required = true)
    protected String jmbg;
    @XmlElement(required = true)
    protected XMLGregorianCalendar datumRodjenja;
    @XmlElement(required = true)
    protected String brojPasosa;
    @XmlElement(required = true)
    protected String razlog;
    @XmlElement(required = true)
    protected Zahtjev.MjestoDatum mjestoDatum;
}
