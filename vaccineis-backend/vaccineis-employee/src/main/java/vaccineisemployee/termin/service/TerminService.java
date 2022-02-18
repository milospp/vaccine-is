package vaccineisemployee.termin.service;

import vaccineisemployee.termin.model.Termin;
import zajednicko.model.CTlicniPodaci;

import javax.xml.datatype.DatatypeConfigurationException;
import java.time.LocalDateTime;
import java.util.List;

public interface TerminService {
    Termin addTermin(Termin termin);
    Termin getTermin(String uuid);
    List<Termin> findTermin(String query);
    List<Termin> getAll();

    //TODO: Testirati
    Termin zakaziPrviSlovodan(CTlicniPodaci cTlicniPodaci) throws DatatypeConfigurationException;

    void saveMetadata(Termin termin);
}
