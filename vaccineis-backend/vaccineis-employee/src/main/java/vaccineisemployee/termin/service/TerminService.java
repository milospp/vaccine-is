package vaccineisemployee.termin.service;

import vaccineisemployee.termin.model.Termin;

import java.time.LocalDateTime;

public interface TerminService {
    Termin addTermin(Termin termin);
    Termin getTermin(String uuid);

    LocalDateTime findFreeTermin();

}
