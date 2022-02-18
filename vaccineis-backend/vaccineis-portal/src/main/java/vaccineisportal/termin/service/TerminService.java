package vaccineisportal.termin.service;

import vaccineisportal.interesovanje.model.Interesovanje;
import vaccineisportal.termin.model.Termin;
import zajednicko.model.CTlicniPodaci;

import javax.xml.datatype.DatatypeConfigurationException;
import java.time.LocalDateTime;
import java.util.List;

public interface TerminService {
    Termin addTermin(String termin);
    Termin getTermin(String uuid);
    List<Termin> findTermin(String query);
    List<Termin> getAll();


    void terminZaInteresovanje();

    //TODO: Testirati
    Termin zakaziPrviSlobodan(CTlicniPodaci cTlicniPodaci) throws DatatypeConfigurationException;

    void saveMetadata(Termin termin);
}
