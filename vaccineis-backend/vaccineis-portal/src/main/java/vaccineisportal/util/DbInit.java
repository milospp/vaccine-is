package vaccineisportal.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import zajednicko.exception.BadRequestException;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.service.MarshallingService;
import zajednicko.service.UserService;

import javax.annotation.PostConstruct;

@AllArgsConstructor
@Component
public class DbInit {

    private final UserService userService;
    private final MarshallingService marshallingService;

    @PostConstruct
    private void postConstruct() {

        Korisnik gradjanin = KorisnikConstants.createGradjanin();
        Korisnik zdravstveniRadnik = KorisnikConstants.createZdravstveniRadnik();

        try {
            userService.create(marshallingService.marshall(zdravstveniRadnik, Korisnik.class));
            userService.create(marshallingService.marshall(gradjanin, Korisnik.class));
        } catch (BadRequestException e) {
            System.out.println("Korisnik je vec dodat.");
        }
    }
}