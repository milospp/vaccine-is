package vaccineisportal.interesovanje.service;

import org.springframework.http.ResponseEntity;
import zajednicko.service.MailService;

import vaccineisportal.interesovanje.model.Interesovanje;

import java.io.IOException;

public interface InteresovanjeService {
    void setMailService(MailService mailService);

    Interesovanje save(Interesovanje interesovanje);

    public ResponseEntity<byte[]> getPdf(int id) throws IOException;

    public ResponseEntity<byte[]> getHtml(int id) throws IOException;
}

//    protected final FusekiManager fusekiManager;
//    protected final ExistManager existManager;
//    public void writeSomething() {
//        Model model = ModelFactory.createDefaultModel();
//        model.setNsPrefix("pred", "http://www.ftn.uns.ac.rs/rdf/examples/predicate/");
//
//        Resource resource = model.createResource("http://www.ftn.uns.ac.rs/rdf/examples/person/Petar_Petrovic");
//
//        Property property1 = model.createProperty("http://www.ftn.uns.ac.rs/rdf/examples/predicate/","livesIn");
//        Literal literal1 = model.createLiteral("Novi Sad BREEE");
//
//        Statement statement = model.createStatement(resource, property1, literal1);
//
//        model.add(statement);
//
////        Model model = fusekiManager.createRDFModel(statement);
//        fusekiManager.testUpload();

//    }
//=======
//@Service
//@AllArgsConstructor
//public class InteresovanjeService {
//
//    protected final FusekiManager fusekiManager;
//    protected final ExistManager existManager;
//    protected final CRUDRDFRepository crudrdfRepository;
//
//    public void writeSomething() {
//        crudrdfRepository.uploadTriplet("test", "http://www.ftn.uns.ac.rs/rdf/examples/person/Petar_Petrovic", "radi", "voli");
////        fusekiManager.testUpload();
//    }
