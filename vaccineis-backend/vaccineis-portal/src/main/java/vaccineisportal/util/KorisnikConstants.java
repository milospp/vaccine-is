package vaccineisportal.util;

import zajednicko.model.korisnik.Korisnik;
import zajednicko.model.korisnik.STrola;

public class KorisnikConstants {

    public static Korisnik createKorisnik(String ime, String prezime, String email, String sifra, STrola role) {
        Korisnik korisnik = new Korisnik();
        korisnik.setIme(ime);
        korisnik.setPrezime(prezime);
        korisnik.setEmail(email);
        korisnik.setSifra(sifra);
        korisnik.setRola(role);
        return korisnik;
    }

    public static Korisnik createGradjanin() {
        String ime = "gradjanin";
        String prezime = "gradjanin";
        String email = "gradjanin@maildrop.cc";
        String sifra = "gradjanin";
        return createKorisnik(ime, prezime, email, sifra, STrola.GRADJANIN);
    }

    public static Korisnik createZdravstveniRadnik() {
        String ime = "zdravstveniradnik";
        String prezime = "zdravstveniradnik";
        String email = "zdravstveniradnik@maildrop.cc";
        String sifra = "zdravstveniradnik";

        return createKorisnik(ime, prezime, email, sifra, STrola.ZDRAVSTVENI_RADNIK);
    }

    public static Korisnik createSluzbenik() {
        String ime = "sluzbenik";
        String prezime = "sluzbenik";
        String email = "sluzbenik@maildrop.cc";
        String sifra = "sluzbenik";

        return createKorisnik(ime, prezime, email, sifra, STrola.SLUZBENIK);
    }
}
