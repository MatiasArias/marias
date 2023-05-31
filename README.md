# Test Backend Developer

## Primera evaluación técnica 


###### Dado el siguiente enunciado...

“Un Candidato tiene conocimiento de ciertas Tecnologías. Los datos de interés y obligatorios de un 
Candidato son: nombre, apellido, tipo (DNI, LE, LC) y número de documento, fecha de nacimiento y las 
tecnologías que maneja. Para el caso de las Tecnologías, interesa persistir el nombre (java, python, 
maven, hibernate, spring) y versión. De la relación entre un Candidato y una Tecnología surge un 
atributo, el de años de experiencia, el cual indica cuántos años de experiencia posee el Candidato en 
dicha Tecnología. La relación es claramente de N a M ya que un candidato puede tener experiencia
en múltiples tecnologías y una tecnología puede estar asociada a múltiples candidatos”

Para resolver lo enunciado anteriormente deberás implementar…

* API Rest (CRUD) para Candidato y Tecnología. Esto implica: Crear controladores, servicios, repositorios y entidades.
* BD en memoria (por ejemplo, H2).
* Validaciones de campos obligatorios, de fecha y númericos.
* Creación, uso y manejo de al menos 2 excepciones.
* Crear Logs en donde consideres.

## Resolución

Para la resolución de este trabajo utilicé los conocimientos adquiridos en Academy que fueron los siguientes:

* Springboot
* JPA
* Git & Gitflow
* Lombok
* H2
* JUnit
* Mockito
* Spring Security
* JWT

Manejé la siguiente estructura de objetos:

* Candidate
* Technology
* TechnologyByCandidate
* Users

### Primeros pasos

Para poder ejecutar el proyecto se deben seguir los siguientes pasos:

1. Clonar el repositorio localmente
2. Ejectuar el proyecto en algun entorno de desarrollo (En mi caso con IntelliJ IDEA)
3. Ingresar en un navegador a la siguiente url: http://localhost:8080 

### Explicación de direcciones

Para las direcciones de los endpoints me basé en la siguiente estructura
```
GET http://localhost:8080/{Entidad en plural} -> Muestra todas los registros de una entidad
GET http://localhost:8080/{Entidad en plural}/{id} -> Muestra todos los datos de la entidad con ese id
POST http://localhost:8080/{Entidad en plural}/create -> Crea una entidad nueva
PUT http://localhost:8080/{Entidad en plural}/update/{id} -> Actualiza los datos de la entidad con ese id
DELETE http://localhost:8080/{entidad}/delete/{id} -> Elimina la entidad con ese id
```

Puedes intentar remplazando {Entidad en plural} por [candidates,technologies]

Para conocer las tecnologias de un candidato debes acceder al siguiente enlace

http://localhost:8080/candidates/{idCandidato}/technologies

(También se encuentran sus respectivos metodos HTTP igual que el ejemplo anterior)

### Rápido acceso

> Si desea probar el proyecto con datos cargados debes realizar lo siguiente:

1. Ingresa a http://localhost:8080/h2-console
2. Ingresa los siguientes datos:
    * Driver Class: org.h2.Driver
    * JDBC URL: jdbc:h2:mem:testdb
    * User Name: sa
    * Password: moby
3. Luego en la consola de SQL copia y pega los siguientes datos:

