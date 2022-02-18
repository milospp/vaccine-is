package vaccineisemployee.authentication.dto.AuthenticationRequest;

import lombok.Getter;

import javax.xml.bind.annotation.*;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "email",
        "sifra"

})
@XmlRootElement(name = "AuthenticationRequestDTO")
public class AuthenticationRequestDTO {

    @XmlElement(required = true)
    private String email;
    @XmlElement(required = true)
    private String sifra;
}
