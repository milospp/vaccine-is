//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.12 at 04:19:01 PM CET 
//


package model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for STtipVakcine.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="STtipVakcine"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Pfizer-BioNTech"/&gt;
 *     &lt;enumeration value="Sputnik V (Gamaleya истраживачки центар)"/&gt;
 *     &lt;enumeration value="Sinopharm"/&gt;
 *     &lt;enumeration value="AstraZeneca"/&gt;
 *     &lt;enumeration value="Moderna"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "STtipVakcine")
@XmlEnum
public enum STtipVakcine {

    @XmlEnumValue("Pfizer-BioNTech")
    PFIZER_BIO_N_TECH("Pfizer-BioNTech"),
    @XmlEnumValue("Sputnik V (Gamaleya \u0438\u0441\u0442\u0440\u0430\u0436\u0438\u0432\u0430\u0447\u043a\u0438 \u0446\u0435\u043d\u0442\u0430\u0440)")
    SPUTNIK_V_GAMALEYA_ИСТРАЖИВАЧКИ_ЦЕНТАР("Sputnik V (Gamaleya \u0438\u0441\u0442\u0440\u0430\u0436\u0438\u0432\u0430\u0447\u043a\u0438 \u0446\u0435\u043d\u0442\u0430\u0440)"),
    @XmlEnumValue("Sinopharm")
    SINOPHARM("Sinopharm"),
    @XmlEnumValue("AstraZeneca")
    ASTRA_ZENECA("AstraZeneca"),
    @XmlEnumValue("Moderna")
    MODERNA("Moderna");
    private final String value;

    STtipVakcine(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static STtipVakcine fromValue(String v) {
        for (STtipVakcine c: STtipVakcine.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
