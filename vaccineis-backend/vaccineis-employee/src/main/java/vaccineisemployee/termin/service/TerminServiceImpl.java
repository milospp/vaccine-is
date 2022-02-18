package vaccineisemployee.termin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vaccineisemployee.termin.model.Termin;
import vaccineisemployee.termin.repository.TerminExistRepository;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class TerminServiceImpl implements TerminService{

    protected final TerminExistRepository terminExistRepository;

    @Override
    public Termin addTermin(String termin) {
        return terminExistRepository.create(termin);
    }

    @Override
    public Termin getTermin(String uuid) {
        return terminExistRepository.findOne(uuid);
    }

    @Override
    public LocalDateTime findFreeTermin() {
        return null;
    }
}