```
INSERT INTO Candidate(id_candidate,name,lastname,document_type,document_number,birthdate) values (1,'Matias','Arias',0,43132313,'2001-01-19');
INSERT INTO Candidate(id_candidate,name,lastname,document_type,document_number,birthdate) values (2,'Joaquin','Alvarez',1,4291231,'2000-11-3');
INSERT INTO Candidate(id_candidate,name,lastname,document_type,document_number,birthdate) values (3,'Gaston','Castro',2,43181222,'2001-05-5');
INSERT INTO Technology(id_technology,name,version) values (1,'Springboot','3.0.5');
INSERT INTO Technology(id_technology,name,version) values (2,'JPA','3.0.5');
INSERT INTO Technology(id_technology,name,version) values (3,'Java','17');
INSERT INTO Technology(id_technology,name,version) values (4,'h2','3.0.5');
INSERT INTO Technology(id_technology,name,version) values (5,'Lombok','3.0.5');
INSERT INTO Technology(id_technology,name,version) values (6,'Git','3.0.5');
INSERT INTO Technology(id_technology,name,version) values (7,'GitFlow','3.0.5');
INSERT INTO Technology(id_technology,name,version) values (8,'Docker','3.0.5');
INSERT INTO Technology(id_technology,name,version) values (9,'Java','19');
INSERT INTO Technology_By_Candidate(id_technology_by_candidate,id_candidate,id_technology,years_of_experience) VALUES (1,1,4,2);
INSERT INTO Technology_By_Candidate(id_technology_by_candidate,id_candidate,id_technology,years_of_experience) VALUES (2,1,5,2);
INSERT INTO Technology_By_Candidate(id_technology_by_candidate,id_candidate,id_technology,years_of_experience) VALUES (3,2,1,2);
INSERT INTO Technology_By_Candidate(id_technology_by_candidate,id_candidate,id_technology,years_of_experience) VALUES (4,2,2,2);
INSERT INTO Technology_By_Candidate(id_technology_by_candidate,id_candidate,id_technology,years_of_experience) VALUES (5,3,7,2);
INSERT INTO Technology_By_Candidate(id_technology_by_candidate,id_candidate,id_technology,years_of_experience) VALUES (6,3,8,2);
INSERT INTO Technology_By_Candidate(id_technology_by_candidate,id_candidate,id_technology,years_of_experience) VALUES (7,1,3,2);
INSERT INTO Technology_By_Candidate(id_technology_by_candidate,id_candidate,id_technology,years_of_experience) VALUES (8,1,9,3);
```

4. Dale al boton **Run** y se cargarán los datos en memoria.

> A continuacion también te dejo estructuras JSON para que puedas probar de manera más fácil

```
## Candidates
{
    "name": "Matias",
    "lastname": "Arias",
    "documentType": 0,
    "documentNumber": 4321312,
    "birthdate": "2023-04-27"
}
## Technologies 
{
    "name":"Java",
    "version":"17"
}
## Technologies by Candidate
{
    "idCandidate":1,
    "idTechnology":1,
    "yearsOfExperience":2
}
```

## Spring security

Para podes comprobar la funcionalidad de seguridad de la aplicación son importantes los siguientes endpoint:
* http://localhost:8080/signup
* http://localhost:8080/login

En donde uno te permitirá registrarte como usuario y el otro te permitirá hacer uso de la api. 
El unico incoveniente es que una vez registrado y logueado deberás de hacer uso de Bearer Auth para hacer uso de la api en su totalidad.

La estructura que debes seguir es la siguiente:
* Para el **Sign up** :
```
   {
    "username":"marias",
    "email":"marias@mobydigital",
    "password":"moby"
    }
```
* Para el login
```
{
    "username":"gcastro",
    "password":"gaston123"
}
```

Al loguearte, la respuesta te enviará un Bearer Token en su header. Para los demás endpoints podras hacer uso de la api al autenticarte con Bearer Token

En los header al loguearte encontrarás lo siguiente:

| Key | Value |
|-----|-------|
|   Authorization  |    Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnY2FzdHJvIiwiZXhwIjoxNjg4MTM3ODYyLCJlbWFpbCI6ImdjYXN0cm9AbW9ieWRpZ2l0YWwifQ.zvwkTv8JL9DFXgDnLXcYnbck3jfjnwBLKBHz9EwRorHSu07ei3jiMqNGYpI09bRochJ4USJlCVvcsvzyFFkqlA   |

Por lo que deberás hacer uso del token (Ignorando el "Bearer ")