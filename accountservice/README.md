# OpenCondo - Account MS

This is the user account micro service for the OpenCondo application. This micro service
is responsible for all operations and requests regarding user accounts of the app.

## Getting Started

These instructions will get you a copy of the project up and running on your local
machine for development and testing purposes.

### Prerequisites

This project has the following requirements:

```
Java 1.8
Maven 3.x
```

### Installing

You can run the application from the command line with Maven. Or you can build a single executable
JAR file that contains all the necessary dependencies, classes, and resources, and run that. 

If you just want to run the application, use:

```
mvnw spring-boot:run
```

Or you can build the Jar file with:

```
mvnw clean package
```

And then run the application:

```
java -jar target/forumservice-0.1-SNAPSHOT.jar
```

Logging output is displayed. You will see the Spring logo and within a few seconds
 the server should be up.

### Checking the service

With the server up in your local machine, you can test with the following url, default server port is 8081:

```
http:localhost:8081/health
```

The project uses Spring Actuator for default services methods, like health checks. If a prompt asks
for user and password is because Spring Security is activated, you can change in 
application.properties the user and password.

## Running the tests

The project uses JUnit for unit tests. To run the tests with maven command, use:

 ```
 mvnw test
 ```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring-Boot](https://projects.spring.io/spring-boot/) - The web framework used, along with 
Actuator, Cache, Security, Dependency Injection and others Spring components
* [Lombok](https://projectlombok.org/) - Code injection
* [JUnit](http://junit.org/junit4/) - Tests


## Authors

* **Olavo Holanda** - *Initial work*
