# vaccine-is

## Građanin

1. registracija

2. podnosenje doc-a kojim se iskazuje interesovanje za prijem vakcine
   -- (interesovanje.pdf)
   -- sistem e-mailom obavestava građanina da je interesovanje uspesno i da ce dobiti prvi slobodan termin cim bude dostupan i dovoljna kolicina doza

3. podnosenje doc-a saglasnosti za imunizaciju
   -- (obrazac saglasnosti za imunizaciju.pdf)

4. podnosenje zahteva za izdavanje digitalnog zelenog sertifikata
   -- (zahtev za sertifikat.pdf)

5. pregleda doc-ove koji su mu izdali zdrav. radnik i sluzbenik
   -- moze da pregleda svaki izdati i prilozeni doc (moze da ih preuzmi u XHTML i PDF formatu)

## Zdravstveni radnik

1. vrsi imunizaciju
   -- drugi deo doc-a - (obrazac saglasnosti za imunizaciju.pdf)

2. izdaje doc potvrde
   -- (potvrda o vakcinaciji.pdf)
   -- izdaje se kad je primio vakcinu i kad sledecu treba da primi (građanin treba ponovo da prilozi doc saglasnosti za sledecu)

## Sluzbenik

1. odgovara na zahtev za izdavanje digitalnog zelenog sertifikata
   -- ako je građanin odbijen emailom se obavestava uz objasnjenje zasto
   -- ako je građanin prihvacen izdaje mu se zeleni sertifikati salje mu se mail da je odobren (salju mi se dokumenti u vidu attacmenta ili linka u XHTML i PDF formatu)

2. izdaje izvestaj o imunizaciji
   -- (izvestaj o imunizaciji.pdf)

3. vodi evidenciju dostupnih vakcina

4. pretrazuje arhivu dokumenata koja pripada građaninu (saglasnost, potvrda, sertifikat)

# Pokretanje projekta

## Pokretanje baza

Potrebno je instalirati docker
https://docs.docker.com/get-docker/

### EXIST DB

```
docker pull existdb/existdb:4.8.0
docker run -it -d -p 8083:8080 -p 8443:8443 --name exist8083 existdb/existdb:4.8.0
docker run -it -d -p 8084:8080 -p 8444:8443 --name exist8084 existdb/existdb:4.8.0
```

http://localhost:8083
http://localhost:8084

### FUSEKI

```
docker pull stain/jena-fuseki
docker run -it -d -p 3033:3030 --name fuseki3033 -e ADMIN_PASSWORD=admin stain/jena-fuseki
docker run -it -d -p 3034:3030 --name fuseki3034 -e ADMIN_PASSWORD=admin stain/jena-fuseki
```

http://localhost:3033
http://localhost:3034

Potrebno je odraditi sledeći korak da baza može ponovo da se pokreće na dockeru

```
docker exec -it fuseki3033 bash
```

U bash od fueskija sibnuti

```
  apt-get update;
  apt-get install -y --no-install-recommends procps
  exit
```

```
docker restart fuseki3033
```

---

Takođe i za drugu bazu

```
docker exec -it fuseki3034 bash
```

U bash od fueskija sibnuti

```
  apt-get update;
  apt-get install -y --no-install-recommends procps
  exit
```

```
docker restart fuseki3034
```
