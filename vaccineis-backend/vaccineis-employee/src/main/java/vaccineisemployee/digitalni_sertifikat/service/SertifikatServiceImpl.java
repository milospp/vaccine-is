package vaccineisemployee.digitalni_sertifikat.service;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vaccineisemployee.digitalni_sertifikat.model.CTinfoSertifikata;
import vaccineisemployee.digitalni_sertifikat.model.CTtest;
import vaccineisemployee.digitalni_sertifikat.model.CTvakcinacijaPodaciSertifikat;
import vaccineisemployee.digitalni_sertifikat.model.ZeleniSertifikat;
import vaccineisemployee.potvrda_o_vakcinaciji.model.PotvrdaVakcinacije;
import vaccineisemployee.potvrda_o_vakcinaciji.service.PotvrdaService;
import zajednicko.model.CTadresa;
import zajednicko.model.CTpodnosilacZahtjeva;
import zajednicko.model.CTvakcinacijaPodaci;
import zajednicko.model.STpol;
import zajednicko.util.ZajednickoUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class SertifikatServiceImpl implements SertifikatService{
    public final PotvrdaService potvrdaService;

    @Override
    public ZeleniSertifikat generisiSertifikat(String uuidZahteva) {
        CTinfoSertifikata cTinfoSertifikata = new CTinfoSertifikata();
        cTinfoSertifikata.setIzdavacSertifikata("FTN Novi Sad");
        cTinfoSertifikata.setDigitalniPotpis(getPotpis());

        ZeleniSertifikat zs = new ZeleniSertifikat();
        zs.setBrojSertifikata(RandomStringUtils.randomNumeric(10));
        zs.setDatumVrijemeIzdavanja(ZajednickoUtil.localDateToGregorian(LocalDate.now()));
        zs.setInfoSertifikata(cTinfoSertifikata);
        zs.setTestovi(getTestovi());
        zs.setQrKod("https://api.qrserver.com/v1/create-qr-code/?size=150x150&amp;data=http://localhost:9092/дигитални/" + zs.getBrojSertifikata());
        zs.setPodaciVakcinisanog(dobaviPodnosioca(uuidZahteva));
        zs.setVakcinacija(dobaviVakcinacije(zs.getPodaciVakcinisanog().getJmbg()));

        return zs;
    }

    private CTinfoSertifikata.DigitalniPotpis getPotpis() {
        CTinfoSertifikata.DigitalniPotpis dp = new CTinfoSertifikata.DigitalniPotpis();
        dp.setNazivUstanove("FTN Novi Sad");
        dp.setAdresaUstanove(new CTadresa("Dr Sime Miloševića", "2"));
        dp.setDatumIzdavanja(ZajednickoUtil.localDateToGregorian(LocalDate.of(1960,1,7)));
        return dp;
    }

    private CTpodnosilacZahtjeva dobaviPodnosioca(String uuidZahteva) {

        //TODO: Povezi se sa drugim bekendom i dobavi

        CTpodnosilacZahtjeva ct = new CTpodnosilacZahtjeva();
        ct.setId(UUID.randomUUID().toString());
        ct.setJmbg("2501000772026");
        ct.setBrojPasosa("123123123");
        ct.setDatumRodjenja(ZajednickoUtil.localDateToGregorian(LocalDate.of(2000,1,25)));
        ct.setIme("Milos");
        ct.setPrezime("Popovic");
        ct.setPol(STpol.МУШКО);

        return ct;
    }

    private CTvakcinacijaPodaciSertifikat dobaviVakcinacije(String jmbg) {
        CTvakcinacijaPodaciSertifikat podaci = new CTvakcinacijaPodaciSertifikat();

        PotvrdaVakcinacije vakcinacija = potvrdaService.getPoslednjuPotvrdu(jmbg);
        for (CTvakcinacijaPodaci vakcPodaci : vakcinacija.getPodaciVakcinacije().getVakcinaPodaci()){
            CTvakcinacijaPodaciSertifikat.Doza doza = new CTvakcinacijaPodaciSertifikat.Doza();
            doza.setProizvodjac(vakcPodaci.getNazivVakcine().value());
            doza.setSerijaVakcine(RandomStringUtils.randomAlphanumeric(3));
            doza.setVakcinaPodaci(vakcPodaci);
            podaci.getDoza().add(doza);
        }

        return podaci;
    }




    private CTtest getTestovi() {
        CTtest cTtest = new CTtest();

        CTtest.Test test1 = new CTtest.Test();
        test1.setTipTesta("N/A");
        test1.setLaboratorija("N/A");
        test1.setDatumVremeUzorkovanja(ZajednickoUtil.localDateToGregorian(LocalDate.now()));
        test1.setDatumVremeRezultata(ZajednickoUtil.localDateToGregorian(LocalDate.now()));
        test1.setProizvodjacTesta("N/A");
        test1.setRezultat("N/A");
        test1.setVrstaUzorka("N/A");

        return cTtest;
    }

    @Override
    public ResponseEntity<?> getPdf(int id) throws IOException {
        return getDocument("pdf");
    }

    @Override
    public ResponseEntity<?> getHtml(int id) throws IOException {
        return getDocument("html");
    }

    private static ResponseEntity<?> getDocument(String type) throws IOException {
        File file = new File("./src/main/resources/data/gen/files/digitalni-sertifikat." + type);
        byte[] arr = FileUtils.readFileToByteArray(file);
        System.out.println(Arrays.toString(arr));
        String ret = new String(arr, StandardCharsets.UTF_8);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentLength(arr.length);
        responseHeaders.setContentType(MediaType.valueOf("application/" + type));
        responseHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=somefile." + type));
        return new ResponseEntity<>(arr, responseHeaders, HttpStatus.OK);
    }
}
