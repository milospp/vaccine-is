package vaccineisportal.bridge_controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/digitalni-sertifikat")
public class DigitalniSertifikatController {
    private RestTemplate restTemplate;
    private final DigitalniSertifikatService digitalniSertifikatService;

    //    @PreAuthorize("hasRole('GRADJANIN')")
    @GetMapping(value = "/get-pdf")
    public ResponseEntity<?> getInteresovanjePdf() throws IOException {
        return digitalniSertifikatService.getPdf(2); // id za dok
    }

    //    @PreAuthorize("hasRole('GRADJANIN')")
    @GetMapping(value = "/get-html")
    public ResponseEntity<?> getInteresovanjeHtml() throws IOException {
        return digitalniSertifikatService.getHtml(2);
    }
}
