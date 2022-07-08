package vaccineisportal.interesovanje.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import vaccineisportal.authentication.service.AuthenticationService;
import vaccineisportal.interesovanje.model.Interesovanje;
import vaccineisportal.interesovanje.service.InteresovanjeService;

import zajednicko.model.docdatas.DocDatas;
import zajednicko.model.korisnik.Korisnik;
import zajednicko.service.UserService;

import javax.annotation.security.PermitAll;
import javax.websocket.server.PathParam;
import zajednicko.service.MailService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;


@AllArgsConstructor
@RestController
@RequestMapping("/api/interesovanje")
public class InteresovanjeController {

    private final InteresovanjeService interesovanjeService;
    private final MailService mailService;
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('GRADJANIN')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Interesovanje> createInteresovanje(@RequestBody String interesovanje) throws IOException {
        Interesovanje retVal = interesovanjeService.create(interesovanje);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PermitAll
//    @PreAuthorize("hasRole('GRADJANIN')")
    @GetMapping(value = "/get-pdf/{uuid}")
    public ResponseEntity<?> getInteresovanjePdf(@PathVariable("uuid") String uuid) throws IOException, ParserConfigurationException, SAXException {
        return interesovanjeService.getPdf(uuid); // id za dok
    }

    //    @PreAuthorize("hasRole('GRADJANIN')")
    @GetMapping(value = "/get-html/{uuid}")
    public ResponseEntity<?> getInteresovanjeHtml(@PathVariable("uuid") String uuid) throws IOException, ParserConfigurationException, SAXException {
        return interesovanjeService.getHtml(uuid);
    }

    @PermitAll
    @GetMapping(value = "/get-rdf/{uuid}")
    public ResponseEntity<?> getInteresovanjeRdf(@PathVariable("uuid") String uuid) {
        String xml = interesovanjeService.getRdfXml(uuid);
        return new ResponseEntity(xml, HttpStatus.OK);
    }


    @PermitAll
    @GetMapping(value = "/get-json/{uuid}")
    public ResponseEntity<?> getInteresovanjeJson(@PathVariable("uuid") String uuid) {
        String json = interesovanjeService.getRdfJson(uuid);
        return new ResponseEntity(json, HttpStatus.OK);
    }


    //    @PreAuthorize("hasRole('GRADJANIN')")
    @GetMapping(value = "/moja-interesovanja", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<DocDatas> getMojaInteresovanja
    () throws IOException, ParserConfigurationException, SAXException {
        Korisnik korisnik = authenticationService.getLoggedInUser();

        DocDatas interesovanjes = interesovanjeService.getInteresovanjaByUser(korisnik.getId());

        return new ResponseEntity<>(interesovanjes, HttpStatus.OK);
    }


    @PermitAll
    @GetMapping(value = "/korisnik/{uuid}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<DocDatas> getKorisnikInteresovanja (@PathVariable("uuid") String uuid) throws IOException, ParserConfigurationException, SAXException {

        DocDatas interesovanjes = interesovanjeService.getInteresovanjaByUser(uuid);

        return new ResponseEntity<>(interesovanjes, HttpStatus.OK);
    }

    @PermitAll
    @GetMapping(value = "/broj-primljenih", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getBrojPrimljenih(@RequestParam String dateStart, @RequestParam String dateEnd) {

        Integer retVal = (int) interesovanjeService.findAll().stream()
                .filter(i ->
                        LocalDate.of(i.getDatum().getYear(),
                                i.getDatum().getMonth(),
                                i.getDatum().getDay()
                        ).isAfter(LocalDate.parse(dateStart)) &&
                                LocalDate.of(i.getDatum().getYear(),
                                        i.getDatum().getMonth(),
                                        i.getDatum().getDay()
                                ).isBefore(LocalDate.parse(dateEnd)))
                .count();

        System.out.println(retVal);

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }
}
