# 🚀 Scalable Social Media Backend

A production-style backend for a social media platform built with modern Java technologies.  
This project demonstrates authentication, secure APIs, database design, caching, and cloud-ready architecture.

---

## 🧠 Tech Stack

### Backend
- Java 17+
- Spring Boot 3
- Spring Security
- JWT Authentication
- Spring Data JPA

### Database
- PostgreSQL

### Dev Tools
- Maven
- Lombok
- IntelliJ IDEA

### Upcoming Integrations
- Redis (Caching & Rate Limiting)
- Docker (Containerization)
- Cloud Deployment (AWS / Render)

---

## 🔐 Features Implemented

### 👤 Authentication
- User Registration API
- Login API
- Password hashing using BCrypt
- JWT token generation
- Stateless authentication base

### 🗄️ Database
- PostgreSQL integration
- JPA/Hibernate ORM
- Auto schema generation

---

## 📌 API Endpoints

### Register User

POST `/api/auth/register`
```json
{
  "name": "Sachin",
  "email": "sachin@test.com",
  "username": "sachin123",
  "password": "password123"
}
```
## Login
POST /api/auth/login

```json
{
  "username": "sachin123",
  "password": "password123"
}
```
---

### Setup Instruction

1️⃣ Clone Repository
git clone https://github.com/YOUR_USERNAME/social-backend.git
cd social-backend

2️⃣ Configure Database
-Create PostgreSQL database:
   CREATE DATABASE socialdb;

Update application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/socialdb
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

3️⃣ Run Application
mvn spring-boot:run
---
### 🏗️ Project Structure
src/main/java/com/sachin/social_backend/
├── config
├── controller
├── dto
├── entity
├── repository
├── security
└── service
---
### Testin APIs
Use:
-Postman
-Thunder Client (VS Code)
-curl

---
### 🔮 Planned Features
-JWT authentication filter
-Protected endpoints
-Role-based authorization
-Follow system
-Posts & comments
-Redis caching
-Rate limiting
-Docker containerization
-Cloud deployment
-CI/CD pipeline
---

## Author
Sachin

