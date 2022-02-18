package zajednicko.service;

import zajednicko.model.vakcina.Vakcina;

import java.util.List;

public interface VakcinaService {

    List<Vakcina> findAll();

    Vakcina create(String xmlString);

    void addKolicina(String nazivVakcine, Integer kolicina);
}
