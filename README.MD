# Spotitube-API

## Auteur: _Maarten van der Lei_

spotitube is een java API gemaakt met Tomcat EE voor OOSE-DEA.

---

### Deployment Diagram:

![deployment_diagram](/docs/deployment_diagram.png)

### Package Diagram:

![package_diagram](/docs/package_diagram.png)

### Release docs

voor het opstarten van de servers dien je de juiste dependencies geinstalleerd te hebben via maven.

voor sql dien je het `init_script.sql` te runnen waarna er wat mock data in de database komt en je de database kan gebruiken.

de juiste `db.properties` dient gemaakt te worden voor het verbinden met de database.

om de e2e te runnen: `npm install` in de e2e folder, vervolgens `npm test`.

In de `tests` folder vind je de code voor het onderzoek.
Het bijbehorende .pdf staat in `docs`.

In de `docs` folder staat de nieuwe versie van het onderzoeksrapport voor de herkansing: DEA.
In de `src` test folder staat de folder `research` met daarin een deel van de code voor het onderzoek: herkansing.
