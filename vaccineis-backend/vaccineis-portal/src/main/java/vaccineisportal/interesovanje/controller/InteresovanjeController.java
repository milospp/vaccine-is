package vaccineisportal.interesovanje.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vaccineisportal.interesovanje.service.InteresovanjeService;
import org.springframework.http.ResponseEntity;
import java.io.IOException;

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

    //@PreAuthorize("hasRole('PACIJENT')")
    @GetMapping(value = "/get-pdf")
    public ResponseEntity<byte[]> getInteresovanjePdf() throws IOException {
        return interesovanjeService.getPdf(2); // id za dok
    }

    @GetMapping(value = "/get-html")
    public ResponseEntity<byte[]> getInteresovanjeHtml() throws IOException {
        return interesovanjeService.getHtml(2);
    }
}
