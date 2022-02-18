package vaccineisportal.authentication.service;


import zajednicko.model.korisnik.Korisnik;

public interface AuthenticationService {
    Korisnik getLoggedInUser();
}
