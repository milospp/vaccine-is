//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.17 at 01:51:16 AM CET 
//


package vaccineisemployee.termin.model;

import zajednicko.model.BaseModel;
import zajednicko.model.CTlicniPodaci;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.ftn.uns.ac.rs/zajednicka}STid"/&gt;
 *         &lt;element name="korisnik" type="{http://www.ftn.uns.ac.rs/zajednicka}CTlicniPodaci"/&gt;
 *         &lt;element name="datumVrijeme" type="{http://www.ftn.uns.ac.rs/zajednicka}STdatumVrijeme"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "korisnik",
    "datumVrijeme"
})
@XmlRootElement(name = "termin")
public class Termin extends BaseModel {

    @XmlElement(required = true)
    protected CTlicniPodaci korisnik;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar datumVrijeme;

    /**
     * Gets the value of the korisnik property.
     * 
     * @return
     *     possible object is
     *     {@link CTlicniPodaci }
     *     
     */
    public CTlicniPodaci getKorisnik() {
        return korisnik;
    }

    /**
     * Sets the value of the korisnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link CTlicniPodaci }
     *     
     */
    public void setKorisnik(CTlicniPodaci value) {
        this.korisnik = value;
    }

    /**
     * Gets the value of the datumVrijeme property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumVrijeme() {
        return datumVrijeme;
    }

    /**
     * Sets the value of the datumVrijeme property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumVrijeme(XMLGregorianCalendar value) {
        this.datumVrijeme = value;
    }

}
