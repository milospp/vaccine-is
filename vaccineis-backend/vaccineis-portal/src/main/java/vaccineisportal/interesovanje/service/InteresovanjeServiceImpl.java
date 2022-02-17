package vaccineisportal.interesovanje.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vaccineisportal.interesovanje.model.Interesovanje;
import vaccineisportal.interesovanje.repository.InteresovanjeExistRepository;

@AllArgsConstructor
@Service
public class InteresovanjeServiceImpl implements InteresovanjeService {

    private InteresovanjeExistRepository interesovanjeExistRepository;

    @Override
    public Interesovanje create(String xmlString) {
        return interesovanjeExistRepository.create(xmlString);
    }
}
