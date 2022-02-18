package vaccineisemployee.vakcina.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vaccineisemployee.vakcina.dto.VakcinaKolicinaDTO;
import zajednicko.model.vakcina.Vakcina;
import zajednicko.service.VakcinaService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/vakcine")
public class VakcinaController {

    private final VakcinaService vakcinaService;

    @PreAuthorize("hasAnyAuthority('SLUZBENIK')")
    @GetMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<Vakcina>> getSveVakcine() {

        return new ResponseEntity<>(vakcinaService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SLUZBENIK')")
    @PutMapping(value = "/dodajKolicinu", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Boolean> addKolicin(@RequestBody VakcinaKolicinaDTO vakcinaKolicina) {

        vakcinaService.addKolicina(vakcinaKolicina.getNazivVakcine(), vakcinaKolicina.getKolicina());
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
