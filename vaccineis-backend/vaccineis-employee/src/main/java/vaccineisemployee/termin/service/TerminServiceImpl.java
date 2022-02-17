package vaccineisemployee.termin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vaccineisemployee.termin.model.Termin;
import vaccineisemployee.termin.repository.TerminExistRepository;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class TerminServiceImpl implements TerminService{
    protected final TerminExistRepository terminExistRepository;


    @Override
    public Termin addTermin(Termin termin) {
        return terminExistRepository.save(termin);
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
