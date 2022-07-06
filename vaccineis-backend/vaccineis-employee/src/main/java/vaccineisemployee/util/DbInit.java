package vaccineisemployee.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import zajednicko.exception.BadRequestException;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.model.vakcina.Vakcina;
import zajednicko.service.MarshallingService;
import zajednicko.service.UserService;
import zajednicko.service.VakcinaService;

import javax.annotation.PostConstruct;
import java.util.List;

@AllArgsConstructor
@Component
public class DbInit {

    private final VakcinaService vakcinaService;
    private final UserService userService;
    private final MarshallingService marshallingService;

    @PostConstruct
    private void postConstruct() {

        // KORISNIK
        Korisnik korisnik = KorisnikConstants.getSluzbenik();
        try {
            userService.create(marshallingService.marshall(korisnik, Korisnik.class));
        } catch (BadRequestException e) {
            System.out.println("korisnik dodat");
        }

        // VAKCINE
        List<Vakcina> vakcine = VakcinaConstants.getSveDefaultVakcine();
        for (Vakcina vakcina: vakcine) {
            try {
                vakcinaService.addKolicina(vakcina.getNaziv(), 1);
            } catch (Exception e) {
                vakcinaService.create(marshallingService.marshall(vakcina, Vakcina.class));
            }
        }
    }
}