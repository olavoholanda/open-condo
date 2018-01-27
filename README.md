# OpenCondo - Open Source Social Condominium

OpenCondo is an open source Java Rest API for social interaction among condominium communities.
Currently, this project is under development and there is no stable version yet. Therefore, there isn't a demo
application for this project.

This project is built using micro service pattern, every service is developed using Spring Boot and they
all are registered using Spring/Netflix eureka.

#### List of Services

* Account Service - Service that manages user accounts;
* Forum Service - Service responsible for the forum feature;
* Eureka Service - Spring/Netflix Eureka Server service;

## Getting Started

These instructions will get you a copy of the project up and running on your local
machine for development and testing purposes.

### Prerequisites

This project has minor requirements, the databases and servers are embedded, so if you need a non in memory
database for each service, you will have to change each service configuration property file.
So, the requirements are:

```
Java 1.8
Maven 3.x
```

### Building

Each service has its own instruction for running. As a micro service pattern they all should be 
independently, however they need the Eureka Server running first, so they can register in it.

To build the application use maven command line: 

```
mvnw clean install
```

If you want to run from the main folder you need to execute the following command line for each service:

```
mvnw spring-boot:run -pl account-service
```

To proper run, please execute eureka service first.

## Running the tests

The project uses JUnit for unit tests. To run the tests with maven command, use:

 ```
 mvnw test
 ```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring-Boot](https://projects.spring.io/spring-boot/) - The web framework used, along with 
Actuator, Cache, Security, Dependency Injection and others Spring components
* [Spring Cloud Netflix Eureka](https://cloud.spring.io/spring-cloud-netflix/) - For service discovery
* [Lombok](https://projectlombok.org/) - Code injection
* [JUnit](http://junit.org/junit4/) - Tests


## Authors

* **Olavo Holanda** - *Initial work*
