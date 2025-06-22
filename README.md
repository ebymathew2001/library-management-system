# 📚 Library Management System – Microservices Architecture

A distributed microservices-based system for managing students, books, and borrowing operations in a library. This project is split into three independent Spring Boot microservices:

- ✅ **Student Service** – Manages student records
- ✅ **Book Service** – Manages book inventory and availability
- ✅ **Borrow Service** – Manages borrowing and returning of books


## 🧱 Microservices Structure

```
library-system/
├── student-service/
├── book-service/
└── borrow-service/
```

Each folder is an independent Spring Boot project with its own dependencies, database, and REST APIs.


## ⚙️ Tech Stack

- Java 21
- Spring Boot 3
- Spring Web (REST APIs)
- Spring Data JPA (with MySQL)
- ModelMapper (DTO conversion)
- Lombok
- RestTemplate (for inter-service calls)
- Maven (project build)


## 🔗 Inter-Service Communication

`borrow-service` communicates with the other services using `RestTemplate`.

| From            | To                  | Purpose                        |
|-----------------|---------------------|--------------------------------|
| Borrow Service  | Student Service     | Validate student existence     |
| Borrow Service  | Book Service        | Validate book availability     |
| Borrow Service  | Book Service        | Increase/decrease book copies  |


📁 Project Folder Structure

# Project Structure

```
Library-Management-System/
├── README.md
├── book-service/
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   │   └── com/spring_project/book_service/
│       │   │       ├── BookServiceApplication.java
│       │   │       ├── config/
│       │   │       │   └── MapperConfig.java
│       │   │       ├── controller/
│       │   │       │   └── BookController.java
│       │   │       ├── dto/
│       │   │       │   ├── BookRequestDto.java
│       │   │       │   └── BookResponseDto.java
│       │   │       ├── entity/
│       │   │       │   └── Book.java
│       │   │       ├── repository/
│       │   │       │   └── BookRepository.java
│       │   │       └── service/
│       │   │           └── BookService.java
│       │   └── resources/
│       │       └── application.properties
│       └── test/
├── borrow-service/
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   │   └── com/spring_projects/borrow_service/
│       │   │       ├── BorrowServiceApplication.java
│       │   │       ├── config/
│       │   │       │   └── AppConfig.java
│       │   │       ├── controller/
│       │   │       │   └── BorrowController.java
│       │   │       ├── dto/
│       │   │       │   ├── BorrowRequestDto.java
│       │   │       │   └── BorrowResponseDto.java
│       │   │       ├── entity/
│       │   │       │   ├── Borrow.java
│       │   │       │   └── BorrowStatus.java
│       │   │       ├── repository/
│       │   │       │   └── BorrowRepository.java
│       │   │       └── service/
│       │   │           └── BorrowService.java
│       │   └── resources/
│       │       └── application.properties
│       └── test/
└── student-service/
    ├── pom.xml
    └── src/
        ├── main/
        │   ├── java/
        │   │   └── com/spring_projects/student_service/
        │   │       ├── StudentServiceApplication.java
        │   │       ├── config/
        │   │       │   └── MapperConfig.java
        │   │       ├── controller/
        │   │       │   └── StudentController.java
        │   │       ├── dto/
        │   │       │   ├── StudentRequestDto.java
        │   │       │   └── StudentResponseDto.java
        │   │       ├── entity/
        │   │       │   └── Student.java
        │   │       ├── repository/
        │   │       │   └── StudentRepository.java
        │   │       └── service/
        │   │           └── StudentService.java
        │   └── resources/
        │       └── application.properties
        └── test/
```

## 🧪 How to Run

> ✅ Prerequisites:
> - MySQL installed and running
> - Java 21 installed
> - Maven installed (or use `./mvnw`)
> - Ports 8081, 8082, and 8083 must be free


### 1️⃣ Clone the Repository

```bash
git clone https://github.com/ebymathew2001/library-system.git
cd library-system

```

2️⃣ Create MySQL Databases

Create the following databases in your MySQL server:


CREATE DATABASE library_student;

CREATE DATABASE library_book;

CREATE DATABASE library_borrow;


3️⃣ Configure Each Service
Update the application.properties file in each service under src/main/resources/ with your database details.

spring.datasource.url=jdbc:mysql://localhost:3306/library_student
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update


4️⃣ Start Each Microservice

5️⃣ Test the APIs
Use Postman, curl, or a frontend app to test the REST APIs:


### 🧑‍🎓 Student Service (Port 8081)
- `POST   /api/students` – Add a student
- `GET    /api/students` – List all students
- `GET    /api/students/{id}` – Get student by ID
- `PUT    /api/students/{id}` – Update student info
- `DELETE /api/students/{id}` – Delete student
### 📘 Book Service (Port 8082)
- `POST   /api/books` – Add a book
- `GET    /api/books` – List all books
- `GET    /api/books/{id}` – Get book by ID
- `PUT    /api/books/{id}` – Update book info
- `DELETE /api/books/{id}` – Delete book
- `PATCH  /api/books/{id}/update-copies?delta=-1` – Update available copies (+/-)

### 🔄 Borrow Service (Port 8083)
- `POST   /api/borrows` – Borrow a book
- `GET    /api/borrows` – Get all borrow records
- `GET    /api/borrows/{id}` – Get borrow record by ID
