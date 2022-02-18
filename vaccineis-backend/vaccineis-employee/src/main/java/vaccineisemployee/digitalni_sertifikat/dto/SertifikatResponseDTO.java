package vaccineisemployee.digitalni_sertifikat.dto;

import lombok.Getter;

import javax.xml.bind.annotation.*;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "decision",
        "message",
        "requestId"
})
@XmlRootElement(name = "SertifikatResponseDTO")
public class SertifikatResponseDTO {

    @XmlElement(required = true)
    private String decision;
    @XmlElement(required = true)
    private String message;
    @XmlElement(required = true)
    private String requestId;
}
