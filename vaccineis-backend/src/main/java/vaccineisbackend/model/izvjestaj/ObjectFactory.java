//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.01.12 at 04:19:01 PM CET 
//


package vaccineisbackend.model.izvjestaj;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the vaccineisbackend.model.izvjestaj package.
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: vaccineisbackend.model.izvjestaj
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Izvjestaj }
     * 
     */
    public Izvjestaj createIzvjestaj() {
        return new Izvjestaj();
    }

    /**
     * Create an instance of {@link Izvjestaj.RaspodjelaDoza }
     * 
     */
    public Izvjestaj.RaspodjelaDoza createIzvjestajRaspodjelaDoza() {
        return new Izvjestaj.RaspodjelaDoza();
    }

    /**
     * Create an instance of {@link Izvjestaj.DozeVakcinaInfo }
     * 
     */
    public Izvjestaj.DozeVakcinaInfo createIzvjestajDozeVakcinaInfo() {
        return new Izvjestaj.DozeVakcinaInfo();
    }

    /**
     * Create an instance of {@link Izvjestaj.Period }
     * 
     */
    public Izvjestaj.Period createIzvjestajPeriod() {
        return new Izvjestaj.Period();
    }

    /**
     * Create an instance of {@link Izvjestaj.SertifikatInfo }
     * 
     */
    public Izvjestaj.SertifikatInfo createIzvjestajSertifikatInfo() {
        return new Izvjestaj.SertifikatInfo();
    }

    /**
     * Create an instance of {@link Izvjestaj.RaspodjelaDoza.TipVakcineInfo }
     * 
     */
    public Izvjestaj.RaspodjelaDoza.TipVakcineInfo createIzvjestajRaspodjelaDozaTipVakcineInfo() {
        return new Izvjestaj.RaspodjelaDoza.TipVakcineInfo();
    }

    /**
     * Create an instance of {@link Izvjestaj.DozeVakcinaInfo.DozaInfo }
     * 
     */
    public Izvjestaj.DozeVakcinaInfo.DozaInfo createIzvjestajDozeVakcinaInfoDozaInfo() {
        return new Izvjestaj.DozeVakcinaInfo.DozaInfo();
    }

}
