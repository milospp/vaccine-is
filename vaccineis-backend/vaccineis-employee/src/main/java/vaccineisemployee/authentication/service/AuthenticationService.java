package vaccineisemployee.authentication.service;


import zajednicko.model.korisnik.Korisnik;

public interface AuthenticationService {
    Korisnik getLoggedInUser();
}
