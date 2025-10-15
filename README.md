# 🎓 Spring Reactor Academy

## 📝 Description

Spring Reactor Academy is a backend application developed with Spring WebFlux, implementing reactive programming to manage an educational academy. This system allows efficient management of students, courses, and enrollments using a non-blocking, event-driven approach.

## ✨ Key Features

- **🚀 Reactive Programming**: Built with Spring WebFlux to provide high scalability and performance.
- **🗄️ NoSQL Database**: Uses MongoDB as a reactive database.
- **🔄 RESTful Architecture**: Well-structured API following REST principles.
- **🔒 Security**: Implements authentication and authorization through Spring Security and JWT.
- **📚 API Documentation**: Includes Swagger/OpenAPI to document endpoints.
- **✅ Data Validation**: Implements validations to ensure data integrity.

## 📂 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── api/
│   │           └── rest/
│   │               ├── config/            # Configurations (MongoDB, Mappers, Swagger)
│   │               ├── controller/        # REST Controllers
│   │               ├── dto/               # Data Transfer Objects
│   │               ├── exception/         # Custom exception handling
│   │               ├── handler/           # Functional routing handlers
│   │               ├── model/             # Domain entities
│   │               ├── pagination/        # Pagination support
│   │               ├── repo/              # Reactive repositories
│   │               ├── security/          # Security configuration and JWT
│   │               ├── service/           # Business services
│   │               └── validator/         # Custom validators
│   └── resources/
│       └── application.properties         # Application configuration
└── test/
    └── java/                              # Unit and integration tests
```

## 💾 Data Models

The system is based on the following main models:

- **👨‍🎓 Student**: Represents students with attributes like first name, last name, ID number, and age.
- **📘 Course**: Represents academic courses with name, acronym, and status.
- **📋 Enrollment**: Represents enrollments that associate students with their courses.
- **📝 EnrollmentDetail**: Details of courses included in an enrollment.
- **👤 User and Role**: Models for authentication and authorization management.

## 🛠️ Technologies Used

- **☕ Java 21**
- **🍃 Spring Boot 3.5.5**
- **⚡ Spring WebFlux** - Reactive framework
- **🔐 Spring Security** - Security and authentication
- **🏦 MongoDB** - Reactive NoSQL database
- **🔑 JWT** - Token-based authentication
- **🔄 ModelMapper** - Mapping between entities and DTOs
- **🏗️ Lombok** - Reduction of boilerplate code
- **📖 SpringDoc OpenAPI** - API documentation

## 🔌 Main Endpoints

The API exposes several endpoints organized by resources:

- **/api/v1/students/** - 👨‍🎓 Student management
- **/api/v1/courses/** - 📚 Course management
- **/api/v1/enrollments/** - 📋 Enrollment management
- **/login** - 🔐 User authentication

## 🔒 Security

The system implements role-based security:
- **👑 ADMIN**: Complete access to all functionalities.
- **👤 USER**: Limited access to read operations.

Authentication is performed using JWT (JSON Web Token).

## 📚 API Documentation

API documentation is available through Swagger UI at:
```
/swagger-ui.html
```

## 📋 Requirements

- ☕ Java 21+
- 🏦 MongoDB
- 🔨 Maven

## 🚀 How to Run

1. Make sure MongoDB is running
2. Clone this repository
3. Configure connection properties in `application.properties`
4. Run the application with:
   ```
   mvn spring-boot:run
   ```

## ⚡ Reactive Programming Features

This project takes full advantage of WebFlux capabilities:

- **🔄 Non-blocking Operations**: Extensive use of Mono and Flux
- **🧩 Functional Programming**: Use of reactive operators for data transformation
- **🔀 High Concurrency**: Efficient handling of multiple requests
- **🚦 Backpressure**: Automatic control of data flow

## 👥 Contributing

Contributions are welcome. Please open an issue to discuss major changes or send a pull request with your improvements.
