package vaccineisemployee.vakcina.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vakcinaDTO", propOrder = {
        "pfizer",
        "moderna",
        "astra",
        "sinopharm",
        "sputnik"
})
@XmlRootElement(name = "vakcinaKolicina")
public class BrojVakcinaPoTipu {
    @XmlElement(required = true)
    protected Integer pfizer;
    @XmlElement(required = true)
    protected Integer moderna;
    @XmlElement(required = true)
    protected Integer astra;
    @XmlElement(required = true)
    protected Integer sinopharm;
    @XmlElement(required = true)
    protected Integer sputnik;
}
