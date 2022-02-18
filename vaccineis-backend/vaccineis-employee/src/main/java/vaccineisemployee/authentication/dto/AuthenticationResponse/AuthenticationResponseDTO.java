package vaccineisemployee.authentication.dto.AuthenticationResponse;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@NoArgsConstructor @AllArgsConstructor @Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "ime",
        "prezime",
        "email",
        "rola",
        "jwt"
})
@XmlRootElement(name = "AuthenticationResponseDTO")
public class AuthenticationResponseDTO {

    @XmlElement(required = false)
    private String id;
    @XmlElement(required = true)
    private String ime;
    @XmlElement(required = true)
    private String prezime;
    @XmlElement(required = true)
    private String email;
    @XmlElement(required = true)
    private String rola;
    @XmlElement(required = false)
    private String jwt;

    public AuthenticationResponseDTO(String id, String ime, String prezime, String email, String rola) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.rola = rola;
    }
}

