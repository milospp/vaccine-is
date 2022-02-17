package vaccineisportal.zahtev_sertifikata.support;

import org.springframework.stereotype.Component;
import vaccineisportal.zahtev_sertifikata.dto.ZahtjevDTO;
import vaccineisportal.zahtev_sertifikata.model.Zahtjev;
import zajednicko.model.CTpodnosilacZahtjeva;
import zajednicko.model.STpol;
import zajednicko.support.AbstractConverter;

@Component
public class ZahtjevDTOToZahtjev extends AbstractConverter<ZahtjevDTO, Zahtjev> {

    @Override
    public Zahtjev convert(ZahtjevDTO zahtjevDTO) {
        Zahtjev zahtjev = new Zahtjev();

        CTpodnosilacZahtjeva podnosilac = new CTpodnosilacZahtjeva();
        podnosilac.setIme(zahtjevDTO.getIme());
        podnosilac.setPrezime(zahtjevDTO.getPrezime());
        podnosilac.setPol(STpol.fromValue(zahtjevDTO.getPol()));
        podnosilac.setJmbg(zahtjevDTO.getJmbg());
        podnosilac.setBrojPasosa(zahtjevDTO.getBrojPasosa());
        podnosilac.setDatumRodjenja(zahtjevDTO.getDatumRodjenja());

        zahtjev.setPodnosilac(podnosilac);

        zahtjev.setMjestoDatum(zahtjevDTO.getMjestoDatum());
        zahtjev.setRazlog(zahtjev.getRazlog());
        return zahtjev;
    }
}
