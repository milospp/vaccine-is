package vaccineisbackend.model;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "licniPodaci")
@XmlType(name = "CTlicniPodaci", propOrder = {"ime", "prezime", "pol", "datumRodjenja"})
public class LicniPodaci {

    @XmlElement(name = "ime", required = true)
    private String ime;

    @XmlElement(name = "prezime", required = true)
    private String prezime;

    @XmlElement(name = "pol", required = true)
    private String pol;

    @XmlElement(name = "datumRodjenja", required = true)
    private LocalDate datumRodjenja;

    public LicniPodaci() {}

    public LicniPodaci(String ime, String prezime, String pol, LocalDate datumRodjenja) {
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }
}
