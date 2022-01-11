package vaccineisbackend.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "kontakt")
@XmlType(name = "CTkontakt", propOrder = {"ime", "prezime", "pol", "datumRodjenja"})
public class Kontakt {

    @XmlElement(name = "fiksniTelefon", required = true)
    private String fiksniTelefon;

    @XmlElement(name = "mobilniTelefon", required = true)
    private String mobilniTelefon;

    @XmlElement(name = "email", required = true)
    private String email;
}
