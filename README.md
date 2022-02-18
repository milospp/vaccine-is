# vaccine-is
vaccine-is je informacioni sistem za vakcinaciju građana, koji podržava poslovni proces imunizacije građana (vakcinacija) i izdavanja digitalnog sertifikata

# Pokretanje projekta
## Arhitektura projekta

## Pokretanje baza

Potrebno je instalirati docker
https://docs.docker.com/get-docker/

### Pokretanje XML baza dokumenata (EXIST DB)

```
docker pull existdb/existdb:4.8.0
docker run -it -d -p 8083:8080 -p 8443:8443 --name exist8083 existdb/existdb:4.8.0
docker run -it -d -p 8084:8080 -p 8444:8443 --name exist8084 existdb/existdb:4.8.0
```

http://localhost:8083
http://localhost:8084

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

U bash od fusekija:

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

U bash od fusekijaČ

```
  apt-get update;
  apt-get install -y --no-install-recommends procps
  exit
```

```
docker restart fuseki3034
```
