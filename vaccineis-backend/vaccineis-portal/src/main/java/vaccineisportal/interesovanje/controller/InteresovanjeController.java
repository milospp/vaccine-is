package vaccineisportal.interesovanje.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/interesovanje")
public class InteresovanjeController {

    @PreAuthorize("hasRole('PACIJENT')")
    @GetMapping()
    public ResponseEntity<Boolean> getInteresovanje() {
        System.out.println("jojjjjjj");
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
