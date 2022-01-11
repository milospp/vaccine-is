package vaccineisbackend.model;


import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "potvrdaOVakcinaciji")
@XmlType(name = "PotvrdaOVakcinaciji")
public class PotvrdaOVakcinaciji {

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"podaciVakcinisanog", "vakcinacijaPodaci", "qrKod"})
    public static class PotvrdaVakcinacije {

        // TODO: Lista ovih zajednickijh objekata kako??

        protected PodaciVakcinisanog podaciVakcinisanog;

        protected VakcinacijaPodaci vakcinacijaPodaci;

        protected String qrKod;

        @XmlAttribute(name = "sifra", required = true)
        protected String sifra;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class VakcinacijaPodaciPotvrda {

        private VakcinacijaPodaci vakcinacijaPodaci;
    }
}
