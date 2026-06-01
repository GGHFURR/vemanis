# Vemanis Backend API

Backend REST API built using Spring Boot for managing product inventory, suppliers, categories, agents, and supply transactions.

## Features

* Authentication & Authorization
* Product Management
* Category Management
* Supplier Management
* Agent Management
* Supply Transaction Management
* Stock Management
* History Tracking
* Optimistic Locking with Versioning
* Global Exception Handling
* Pagination Support
* Validation & Unique Constraints

---

# Tech Stack

* Java 21
* Spring Boot 3
* Spring Data JPA
* Hibernate
* PostgreSQL / MySQL
* Maven
* Lombok

---

# Project Structure

```bash
src/main/java/com/dansmultipro/vemanis
│
├── controller
├── dto
├── exception
├── model
├── repository
├── service
├── service/impl
├── configuration
└── utils
```

---

# Database Design

Main entities:

* Product
* Category
* Supplier
* Agent
* Supply
* SupplyDetail
* HistoryStatus
* ProductHistory

All entities extend `BaseModel`.

Example:

```java
public class BaseModel {
    private UUID id;
    private Integer version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
```

---

# Installation

## 1. Clone Repository

```bash
git clone https://github.com/your-username/vemanis.git
```

## 2. Open Project

Open using:

* IntelliJ IDEA
* VS Code
* Spring Tool Suite

## 3. Configure Database

Edit `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/vemanis
spring.datasource.username=postgres
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# Run Application

Using Maven:

```bash
mvn spring-boot:run
```

Application will run at:

```bash
http://localhost:8080
```

---

# API Example

## Create Category

### Request

```http
POST /api/category
```

```json
{
  "name": "Snack",
  "code": "SNK"
}
```

### Response

```json
{
  "message": "Category Created"
}
```

---

# Pagination Example

```http
GET /api/product?page=0&size=10
```

| Parameter | Description         |
| --------- | ------------------- |
| page      | Current page number |
| size      | Total data per page |

---

# Validation

This project implements several validations:

* Unique product code
* Unique category name
* Unique supplier name
* Version validation for update process
* UUID validation
* Request body validation

Example:

```java
if(existingProduct.isPresent()){
    throw new DuplicateResourceException("Data Is Not Unique");
}
```

---

# Optimistic Locking

This project uses optimistic locking with `@Version`.

Example:

```java
if(!entity.getVersion().equals(req.getVersion())){
    throw new BadRequestException("Please Refresh The Page");
}
```

---

# Stock Flow

When supply data is created:

* Product stock automatically increases
* Supply detail is stored
* Product history is recorded
* Duplicate product inside supply request will accumulate quantity

---

# Error Handling

Example error response:

```json
{
  "message": "Data Is Not Unique"
}
```

Common Exceptions:

* NotFoundException
* BadRequestException
* DuplicateResourceException

---

# Developer Notes

* Uses generic helper methods for base entity handling
* Uses transactional service layer
* Follows layered architecture
* Clean and maintainable code structure

Example:

```java
protected <T extends BaseModel> void createBase(T entity){
    entity.setId(UUID.randomUUID());
    entity.setCreatedAt(LocalDateTime.now());
}
```

---

# Future Improvements

* JWT Authentication
* Swagger Documentation
* Unit Testing
* Docker Support
* CI/CD Pipeline
* Role Based Access Control

---
