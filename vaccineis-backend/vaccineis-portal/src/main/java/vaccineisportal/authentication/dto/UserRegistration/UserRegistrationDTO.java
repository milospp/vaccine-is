package vaccineisportal.authentication.dto.UserRegistration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "ime",
        "prezime",
        "email",
        "sifra",
        "rola"
})
@XmlRootElement(name = "UserRegistrationDTO")
public class UserRegistrationDTO {

    @XmlElement(required = true)
    private String ime;
    @XmlElement(required = true)
    private String prezime;
    @XmlElement(required = true)
    private String email;
    @XmlElement(required = true)
    private String sifra;
    @XmlElement(required = true)
    private String rola;
}
