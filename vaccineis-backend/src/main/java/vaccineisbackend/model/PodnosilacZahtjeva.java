package vaccineisbackend.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "podnosilacZahtjeva")
@XmlType(name = "CTpodaciVakcinisanog", propOrder = {"podaciVakcinisanog", "brojPasosa"})
public class PodnosilacZahtjeva {

    @XmlElement(name = "podaciVakcinisanog", required = true)
    private PodaciVakcinisanog podaciVakcinisanog;

    @XmlElement(name = "brojPasosa", required = true)
    private Integer brojPasosa;
}
