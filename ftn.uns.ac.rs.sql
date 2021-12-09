ftn.uns.ac.rs

gradjanin
	registracija na portall
		-- /registracija
        (el posta salje potvrdu registracije)
  prijava na portal
      -- /prijava

	forma za kreiranje dok za interesovanje
		-- /interesovanje/forma
	podnosi dok za interesovanje za prijem vakcine 
		-- /interesovanje/podnesi
		(podnosi se prije primanja prve doze)
		(putem el poste se obavjestava gradjanin da je interesovanje uspjesno iskazano)
		(el posta sadrzi sve podatke koje su unijeti u formi)
		(dodjeljuje mu se slobodan termin za dolazak na vakcinaciju)
			(sistem obavjestava kada da dodje)
				(u narednih 7 dana)
				(cim postoji slobodan termin)
				(cim postoji dovoljna kolicina doza vakcine)
	pregledaj dok za interesovanje
		-- /interesovanje/pregled
    preuzmi dok za interesovanje
		-- /interesovanje/preuzmi?format=xhtml
		-- /interesovanje/preuzmi?format=pdf
    ---- /interesovanje/pregled?pacijent=jmbg&id=dokId 
    ---- /interesovanje/preuzmi?pacijent=jmbg&id=dokId&format=xhtml
    ---- /interesovanje/preuzmi?pacijent=jmbg&id=dokId&format=pdf


	forma za kreiranje dok saglasnosti
		-- /obrazac-saglasnosti/forma
	podnosi dok za saglasnost
		-- /obrazac-saglasnosti/podnesi
		(kada dobije termin za prijem vakcine)
		(podnosi se prije primanja bilo koje doze)
	pregledaj dok za saglasnost
		-- /obrazac-saglasnosti/pregled
    preuzmi dok za saglasnost
		-- /obrazac-saglasnosti/preuzmi?format=xhtml
		-- /obrazac-saglasnosti/preuzmi?format=pdf
    ---- /obrazac-saglasnosti/pregled?pacijent=jmbg&id=dokId
    ---- /obrazac-saglasnosti/preuzmi?pacijent=jmbg&id=dokId&format=xhtml
    ---- /obrazac-saglasnosti/preuzmi?pacijent=jmbg&id=dokId&format=pdf

	forma za kreiranje zahtjeva za izdavanje dig zelenog sertifikata
		-- /zahtjev-sertifikat/forma
		(rich edit komponenta za unos razloga)
	podnosi dok za sertifikat
		-- /zahtjev-sertifikat/podnesi
    pregledaj dok za sertifikat
		-- /zahtjev-sertifikat/pregled
    preuzmi dok za sertifikat
		-- /zahtjev-sertifikat/preuzmi?format=xhtml
		-- /zahtjev-sertifikat/preuzmi?format=pdf
    ---- /zahtjev-sertifikat/pregled?pacijent=jmbg&id=dokId
    ---- /zahtjev-sertifikat/preuzmi?pacijent=jmbg&id=dokId&format=xhtml
    ---- /zahtjev-sertifikat/preuzmi?pacijent=jmbg&id=dokId&format=pdf


    pregledaj dok za potvrda vakcinacije
		-- /potvrda-o-vakcinaciji/pregled
    preuzmi dok za potvrdu vakcinacije
		-- /potvrda-o-vakcinaciji/preuzmi?format=xhtml
		-- /potvrda-o-vakcinaciji/preuzmi?format=pdf
    -- potvrdaVakcinacije?pacijent=jmbg&id=dokId
    -- potvrdaVakcinacije?pacijent=jmbg&id=dokId&format=xhtml
    -- potvrdaVakcinacije?pacijent=jmbg&id=dokId&format=pdf

    pregledaj dok za zeleni sertifikat
		-- /digitalni-sertifikat/pregled
    preuzmi dok za zeleni sertifikat
		-- /digitalni-sertifikat/preuzmi?format=xhtml
		-- /digitalni-sertifikat/preuzmi?format=pdf
    -- digitalniSertifikat?pacijent=jmbg&id=dokId
    -- digitalniSertifikat?pacijent=jmbg&id=dokId&format=xhtml
    -- digitalniSertifikat?pacijent=jmbg&id=dokId&format=pdf

Zdravstveni radnik
    vakcinise gradjanina 
        -- /obrazac-saglasnosti/evidencija
        (popunio prije toga dokument saglasnosti)
        (smanji se dostupnaKolicina te vakcine)
    izdaje dok potvrde o izvrsenoj vakcinaciji
        -- /potvrda-o-vakcinaciji/izdaj
        (datumi svih primljenih doza)
        (datum kada treba da primi sljedecu vakcinu)
            (do ovog datuma treba da prilozi dok saglasnosti)
        (sistem automatski dodjeljuje termin)  
            (zavisno od vremena koje treba da prodje)
                (vrijeme zavisi od tipa vakcine i rednog broja doze)
    forma za izdavanje dok potvrde o izvrsenoj vakcinaciji
        -- /potvrda-vakcinaciji/forma
    
Sluzbenik
    odgovara na zahtjev za izdavanje sertifikata
        -- /zahtjev-sertifikat/sluzbenik?prihvata=odgovor
        (u slucaju odbijanja navodi razlog i sistem el postom obavjestava gradjanina da je odbijen zahtjev, uz razlog odbijanja)
        (ako prohvati, gradjaninu postaje dostupan sertifikat, salje se el posta da je prihvacen zahtjev i ukljucuje se sertifikat
        u vidu xhtml i pdf formata - attachment ili link za preuzimanje)
    pronalazi dokumentaciju odredjenog gradjanina  
        -- /registar?gradjanin=id (jmbg)
    izdaje izvjestaj o imunizaciji
        -- /izvjestaj/izdaj
    -- izvjestaj?sluzbenik=id&id=izvjestajId
    vodi evidenciju dostupnih vakcina
        -- /vakcina?tip=ime&informacija=dostupnaKolicina
    azurira dostupnaKolicina vakcina kada dodju nove
        -- /vakcina?tip=ime&informacija=dostupnaKolicina
    osnovna pretraga (fraze, rijeci, pretraga tekstualnog sadrzaja svih dokumenata nekog pacijenta)
        -- /registar?gradjanin=jmbg&unos=text
    napredna pretraga (po metapodacima)
        -- /registar?gradjanin=jmbg&metaList=metas
    (link na referencirane dokumente)
    (pronalazenje svih dok koji referenciraju na dok)
    
    