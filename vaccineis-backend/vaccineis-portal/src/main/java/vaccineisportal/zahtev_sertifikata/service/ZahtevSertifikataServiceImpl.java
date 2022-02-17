package vaccineisportal.zahtev_sertifikata.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vaccineisportal.zahtev_sertifikata.model.Zahtjev;
import vaccineisportal.zahtev_sertifikata.repository.ZahtevSertifikataExistRepository;

@AllArgsConstructor
@Service
public class ZahtevSertifikataServiceImpl implements ZahtevSertifikataService {

    private ZahtevSertifikataExistRepository zahtevSertifikataExistRepository;

    @Override
    public Zahtjev create(String xmlString) {
        return zahtevSertifikataExistRepository.create(xmlString);
    }
}
