package vaccineisbackend.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "podaciVakcinisanog")
@XmlType(name = "CTpodaciVakcinisanog", propOrder = {"licniPodaci", "jmbg"})
public class PodaciVakcinisanog {

    @XmlElement(name = "licniPodaci", required = true)
    private LicniPodaci licniPodaci;

    @XmlElement(name = "jmbg", required = true)
    private String jmbg;

    public PodaciVakcinisanog() {}

    public PodaciVakcinisanog(LicniPodaci licniPodaci, String jmbg) {
        this.licniPodaci = licniPodaci;
        this.jmbg = jmbg;
    }

    public LicniPodaci getLicniPodaci() {
        return licniPodaci;
    }

    public void setLicniPodaci(LicniPodaci licniPodaci) {
        this.licniPodaci = licniPodaci;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }
}
