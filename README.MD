# Spotitube-API

## Auteur: _Maarten van der Lei_
> spotitube is een java API gemaakt met Tomcat EE voor OOSE-DEA.

### Deployment Diagram:


### Package Diagram:
![package_diagram](/docs/package_diagram.png)
> Binnen het domain bevinden zich de verschillende packages voor de verschillende endpoints: playlists, tracks en users.
Elk van deze packages bevatten data DAO's als interface, presentation voor de endpoints en een service laag voor het verbinden van de vorige 2 genoemde lagen.
> > De package: Presentation bevat de inkomende objecten in de vorm van een Request, de uitgaande objecten in de vorm van een Response en de Controllers waar de http verzoeken binnen komen.

> In de package: impl, zijn de interfaces van data en service geimplementeerd, met behulp van DI(Dependency Injection) kunnen we deze gebruiken binnen de presentation en service laag door de interface aan te roepen.

> In de package: Exceptions, is een custom exceptie gemaakt voor het missen of sturen van onjuiste token bij protected requests.

> In de package: db is een class met een static methode waar de dao's een nieuwe database connectie aan kunnen maken door deze static method aan te roepen.
