//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.17 at 01:51:16 AM CET 
//


package zajednicko.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for CTvakcinacijaPodaci complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CTvakcinacijaPodaci"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.ftn.uns.ac.rs/zajednicka}STid"/&gt;
 *         &lt;element name="datumDavanjaDoze" type="{http://www.ftn.uns.ac.rs/zajednicka}STdatum"/&gt;
 *         &lt;element name="brojDoze" type="{http://www.ftn.uns.ac.rs/zajednicka}STbrojDoze"/&gt;
 *         &lt;element name="nazivVakcine" type="{http://www.ftn.uns.ac.rs/zajednicka}STtipVakcine"/&gt;
 *         &lt;element name="zdravstvenaUstanova" type="{http://www.ftn.uns.ac.rs/zajednicka}STnazivZdravstveneUstanove"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CTvakcinacijaPodaci", propOrder = {
    "id",
    "datumDavanjaDoze",
    "brojDoze",
    "nazivVakcine",
    "zdravstvenaUstanova"
})
public class CTvakcinacijaPodaci {

    @XmlElement(required = false)
    protected String id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumDavanjaDoze;
    protected int brojDoze;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected STtipVakcine nazivVakcine;
    @XmlElement(required = true)
    protected String zdravstvenaUstanova;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the datumDavanjaDoze property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumDavanjaDoze() {
        return datumDavanjaDoze;
    }

    /**
     * Sets the value of the datumDavanjaDoze property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumDavanjaDoze(XMLGregorianCalendar value) {
        this.datumDavanjaDoze = value;
    }

    /**
     * Gets the value of the brojDoze property.
     * 
     */
    public int getBrojDoze() {
        return brojDoze;
    }

    /**
     * Sets the value of the brojDoze property.
     * 
     */
    public void setBrojDoze(int value) {
        this.brojDoze = value;
    }

    /**
     * Gets the value of the nazivVakcine property.
     * 
     * @return
     *     possible object is
     *     {@link STtipVakcine }
     *     
     */
    public STtipVakcine getNazivVakcine() {
        return nazivVakcine;
    }

    /**
     * Sets the value of the nazivVakcine property.
     * 
     * @param value
     *     allowed object is
     *     {@link STtipVakcine }
     *     
     */
    public void setNazivVakcine(STtipVakcine value) {
        this.nazivVakcine = value;
    }

    /**
     * Gets the value of the zdravstvenaUstanova property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZdravstvenaUstanova() {
        return zdravstvenaUstanova;
    }

    /**
     * Sets the value of the zdravstvenaUstanova property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZdravstvenaUstanova(String value) {
        this.zdravstvenaUstanova = value;
    }

}
