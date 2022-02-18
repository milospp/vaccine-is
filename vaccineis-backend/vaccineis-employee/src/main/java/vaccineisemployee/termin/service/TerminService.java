package vaccineisemployee.termin.service;

import vaccineisemployee.termin.model.Termin;

import java.time.LocalDateTime;

public interface TerminService {
    Termin addTermin(String termin);
    Termin getTermin(String uuid);

    LocalDateTime findFreeTermin();

}
