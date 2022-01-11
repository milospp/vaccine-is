package vaccineisbackend.model;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "zahtevZaSertifikat")
@XmlType(name = "zahtjev", propOrder = {"podnosilacZahtjeva", "razlog", "mestoDatum", "location", "datum"})
public class ZahtevZaSertifikat {

    @XmlElement(name = "podnosilac", required = true)
    private PodnosilacZahtjeva podnosilacZahtjeva;

    @XmlElement(name = "razlog", required = true)
    private String razlog;

    @XmlElement(name = "mjestoDatum", required = true)
    private LocalDate mestoDatum;

    @XmlElement(name = "lokacija", required = true)
    private String location;

    @XmlElement(name = "datum", required = true)
    private LocalDate datum;
}
