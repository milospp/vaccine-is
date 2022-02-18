package vaccineisemployee.authentication.service;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import zajednicko.model.korisnik.Korisnik;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    public Korisnik getLoggedInUser()  {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof Korisnik) {
            return (Korisnik) principal;
        } else {
            return null;
        }
    }
}
