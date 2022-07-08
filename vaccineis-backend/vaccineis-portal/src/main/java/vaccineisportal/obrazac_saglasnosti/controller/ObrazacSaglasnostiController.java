package vaccineisportal.obrazac_saglasnosti.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vaccineisportal.authentication.service.AuthenticationService;
import vaccineisportal.obrazac_saglasnosti.dto.BrojVakcinaPoTipu;
import vaccineisportal.obrazac_saglasnosti.model.ListaSaglasnosti;
import vaccineisportal.obrazac_saglasnosti.model.Saglasnost;
import vaccineisportal.obrazac_saglasnosti.service.ObrazacSaglasnostiService;
import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.service.MarshallingService;
import zajednicko.service.UserService;

import javax.annotation.security.PermitAll;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/saglasnost")
public class ObrazacSaglasnostiController {

    private final ObrazacSaglasnostiService obrazacSaglasnostiService;
    private final AuthenticationService authenticationService;
    private final MarshallingService marshallingService;
    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('GRADJANIN')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Saglasnost> createObrazacSaglasnosti(@RequestBody String obrazacSaglasnosti) {

        Saglasnost retVal = obrazacSaglasnostiService.create(obrazacSaglasnosti);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PermitAll
    //@PreAuthorize("hasRole('PACIJENT')")
    @GetMapping(value = "/get-pdf/{uuid}")
    public ResponseEntity<?> getInteresovanjePdf(@PathVariable("uuid") String uuid) throws IOException {
        return obrazacSaglasnostiService.getPdf(uuid);
    }

    @PermitAll
    @GetMapping(value = "/get-html/{uuid}")
    public ResponseEntity<?> getInteresovanjeHtml(@PathVariable("uuid") String uuid) throws IOException {
        return obrazacSaglasnostiService.getHtml(uuid);
    }

    @PermitAll
    @GetMapping(value = "/get-rdf/{uuid}")
    public ResponseEntity<?> getInteresovanjeRdf(@PathVariable("uuid") String uuid) {
        String xml = obrazacSaglasnostiService.getRdfXml(uuid);
        return new ResponseEntity(xml, HttpStatus.OK);
    }

    @PermitAll
    @GetMapping(value = "/get-json/{uuid}")
    public ResponseEntity<?> getInteresovanjeJson(@PathVariable("uuid") String uuid) {
        String json = obrazacSaglasnostiService.getRdfJson(uuid);
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @PermitAll
    @GetMapping(value = "/moji-obrasci", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<DocDatas> getMojeSaglasnosti() {
        Korisnik korisnik = authenticationService.getLoggedInUser();

        DocDatas interesovanjes = obrazacSaglasnostiService.getObrasciByUser(korisnik.getId());

        return new ResponseEntity<>(interesovanjes, HttpStatus.OK);
    }

    @PermitAll
    @GetMapping(value = "/korisnik/{uuid}", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<DocDatas> getKorisnikSaglasnosti(@PathVariable("uuid") String uuid) {
        Korisnik korisnik = userService.findUserByUuid(uuid);

        DocDatas interesovanjes = obrazacSaglasnostiService.getObrasciByUser(korisnik.getId());

        return new ResponseEntity<>(interesovanjes, HttpStatus.OK);
    }

    @GetMapping(value = "/podnete-saglasnosti", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ListaSaglasnosti> getPodnetiObrasciSaglasnosti() {
        List<Saglasnost> saglasnosti = obrazacSaglasnostiService.findAll().stream().filter(o -> o.getEvidencijaVakcinacije() == null).collect(Collectors.toList());

        ListaSaglasnosti retVal = new ListaSaglasnosti();
        retVal.setSaglasnosti(saglasnosti);

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PermitAll
    @GetMapping(value = "/broj-vakcina", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<BrojVakcinaPoTipu> getBrojPrimljenihVakcina(@RequestParam String dateStart, @RequestParam String dateEnd) {
        List<Saglasnost> saglasnosti = obrazacSaglasnostiService.findAll().stream()
                .filter(o -> o.getEvidencijaVakcinacije() != null &&

                        LocalDate.of(o.getEvidencijaVakcinacije()
                                        .getPodaciVakcinacija()
                                        .getVakcinacija()
                                        .get(0)
                                        .getDatumDavanjaVakcine().getYear(),
                                    o.getEvidencijaVakcinacije()
                                        .getPodaciVakcinacija()
                                        .getVakcinacija()
                                        .get(0)
                                        .getDatumDavanjaVakcine().getMonth(),
                                    o.getEvidencijaVakcinacije()
                                        .getPodaciVakcinacija()
                                        .getVakcinacija()
                                        .get(0)
                                        .getDatumDavanjaVakcine().getDay()).isBefore(LocalDate.parse(dateEnd)) &&

                            LocalDate.of(o.getEvidencijaVakcinacije()
                                            .getPodaciVakcinacija()
                                            .getVakcinacija()
                                            .get(0)
                                            .getDatumDavanjaVakcine().getYear(),
                                    o.getEvidencijaVakcinacije()
                                            .getPodaciVakcinacija()
                                            .getVakcinacija()
                                            .get(0)
                                            .getDatumDavanjaVakcine().getMonth(),
                                    o.getEvidencijaVakcinacije()
                                            .getPodaciVakcinacija()
                                            .getVakcinacija()
                                            .get(0)
                                            .getDatumDavanjaVakcine().getDay()).isAfter(LocalDate.parse(dateStart))
                        ).collect(Collectors.toList());

        BrojVakcinaPoTipu retVal = new BrojVakcinaPoTipu(0, 0, 0, 0, 0);

        for (var el: saglasnosti) {
            for (var vakcina: el.getEvidencijaVakcinacije().getPodaciVakcinacija().getVakcinacija()) {
                if (vakcina.getNazivVakcine().value().equals("Pfizer-BioNTech"))
                    retVal.setPfizer(retVal.getPfizer() + 1);
                if (vakcina.getNazivVakcine().value().equals("Sputnik V (Gamaleya истраживачки центар)"))
                    retVal.setSputnik(retVal.getSputnik() + 1);
                if (vakcina.getNazivVakcine().value().equals("Sinopharm"))
                    retVal.setSinopharm(retVal.getSinopharm() + 1);
                if (vakcina.getNazivVakcine().value().equals("AstraZeneca"))
                    retVal.setAstra(retVal.getAstra() + 1);
                if (vakcina.getNazivVakcine().value().equals("Moderna"))
                    retVal.setModerna(retVal.getModerna() + 1);
            }
        }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ZDRAVSTVENI_RADNIK')")
    @PutMapping(value = "/odgovori-na-obrazac/{id}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Saglasnost> zameniObrazacSaglasnosti(@PathVariable String id, @RequestBody String obrazacSaglasnosti) {

        Saglasnost saglasnost = obrazacSaglasnostiService.update(id, obrazacSaglasnosti);
        return new ResponseEntity<>(saglasnost, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Saglasnost> getObrazacById(@PathVariable String id) {
        System.out.println(id);
        Saglasnost retVal = obrazacSaglasnostiService.findOne(id);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }
}
