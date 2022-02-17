package vaccineisportal.interesovanje.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vaccineisportal.interesovanje.model.Interesovanje;
import vaccineisportal.interesovanje.service.InteresovanjeService;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/interesovanje")
public class InteresovanjeController {

    private final InteresovanjeService interesovanjeService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Interesovanje> createInteresovanje(@Valid @RequestBody Interesovanje interesovanje) {

        Interesovanje retVal = interesovanjeService.save(interesovanje);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }



//    private final InteresovanjeService interesovanjeService;
//
//    @GetMapping("test")
//    public String index() {
//        interesovanjeService.writeSomething();
//        return "Greetings from Spring Boot!";
//    }
//
//    @PreAuthorize("hasRole('PACIJENT')")
//    @GetMapping()
//    public ResponseEntity<Boolean> getInteresovanje() {
//        System.out.println("jojjjjjj");
//        return new ResponseEntity<>(true, HttpStatus.OK);
//    }

}
