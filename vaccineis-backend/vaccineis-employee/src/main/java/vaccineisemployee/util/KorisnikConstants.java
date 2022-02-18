package vaccineisemployee.util;

import zajednicko.model.korisnik.Korisnik;
import zajednicko.model.korisnik.STrola;

public class KorisnikConstants {

    public static Korisnik getSluzbenik() {
        String ime = "Sluzbenik";
        String prezime = "Sluzbenovic";
        String email = "sluzbenik@gmail.com";
        String sifra = "sluzbenik123";

        Korisnik korisnik = new Korisnik();
        korisnik.setIme(ime);
        korisnik.setPrezime(prezime);
        korisnik.setEmail(email);
        korisnik.setSifra(sifra);
        korisnik.setRola(STrola.SLUZBENIK);
        return korisnik;
    }
}
