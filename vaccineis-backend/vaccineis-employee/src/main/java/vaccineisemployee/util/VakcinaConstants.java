package vaccineisemployee.util;

import zajednicko.model.STtipVakcine;
import zajednicko.model.vakcina.Vakcina;

import java.util.ArrayList;
import java.util.List;

public class VakcinaConstants {

    public static List<Vakcina> getSveDefaultVakcine() {
        Vakcina pfizer = new Vakcina();
        pfizer.setNaziv(STtipVakcine.PFIZER_BIO_N_TECH);
        pfizer.setKolicina(3);

        Vakcina sputnik = new Vakcina();
        sputnik.setNaziv(STtipVakcine.SPUTNIK_V_GAMALEYA_ИСТРАЖИВАЧКИ_ЦЕНТАР);
        sputnik.setKolicina(3);

        Vakcina sinopharm = new Vakcina();
        sinopharm.setNaziv(STtipVakcine.SINOPHARM);
        sinopharm.setKolicina(3);

        Vakcina astraZenic = new Vakcina();
        astraZenic.setNaziv(STtipVakcine.ASTRA_ZENECA);
        astraZenic.setKolicina(3);

        Vakcina moderna = new Vakcina();
        moderna.setNaziv(STtipVakcine.MODERNA);
        moderna.setKolicina(3);

        List<Vakcina> vakcine = new ArrayList<>();
        vakcine.add(pfizer);
        vakcine.add(sputnik);
        vakcine.add(sinopharm);
        vakcine.add(astraZenic);
        vakcine.add(moderna);

        return vakcine;
    }
}
