package vaccineisbackend.service;

import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import vaccineisbackend.repository.InteresovanjeRepository;

@Service
public class XMLService {

    private final InteresovanjeRepository interesovanjeRepo;

    public XMLService(InteresovanjeRepository interesovanjeRepo) {
        this.interesovanjeRepo = interesovanjeRepo;
    }

//    public void saveInteresovanjeFileFromString(String text) throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//        interesovanjeRepo.saveInteresovanje(text);
//    }

}
