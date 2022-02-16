package vaccineisportal.interesovanje.service;

import lombok.AllArgsConstructor;
import org.apache.jena.rdf.model.*;
import org.springframework.stereotype.Service;
import zajednicko.db.ExistManager;
import zajednicko.db.FusekiManager;
import zajednicko.repository.CRUDRDFRepository;
import zajednicko.repository.CRUDRDFRepositoryImpl;
import zajednicko.repository.CRUDRepository;

@Service
@AllArgsConstructor
public class InteresovanjeService {

    protected final FusekiManager fusekiManager;
    protected final ExistManager existManager;
    protected final CRUDRDFRepository crudrdfRepository;

    public void writeSomething() {
        crudrdfRepository.uploadTriplet("test", "http://www.ftn.uns.ac.rs/rdf/examples/person/Petar_Petrovic", "radi", "voli");
//        fusekiManager.testUpload();
    }
}
