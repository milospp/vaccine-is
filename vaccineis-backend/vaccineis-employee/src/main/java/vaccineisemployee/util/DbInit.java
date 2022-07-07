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

        Korisnik sluzbenik = KorisnikConstants.createSluzbenik();

        try {
            userService.create(marshallingService.marshall(sluzbenik, Korisnik.class));
        } catch (BadRequestException e) {
            System.out.println("Korisnici su vec dodati u bazu.");
        }

        // VAKCINE
        List<Vakcina> vakcine = VakcinaConstants.getSveDefaultVakcine();
        for (Vakcina vakcina: vakcine) {
            try {
                vakcinaService.addKolicina(vakcina.getNaziv(), vakcina.getKolicina());
            } catch (Exception e) {
                vakcinaService.create(marshallingService.marshall(vakcina, Vakcina.class));
            }
        }
    }
}