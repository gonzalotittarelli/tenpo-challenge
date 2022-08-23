# Calculator

# Challenge Tenpo

## Stack tecnológico

- Java 17
- Spring Boot
- Postgresql
- Spring security
- Resilience4j
- Lombok
- Swagger
- JUnit y Mockito

## Despliegue

### Requisitos instalación

- **git**
    - [Windows](https://git-scm.com/download/win)
    - [Linux o Macos](https://git-scm.com/download/mac)
    - ``` git --version``` para comprobar la instalación
- **docker desktop**
    - Este paquete incluye **docker-compose**, desde [aquí](https://docs.docker.com/desktop/) puede hacerlo

### Despliegue del servicio

- Clonar el [repositorio remoto](https://github.com/gonzalotittarelli/tenpo-challenge)
  - Puede hacerlo utilizando ssh```git clone git@github.com:gonzalotittarelli/tenpo-challenge.git``` 
- En la raíz del proyecto ejecutar
  ```
    ./mvnw clean install
    docker-compose up
  ```
## Utilización

El proyecto utiliza autenticación por token JWT, con lo cual, primero deberá loguearse para luego utilizar los endpoints
que requieran la misma. Deberá enviarse el header **Authorization** junto con el token proporcionado.

## Documentación

#### Swagger

Este proyecto utiliza swagger para automatizar la generación de la documentación asi como también la prueba.
Para acceder ingrese a: http://localhost:8080/swagger-ui/

#### Postman Collection

También se proporciona un [postman collection](https://www.postman.com/collections/56baebdb61f5dd04ad9d) para realizar
las pruebas correspondientes.

## [Imagen docker](https://hub.docker.com/r/gonzalotittarelli/tenpo_challenge)


