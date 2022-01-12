//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.12 at 04:19:01 PM CET 
//


package vaccineisbackend.model.potvrda_o_vakcinaciji;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import vaccineisbackend.model.zajednicka.CTvakcinacijaPodaci;


/**
 * <p>Java class for CTvakcinacijaPodaciPotvrda complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CTvakcinacijaPodaciPotvrda"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;sequence maxOccurs="unbounded"&gt;
 *           &lt;element name="vakcinaPodaci" type="{http://www.ftn.uns.ac.rs/zajednicka}CTvakcinacijaPodaci"/&gt;
 *         &lt;/sequence&gt;
 *         &lt;element name="datumIzdavanjaPotvrde" type="{http://www.ftn.uns.ac.rs/zajednicka}STdatum"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CTvakcinacijaPodaciPotvrda", propOrder = {
    "vakcinaPodaci",
    "datumIzdavanjaPotvrde"
})
public class CTvakcinacijaPodaciPotvrda {

    @XmlElement(required = true)
    protected List<CTvakcinacijaPodaci> vakcinaPodaci;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumIzdavanjaPotvrde;

    /**
     * Gets the value of the vakcinaPodaci property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vakcinaPodaci property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVakcinaPodaci().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CTvakcinacijaPodaci }
     * 
     * 
     */
    public List<CTvakcinacijaPodaci> getVakcinaPodaci() {
        if (vakcinaPodaci == null) {
            vakcinaPodaci = new ArrayList<CTvakcinacijaPodaci>();
        }
        return this.vakcinaPodaci;
    }

    /**
     * Gets the value of the datumIzdavanjaPotvrde property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumIzdavanjaPotvrde() {
        return datumIzdavanjaPotvrde;
    }

    /**
     * Sets the value of the datumIzdavanjaPotvrde property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumIzdavanjaPotvrde(XMLGregorianCalendar value) {
        this.datumIzdavanjaPotvrde = value;
    }

}
