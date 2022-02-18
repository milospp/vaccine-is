package vaccineisportal.obrazac_saglasnosti.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vaccineisportal.obrazac_saglasnosti.model.ListaSaglasnosti;
import vaccineisportal.obrazac_saglasnosti.model.Saglasnost;
import vaccineisportal.obrazac_saglasnosti.service.ObrazacSaglasnostiService;
import zajednicko.service.MarshallingService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/obrasci-saglasnosti-bridge")
public class ObrazacSaglasnostiBridgeController {

    private final ObrazacSaglasnostiService obrazacSaglasnostiService;
    private final MarshallingService marshallingService;

    @GetMapping(value = "/podneti")
    public ResponseEntity<String> getPodnetiObrasciSaglasnosti() {
        List<Saglasnost> saglasnosti = obrazacSaglasnostiService.findAll().stream().filter(o -> o.getEvidencijaVakcinacije() == null).collect(Collectors.toList());

        ListaSaglasnosti retVal = new ListaSaglasnosti();
        retVal.setSaglasnosti(saglasnosti);

        return new ResponseEntity<>(marshallingService.marshall(retVal, ListaSaglasnosti.class), HttpStatus.OK);
    }
}
