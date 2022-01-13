package vaccineisbackend.service;

import org.springframework.stereotype.Service;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import vaccineisbackend.model.izvjestaj.Izvjestaj;
import vaccineisbackend.rdf.FusekiWriter;
import vaccineisbackend.repository.*;
import vaccineisbackend.util.AuthenticationUtilities;

import java.io.IOException;
import java.util.Collection;

@Service
public class XMLService {

    private final DigitalniSertifikatRepository digitalniSertifikatRepository;
    private final InteresovanjeRepository interesovanjeRepository;
    private final IzvestajRepository izvestajRepository;
    private final ObrazacSaglasnostiRepository obrazacSaglasnostiRepository;
    private final PotvrdaOVakcinacijiRepository potvrdaOVakcinacijiRepository;
    private final ZahtevSertifikataRepository zahtevSertifikataRepository;

    public XMLService(DigitalniSertifikatRepository digitalniSertifikatRepository, InteresovanjeRepository
            interesovanjeRepository, IzvestajRepository izvestajRepository, ObrazacSaglasnostiRepository
            obrazacSaglasnostiRepository, PotvrdaOVakcinacijiRepository potvrdaOVakcinacijiRepository,
                      ZahtevSertifikataRepository zahtevSertifikataRepository) {
        this.digitalniSertifikatRepository = digitalniSertifikatRepository;
        this.interesovanjeRepository = interesovanjeRepository;
        this.izvestajRepository = izvestajRepository;
        this.obrazacSaglasnostiRepository = obrazacSaglasnostiRepository;
        this.potvrdaOVakcinacijiRepository = potvrdaOVakcinacijiRepository;
        this.zahtevSertifikataRepository = zahtevSertifikataRepository;
    }
    public void writeToRdfDatabase() throws IOException {
        FusekiWriter.run(AuthenticationUtilities.loadProperties());
    }

    public Object findIzvestaji() throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
         ResourceSet izvestaji = izvestajRepository.findAll();
         return izvestaji.getResource(0).getContent();
    }

    public Object findDigitlniSertifikat() throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ResourceSet izvestaji = digitalniSertifikatRepository.findAll();
        return izvestaji.getResource(0).getContent();
    }

    public void saveDigitalniSertifikatFileFromString(String text) throws XMLDBException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        digitalniSertifikatRepository.saveDigitalniSertifikat(text);
    }

    public void saveInteresovanjeFileFromString(String text) throws XMLDBException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        interesovanjeRepository.saveInteresovanje(text);
    }

    public void saveIzvestajFileFromString(String text) throws XMLDBException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        izvestajRepository.saveIzvestaj(text);
    }

    public void saveObrazacSaglasnostiFileFromString(String text) throws XMLDBException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        obrazacSaglasnostiRepository.saveObrazacSaglasnosti(text);
    }

    public void savePotvrdaOVakcinacijiFileFromString(String text) throws XMLDBException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        potvrdaOVakcinacijiRepository.savePotvrdaOVakcinaciji(text);
    }

    public void saveZahtevSertifikataFileFromString(String text) throws XMLDBException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        zahtevSertifikataRepository.saveZahtevSertifikata(text);
    }

}
