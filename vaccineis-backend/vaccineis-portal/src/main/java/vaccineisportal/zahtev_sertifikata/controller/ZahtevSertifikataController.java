package vaccineisportal.zahtev_sertifikata.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vaccineisportal.zahtev_sertifikata.dto.ZahtjevDTO;
import vaccineisportal.zahtev_sertifikata.model.Zahtjev;
import vaccineisportal.zahtev_sertifikata.service.ZahtevSertifikataService;
import zajednicko.support.IConverter;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/digitalni-zeleni-sertifikat-zahtev")
public class ZahtevSertifikataController {

    private final ZahtevSertifikataService zahtevSertifikataService;

    private final IConverter<ZahtjevDTO, Zahtjev> toZahtjev;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Zahtjev> createInteresovanje(@Valid @RequestBody ZahtjevDTO zahtjev) {

        Zahtjev retVal = zahtevSertifikataService.save(toZahtjev.convert(zahtjev));
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }
}
