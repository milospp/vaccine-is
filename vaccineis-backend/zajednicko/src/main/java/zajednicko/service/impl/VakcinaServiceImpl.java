package zajednicko.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import zajednicko.exception.NotFoundException;
import zajednicko.exception.XMLSchemaValidationException;
import zajednicko.model.STtipVakcine;
import zajednicko.model.vakcina.Vakcina;
import zajednicko.repository.VakcinaExistRepository;
import zajednicko.service.VakcinaService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class VakcinaServiceImpl implements VakcinaService {

    private final VakcinaExistRepository vakcinaExistRepository;

    @Override
    public List<Vakcina> findAll() {
        return vakcinaExistRepository.findAll();
    }

    @Override
    public Vakcina create(String xmlString) {
        return vakcinaExistRepository.create(xmlString);
    }

    @Override
    public void addKolicina(STtipVakcine naziv, Integer kolicina) {
        Optional<Vakcina> vakcina = vakcinaExistRepository.findAll().stream().filter(v -> v.getNaziv().equals(naziv)).findFirst();

        if (vakcina.isEmpty()) {
            System.out.println("emptyyyyy");
            throw new NotFoundException("Vakcina " + naziv + "is not found");
        }
        vakcina.get().setKolicina(vakcina.get().getKolicina() + kolicina);
        vakcinaExistRepository.save(vakcina.get());
    }

    public Vakcina update(STtipVakcine naziv, Integer kolicina) {
        Optional<Vakcina> vakcina = vakcinaExistRepository.findAll().stream().filter(v -> v.getNaziv().equals(naziv)).findFirst();
        if (vakcina.isEmpty()) {
            System.out.println("emptyyyyy");
            throw new NotFoundException("Vakcina " + naziv + "is not found");
        }

        Vakcina retVal = vakcina.get();
        retVal.setKolicina(kolicina);
        vakcinaExistRepository.save(retVal);
        return retVal;
    }
}
