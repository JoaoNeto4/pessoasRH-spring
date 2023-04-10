## pessoasRH-spring

## Description
- Rest API project developed using Spring Boot Framework and H2 database.

## Prerequisites
```
$ sudo apt-get update
$ sudo apt-get install git
$ sudo apt-get install openjdk-11-jdk
$ sudo apt-get install openjdk-11-jre
$ sudo apt-get install maven
$ sudo apt-get install docker
$ sudo apt-get install docker-compose
```
## Instructions

### Clone this repository
```
$ git clone https://github.com/JoaoNeto4/pessoasRH-spring.git app
```
### Navigate to the "app" folder
```
$ cd app/
```
### Create Build
```
$ ./mvnw clean package -DskipTests
```
> **Note**
> If you want to run it with Java:
```
$ cd target/
$ java -jar pessoasRH-0.0.1-SNAPSHOT.jar
```
> **Note**
> If you want to run it with Docker:
```
$ sudo docker-compose up
```
> **Note**
> If Or even run it with the IDE of your choice

### Now just access http://localhost:8080/pessoas

### You can access Swagger to understand endpoints at:
```
http://localhost:8080/swagger-ui.html
```
### You can access the database IF YOU ARE USING Docker at: http://localhost:8082/h2-console
```
user: sa
pass: password
```
### You can access the database IF YOU ARE USING Java at: http://localhost:8080/h2-console
```
user: sa
pass: password
```
### You can still use the PessoaRH_Tests.postman_collection.json file by importing it into Postman and following the API tests.
