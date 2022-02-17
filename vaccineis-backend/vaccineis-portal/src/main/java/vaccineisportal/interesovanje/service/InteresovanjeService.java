package vaccineisportal.interesovanje.service;


import vaccineisportal.interesovanje.model.Interesovanje;

public interface InteresovanjeService {

    Interesovanje save(Interesovanje interesovanje);

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
}
