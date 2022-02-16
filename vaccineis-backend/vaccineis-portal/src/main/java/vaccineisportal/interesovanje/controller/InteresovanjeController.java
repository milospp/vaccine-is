package vaccineisportal.interesovanje.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vaccineisportal.interesovanje.service.InteresovanjeService;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/interesovanje")
public class InteresovanjeController {

    private final InteresovanjeService interesovanjeService;

    @GetMapping("")
    public String index() {
        interesovanjeService.writeSomething();
        return "Greetings from Spring Boot!";
    }
}
