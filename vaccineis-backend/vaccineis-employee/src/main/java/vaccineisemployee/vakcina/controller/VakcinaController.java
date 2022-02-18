package vaccineisemployee.vakcina.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vaccineisemployee.vakcina.dto.ListaVakcinaDTO;
import vaccineisemployee.vakcina.dto.VakcinaDTO;
import zajednicko.service.MarshallingService;
import zajednicko.service.VakcinaService;


@AllArgsConstructor
@RestController
@RequestMapping("/api/vakcine")
public class VakcinaController {

    private final VakcinaService vakcinaService;
    private final MarshallingService marshallingService;

    @PreAuthorize("hasAnyAuthority('SLUZBENIK')")
    @GetMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ListaVakcinaDTO> getSveVakcine() {

        var retVal = new ListaVakcinaDTO();
        vakcinaService.findAll().forEach(v -> retVal.getVakcine().add(new VakcinaDTO(v.getId(), v.getNaziv(), v.getKolicina())));
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SLUZBENIK')")
    @PutMapping(value = "/dodajKolicinu", consumes = MediaType.APPLICATION_XML_VALUE)
    public void addKolicin(@RequestBody VakcinaDTO vakcinaKolicina) {

        vakcinaService.addKolicina(vakcinaKolicina.getNaziv(), vakcinaKolicina.getKolicina());
    }
}
