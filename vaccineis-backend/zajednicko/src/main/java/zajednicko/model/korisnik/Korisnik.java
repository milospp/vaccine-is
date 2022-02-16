//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.02.15 at 09:34:48 PM CET 
//


package zajednicko.model.korisnik;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.util.Collection;
import java.util.List;


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
 *         &lt;element name="ime" type="{http://www.ftn.uns.ac.rs/zajednicka}STime"/&gt;
 *         &lt;element name="prezime" type="{http://www.ftn.uns.ac.rs/zajednicka}STprezime"/&gt;
 *         &lt;element name="email" type="{http://www.ftn.uns.ac.rs/zajednicka}STemail"/&gt;
 *         &lt;element name="sifra" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="jmbg" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="rola" type="{http://www.ftn.uns.ac.rs/korisnik}STrola"/&gt;
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
    "ime",
    "prezime",
    "email",
    "sifra",
    "jmbg",
    "rola"
})
@XmlRootElement(name = "korisnik")
public class Korisnik implements UserDetails {

    @XmlElement(required = true)
    protected String ime;
    @XmlElement(required = true)
    protected String prezime;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String sifra;
    @XmlElement(required = true)
    protected String jmbg;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected STrola rola;

    /**
     * Gets the value of the ime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIme() {
        return ime;
    }

    /**
     * Sets the value of the ime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIme(String value) {
        this.ime = value;
    }

    /**
     * Gets the value of the prezime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Sets the value of the prezime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrezime(String value) {
        this.prezime = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the sifra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifra() {
        return sifra;
    }

    /**
     * Sets the value of the sifra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifra(String value) {
        this.sifra = value;
    }

    /**
     * Gets the value of the jmbg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJmbg() {
        return jmbg;
    }

    /**
     * Sets the value of the jmbg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJmbg(String value) {
        this.jmbg = value;
    }

    /**
     * Gets the value of the rola property.
     * 
     * @return
     *     possible object is
     *     {@link STrola }
     *     
     */
    public STrola getRola() {
        return rola;
    }

    /**
     * Sets the value of the rola property.
     * 
     * @param value
     *     allowed object is
     *     {@link STrola }
     *     
     */
    public void setRola(STrola value) {
        this.rola = value;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.rola);
    }

    @Override
    public String getPassword() {
        return sifra;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
