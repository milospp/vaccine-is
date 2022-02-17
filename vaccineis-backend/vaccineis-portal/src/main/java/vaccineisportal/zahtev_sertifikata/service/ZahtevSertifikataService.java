package vaccineisportal.zahtev_sertifikata.service;

import vaccineisportal.zahtev_sertifikata.model.Zahtjev;

public interface ZahtevSertifikataService {

    Zahtjev create(String xmlString);
}
