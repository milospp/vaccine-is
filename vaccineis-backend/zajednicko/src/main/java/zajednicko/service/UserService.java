package zajednicko.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import zajednicko.model.korisnik.Korisnik;

public interface UserService extends UserDetailsService {

    Korisnik findUserByEmail(String email);

    Korisnik save(Korisnik korisnik);
}
