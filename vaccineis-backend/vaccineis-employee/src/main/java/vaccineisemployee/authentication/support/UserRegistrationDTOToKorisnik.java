package vaccineisemployee.authentication.support;

import org.springframework.stereotype.Component;
import vaccineisemployee.authentication.dto.UserRegistration.UserRegistrationDTO;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.model.korisnik.STrola;
import zajednicko.support.AbstractConverter;

@Component
public class UserRegistrationDTOToKorisnik extends AbstractConverter<UserRegistrationDTO, Korisnik> {

    @Override
    public Korisnik convert(UserRegistrationDTO userRegistrationDTO) {
        Korisnik korisnik = new Korisnik();
        korisnik.setIme(userRegistrationDTO.getIme());
        korisnik.setPrezime(userRegistrationDTO.getPrezime());
        korisnik.setJmbg(userRegistrationDTO.getJmbg());
        korisnik.setEmail(userRegistrationDTO.getEmail());
        korisnik.setSifra(userRegistrationDTO.getSifra());
        korisnik.setRola(STrola.GRADJANIN);

        return korisnik;
    }
}
