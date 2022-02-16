//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.15 at 09:34:48 PM CET 
//


package zajednicko.model.korisnik;

import org.springframework.security.core.GrantedAuthority;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for STrola.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="STrola"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="GRADJANIN"/&gt;
 *     &lt;enumeration value="ZDRAVSTVENI_RADNIK"/&gt;
 *     &lt;enumeration value="SLUZBENIK"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "STrola")
@XmlEnum
public enum STrola implements GrantedAuthority {

    GRADJANIN,
    ZDRAVSTVENI_RADNIK,
    SLUZBENIK;

    public String value() {
        return name();
    }

    public static STrola fromValue(String v) {
        return valueOf(v);
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
