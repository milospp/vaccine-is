package zajednicko.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zajednicko.exception.NotFoundException;
import zajednicko.model.vakcina.Vakcina;
import zajednicko.repository.VakcinaExistRepository;
import zajednicko.service.VakcinaService;

import java.util.Optional;

@AllArgsConstructor
@Service
public class VakcinaServiceImpl implements VakcinaService {

    private final VakcinaExistRepository vakcinaExistRepository;

    @Override
    public void addKolicina(String nazivVakcine, Integer kolicina) {
        Optional<Vakcina> vakcina = vakcinaExistRepository.findAll().stream().filter(v -> v.getNaziv().value().equals(nazivVakcine)).findFirst();

        if (vakcina.isEmpty())
            throw new NotFoundException("Vakcina " + nazivVakcine + "is not found");

        vakcina.get().setKolicina(vakcina.get().getKolicina() + kolicina);
        vakcinaExistRepository.save(vakcina.get());
    }
}
