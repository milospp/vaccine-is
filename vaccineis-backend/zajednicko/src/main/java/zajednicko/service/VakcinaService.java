package zajednicko.service;

import zajednicko.model.STtipVakcine;
import zajednicko.model.vakcina.Vakcina;

import java.util.List;

public interface VakcinaService {

    List<Vakcina> findAll();

    Vakcina create(String xmlString);

    void addKolicina(STtipVakcine naziv, Integer kolicina);

    Vakcina update(STtipVakcine naziv, Integer kolicina);
}
