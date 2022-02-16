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
    public void writeSomething() {
        Model model = ModelFactory.createDefaultModel();
        model.setNsPrefix("pred", "http://www.ftn.uns.ac.rs/rdf/examples/predicate/");

        Resource resource = model.createResource("http://www.ftn.uns.ac.rs/rdf/examples/person/Petar_Petrovic");

        Property property1 = model.createProperty("http://www.ftn.uns.ac.rs/rdf/examples/predicate/","livesIn");
        Literal literal1 = model.createLiteral("Novi Sad BREEE");

        Statement statement = model.createStatement(resource, property1, literal1);

        model.add(statement);

//        Model model = fusekiManager.createRDFModel(statement);
        fusekiManager.testUpload();
    }
}
