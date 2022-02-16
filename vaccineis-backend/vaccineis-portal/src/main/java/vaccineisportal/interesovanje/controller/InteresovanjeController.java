package vaccineisportal.interesovanje.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vaccineisportal.interesovanje.service.InteresovanjeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@AllArgsConstructor
@RestController
@RequestMapping("/api/interesovanje")
public class InteresovanjeController {

    private final InteresovanjeService interesovanjeService;

    @GetMapping("test")
    public String index() {
        interesovanjeService.writeSomething();
        return "Greetings from Spring Boot!";
    }

    @PreAuthorize("hasRole('PACIJENT')")
    @GetMapping()
    public ResponseEntity<Boolean> getInteresovanje() {
        System.out.println("jojjjjjj");
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
