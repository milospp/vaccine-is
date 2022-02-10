package vaccineisbackend.service;

import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import vaccineisbackend.digitalni_sertifikat.repository.ExistDigitalniSertifikatRepository;
import vaccineisbackend.izvestaj.repository.ExistIzvestajRepository;
import vaccineisbackend.obrazac_saglasnosti.repository.ExistObrazacSaglasnostiRepository;
import vaccineisbackend.potvrda_o_vakcinaciji.repository.ExistPotvrdaOVakcinacijiRepository;
import vaccineisbackend.rdf.FusekiWriter;
import vaccineisbackend.util.AuthenticationUtilities;
import vaccineisbackend.zahtev_sertifikata.repository.ExistZahtevSertifikataRepository;

import java.io.IOException;

@Service
public class XMLService {

    private final ExistDigitalniSertifikatRepository existDigitalniSertifikatRepository;
    private final ExistIzvestajRepository existIzvestajRepository;
    private final ExistObrazacSaglasnostiRepository existObrazacSaglasnostiRepository;
    private final ExistPotvrdaOVakcinacijiRepository existPotvrdaOVakcinacijiRepository;
    private final ExistZahtevSertifikataRepository existZahtevSertifikataRepository;

    public XMLService(ExistDigitalniSertifikatRepository existDigitalniSertifikatRepository, ExistIzvestajRepository existIzvestajRepository, ExistObrazacSaglasnostiRepository
            existObrazacSaglasnostiRepository, ExistPotvrdaOVakcinacijiRepository existPotvrdaOVakcinacijiRepository,
                      ExistZahtevSertifikataRepository existZahtevSertifikataRepository) {
        this.existDigitalniSertifikatRepository = existDigitalniSertifikatRepository;
        this.existIzvestajRepository = existIzvestajRepository;
        this.existObrazacSaglasnostiRepository = existObrazacSaglasnostiRepository;
        this.existPotvrdaOVakcinacijiRepository = existPotvrdaOVakcinacijiRepository;
        this.existZahtevSertifikataRepository = existZahtevSertifikataRepository;
    }
    public void writeToRdfDatabase() throws IOException {
        FusekiWriter.run(AuthenticationUtilities.loadProperties());
    }

//    public Object findIzvestaji() throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//         ResourceSet izvestaji = existIzvestajRepository.findAll();
//         return izvestaji.getResource(0).getContent();
//    }

//    public Object findDigitlniSertifikat() throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//        ResourceSet izvestaji = existDigitalniSertifikatRepository.findAll();
//        return izvestaji.getResource(0).getContent();
//    }

    public void saveDigitalniSertifikatFileFromString(String text) throws XMLDBException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        existDigitalniSertifikatRepository.saveDigitalniSertifikat(text);
    }

    public void saveIzvestajFileFromString(String text) throws XMLDBException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        existIzvestajRepository.saveIzvestaj(text);
    }

    public void saveObrazacSaglasnostiFileFromString(String text) throws XMLDBException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        existObrazacSaglasnostiRepository.saveObrazacSaglasnosti(text);
    }

    public void savePotvrdaOVakcinacijiFileFromString(String text) throws XMLDBException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        existPotvrdaOVakcinacijiRepository.savePotvrdaOVakcinaciji(text);
    }

    public void saveZahtevSertifikataFileFromString(String text) throws XMLDBException, ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        existZahtevSertifikataRepository.saveZahtevSertifikata(text);
    }

}
