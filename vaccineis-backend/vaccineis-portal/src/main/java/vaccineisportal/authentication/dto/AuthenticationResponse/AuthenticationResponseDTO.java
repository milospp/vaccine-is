package vaccineisportal.authentication.dto.AuthenticationResponse;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;


@NoArgsConstructor @AllArgsConstructor @Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "ime",
        "prezime",
        "jmbg",
        "email",
        "rola",
        "jwt"
})
@XmlRootElement(name = "AuthenticationResponseDTO")
public class AuthenticationResponseDTO {

    @XmlElement(required = true)
    private String ime;
    @XmlElement(required = true)
    private String prezime;
    @XmlElement(required = true)
    private String jmbg;
    @XmlElement(required = true)
    private String email;
    @XmlElement(required = true)
    private String rola;
    @XmlElement(required = false)
    private String jwt;

    public AuthenticationResponseDTO(String ime, String prezime, String jmbg, String email, String rola) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.email = email;
        this.rola = rola;
    }
}
