//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.12 at 04:19:01 PM CET 
//


package vaccineisbackend.digitalni_sertifikat.model;

import vaccineisbackend.model.zajednicka.CTvakcinacijaPodaci;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for CTvakcinacijaPodaciSertifikat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CTvakcinacijaPodaciSertifikat"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence maxOccurs="unbounded" minOccurs="2"&gt;
 *         &lt;element name="doza"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="vakcinaPodaci" type="{http://www.ftn.uns.ac.rs/zajednicka}CTvakcinacijaPodaci"/&gt;
 *                   &lt;element name="proizvodjac" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="serijaVakcine" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CTvakcinacijaPodaciSertifikat", propOrder = {
    "doza"
})
public class CTvakcinacijaPodaciSertifikat {

    @XmlElement(required = true)
    protected List<Doza> doza;

    /**
     * Gets the value of the doza property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the doza property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDoza().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Doza }
     * 
     * 
     */
    public List<Doza> getDoza() {
        if (doza == null) {
            doza = new ArrayList<Doza>();
        }
        return this.doza;
    }


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
     *         &lt;element name="vakcinaPodaci" type="{http://www.ftn.uns.ac.rs/zajednicka}CTvakcinacijaPodaci"/&gt;
     *         &lt;element name="proizvodjac" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="serijaVakcine" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "vakcinaPodaci",
        "proizvodjac",
        "serijaVakcine"
    })
    public static class Doza {

        @XmlElement(required = true)
        protected CTvakcinacijaPodaci vakcinaPodaci;
        @XmlElement(required = true)
        protected String proizvodjac;
        @XmlElement(required = true)
        protected String serijaVakcine;

        /**
         * Gets the value of the vakcinaPodaci property.
         * 
         * @return
         *     possible object is
         *     {@link CTvakcinacijaPodaci }
         *     
         */
        public CTvakcinacijaPodaci getVakcinaPodaci() {
            return vakcinaPodaci;
        }

        /**
         * Sets the value of the vakcinaPodaci property.
         * 
         * @param value
         *     allowed object is
         *     {@link CTvakcinacijaPodaci }
         *     
         */
        public void setVakcinaPodaci(CTvakcinacijaPodaci value) {
            this.vakcinaPodaci = value;
        }

        /**
         * Gets the value of the proizvodjac property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProizvodjac() {
            return proizvodjac;
        }

        /**
         * Sets the value of the proizvodjac property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProizvodjac(String value) {
            this.proizvodjac = value;
        }

        /**
         * Gets the value of the serijaVakcine property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSerijaVakcine() {
            return serijaVakcine;
        }

        /**
         * Sets the value of the serijaVakcine property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSerijaVakcine(String value) {
            this.serijaVakcine = value;
        }

    }

}