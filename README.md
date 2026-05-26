# Employee Management App

Full-stack Employee Management CRUD application.

The backend is a Spring Boot REST API using Spring Data JPA and MySQL. The frontend will be a React app that consumes the backend APIs.

## Backend File Structure

```text
backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.farzin.ems/
│   │   │       ├── EmsBackendApplication.java
│   │   │       ├── controller/
│   │   │       │   └── EmployeeController.java
│   │   │       ├── dto/
│   │   │       │   └── EmployeeDto.java
│   │   │       ├── entity/
│   │   │       │   └── Employee.java
│   │   │       ├── exception/
│   │   │       │   └── ResourceNotFoundException.java
│   │   │       ├── mapper/
│   │   │       │   └── EmployeeMapper.java
│   │   │       ├── repository/
│   │   │       │   └── EmployeeRepository.java
│   │   │       └── service/
│   │   │           ├── EmployeeService.java
│   │   │           └── EmployeeServiceImpl.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
├── pom.xml
└── target/

Backend Architecture

React Frontend
    ↓
EmployeeController
    ↓
EmployeeService
    ↓
EmployeeServiceImpl
    ↓
EmployeeRepository
    ↓
MySQL Database


File Responsibilities
EmsBackendApplication.java

Main entry point for the Spring Boot application.

Contains the main() method and starts the embedded Tomcat server.

Uses @SpringBootApplication, which combines:

@Configuration
@EnableAutoConfiguration
@ComponentScan
controller/EmployeeController.java

REST controller layer.

Responsible for handling HTTP requests from the frontend.

Exposes CRUD endpoints:

GET    /api/v1/employees
POST   /api/v1/employees
GET    /api/v1/employees/{id}
PUT    /api/v1/employees/{id}
DELETE /api/v1/employees/{id}

Common annotations:

@RestController
@RequestMapping
@GetMapping
@PostMapping
@PutMapping
@DeleteMapping
@RequestBody
@PathVariable
dto/EmployeeDto.java

DTO stands for Data Transfer Object.

Used to transfer employee data between the API layer and service layer.

Helps avoid exposing the database entity directly to API consumers.

entity/Employee.java

JPA entity class.

Represents the employees table in MySQL.

Common annotations:

@Entity
@Table
@Id
@GeneratedValue
@Column
exception/ResourceNotFoundException.java

Custom exception used when an employee ID does not exist.

Mapped to HTTP 404 NOT FOUND using @ResponseStatus.

mapper/EmployeeMapper.java

Converts between:

Employee entity ↔ EmployeeDto

Keeps entity/DTO conversion logic separate from controller and service code.

repository/EmployeeRepository.java

Data access layer.

Extends JpaRepository<Employee, Long>.

Spring Data JPA automatically provides methods like:

findAll()
findById()
save()
delete()
service/EmployeeService.java

Service interface.

Defines the business operations supported by the app:

create employee
get employee by ID
get all employees
update employee
delete employee
service/EmployeeServiceImpl.java

Service implementation.

Contains the business logic and calls the repository layer.

Also handles cases where an employee does not exist by throwing ResourceNotFoundException.

resources/application.properties

Application configuration file.

Contains:

application name
MySQL datasource URL
database username/password
Hibernate/JPA settings
