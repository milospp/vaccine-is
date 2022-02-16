package vaccineisemployee.digitalni_sertifikat.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/digitalni-sertifikat")
public class DigitalniSertifikatController {

    @GetMapping()
    public ResponseEntity<Boolean> getDigitalniSertifikat() {
        System.out.println("jojjjjjj");
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
