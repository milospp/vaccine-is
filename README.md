# vaccine-is
vaccine-is je informacioni sistem za vakcinaciju građana, koji podržava poslovni proces imunizacije građana (vakcinacija) i izdavanja digitalnog sertifikata

# Arhitektura projekta
<img width="870" alt="Screenshot 2022-02-18 at 19 03 20" src="https://user-images.githubusercontent.com/54076398/154738335-ff0562da-4e2e-4e8d-bf57-8ece7b19095a.png">

# Pokretanje projekta
## Pokretanje baza

Potrebno je instalirati docker

https://docs.docker.com/get-docker/

### Pokretanje XML baza dokumenata (EXIST DB)

```
docker pull existdb/existdb:4.8.0
docker run -it -d -p 8083:8080 -p 8443:8443 --name exist8083 existdb/existdb:4.8.0
docker run -it -d -p 8084:8080 -p 8444:8443 --name exist8084 existdb/existdb:4.8.0
```

### Pokretanje RDF baza podataka (FUSEKI)

```
docker pull stain/jena-fuseki
docker run -it -d -p 3033:3030 --name fuseki3033 -e ADMIN_PASSWORD=admin stain/jena-fuseki
docker run -it -d -p 3034:3030 --name fuseki3034 -e ADMIN_PASSWORD=admin stain/jena-fuseki
```

Potrebno je odraditi sledeći korak da RDF baza može ponovo da se pokreće na dockeru


```
docker exec -it fuseki3033 bash
```

U bash od fusekija dodati:
```
  apt-get update;
  apt-get install -y --no-install-recommends procps
  exit
```
zatim
```
docker restart fuseki3033
```

---

Takođe i za drugu bazu

```
docker exec -it fuseki3034 bash
```

U bash od fusekija

```
  apt-get update;
  apt-get install -y --no-install-recommends procps
  exit
```
zatim
```
docker restart fuseki3034
```

## Pokretanje backend aplikacija
### vaccineis-backend/vaccineis-employee
- Pokrece se kao standardna Spring Boot aplikacija
- Aplikacija ce se pokrenuti na portu 8081

### vaccineis-backend/vaccineis-portal
- Pokrece se kao standardna Spring Boot aplikacija
- Aplikacija ce se pokrenuti na portu 8082

## Pokretanje frontend aplikacija
### Unutar vaccineis-frontend/vaccineis-employee pokrenuti
```
npm run serve
```
aplikacija ce se pokrenuti na portu 9091

### Unutar vaccineis-frontend/vaccineis-portal pokrenuti
```
npm run serve

```
aplikacija ce se pokrenuti na portu 9092
