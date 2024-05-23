# Kotuko User Management System Backend REST API

## Required Softwares
* Java 17
* MySQL Database 

## Configuration
The configuration can be changed in `./src/main/resources/application.properties`(for deployment). The following variables need to be configured properly.

* `spring.datasource.url`: the database connection url
* `spring.datasource.username`: the username for the database
* `spring.datasource.password`: the password for the database

## Initialize

Create the role  by running following command in the database (It wi;; automcatically inserted while running application)
```sql
insert  into `role`(`id`,`name`)
values (1,'USER');
```
Create a User by requesting /users api 

Do Login to hit other requests

## Local Deployment
* Create a new database
* configure the application see `Configuration` section above
* Run following commands
    ```bash
    mvnw clean spring-boot:run -DskipTests
    ```

* The API will be available at `http://localhost:8080/api`
