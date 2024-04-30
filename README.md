# Getting Started

## Spring Data JPA API Server

This server, built using Spring Data JPA (Java version 21, Maven 4.0.0), offers the following features:

1. **Database Connectivity**: 
   - Utilizes SQL Server as the database server.

2. **CRUD Operations for GPS Locations**:
   - Supports CRUD operations for GPS locations obtained from the Google Places Service.
   - Stores GPS locations in the geometry type of SQL Server.

3. **Logging**:
   - Logs request and response information in separate log files with date suffixes.
   - Implements logging using the logback-access package.

4. **Transactional Support**:
   - Implements `@Transactional` in the `LocationService`.

5. **Pagination**:
   - Supports pagination and sorting in the `getAllLocations` controller action.

6. **Third-Party API Integration**:
   - Allows nested calls to another API from a 3rd party.
   - Handled by the `ExternalApiController`.

7. **CORS Configuration**:
   - Allows access to the React web application origin.
   - Configurable by changing the value of `cors.allowed.origins` in `application.properties`.

---

## Steps

1. Set up the environment variable `DB_PASS` with the SQL Server password for the TESTDB database. For example, in VSCode on macOS, run the command `export DB_PASS="password"`. Alternatively, you can replace `${DB_PASS}` in the `application.properties` file with your database password.
2. If you plan to use SQL Server via Docker, you can obtain it by running the command: `docker pull mcr.microsoft.com/mssql/server:2022-latest`
3. Don't forget to enable the Docker general setting "Use Rosetta for x86_64/amd64 emulation on Apple Silicon" if you're using an Apple Silicon processor.
4. You can enhance your Spring framework support by installing Spring Tools 4 directly from https://spring.io/tools in your IDE.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.5/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/index.html#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
