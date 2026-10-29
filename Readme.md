# 🚗 Smart Parking Management System

A backend application built with **Java 21, Spring Boot 3, Spring Data JPA, and PostgreSQL** to manage parking lots, parking spots, vehicles, parking sessions, and simulated payments through RESTful APIs.

## 📌 Overview

The Smart Parking Management System simplifies parking operations by providing APIs for vehicle registration, parking entry and exit, spot allocation, fee calculation, payment tracking, and parking occupancy monitoring.

The project follows a layered architecture with DTOs, mappers, repositories, services, and centralized exception handling.

## ✨ Features

### User Management
- Register users with validated input.
- Store passwords using BCrypt hashing.
- Assign the default `CUSTOMER` role during public registration.

### Parking Lot Management
- Create, retrieve, update, and delete parking lots.
- Search by parking lot name and location.
- Support pagination, sorting, and filtering.
- View parking occupancy statistics.

### Parking Spot Management
- Create and manage parking spots.
- Allocate spots according to vehicle/spot type.
- Track spot status: `AVAILABLE`, `OCCUPIED`, and `MAINTENANCE`.
- Retrieve available spots by parking lot and spot type.

### Vehicle Management
- Register vehicles against users.
- Prevent duplicate vehicle registrations.
- Retrieve vehicles by user.
- Update and delete vehicle records.

### Parking Sessions
- Record vehicle entry and exit.
- Prevent a vehicle from having multiple active parking sessions.
- Calculate parking charges based on vehicle type and parking duration.
- Release spots when a vehicle exits.
- Retrieve active sessions and parking history.
- Search sessions using status, vehicle, parking lot, and entry-time filters.

### Payment Management
- Support simulated payments through `CASH`, `UPI`, and `CARD`.
- Generate unique transaction IDs.
- Prevent multiple payment records for the same parking session.
- Retrieve payment details by payment ID or parking session.

### Exception Handling and API Search
- Custom exceptions for missing resources, invalid operations, and conflicts.
- Standardized JSON error responses.
- Pagination, sorting, and filtering using Spring Data JPA.

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming language |
| Spring Boot 3 | Backend application framework |
| Spring Web | REST API development |
| Spring Data JPA | Data persistence |
| Hibernate | ORM |
| PostgreSQL | Relational database |
| Maven | Dependency and build management |
| Lombok | Boilerplate reduction |
| Jakarta Bean Validation | Request validation |
| Spring Security / BCrypt | Password hashing; full authentication planned |
| JUnit 5 / Mockito | Automated testing — in progress |

## 🏗️ Architecture

```text
Client / Postman
       |
       v
REST Controllers
       |
       v
Request DTOs
       |
       v
Service Layer
       |
       v
Repositories
       |
       v
Spring Data JPA / Hibernate
       |
       v
PostgreSQL
```

### Project Structure

```text
src/
├── main/
│   ├── java/parking/
│   │   ├── config/
│   │   ├── controller/
│   │   ├── dto/
│   │   │   ├── request/
│   │   │   └── response/
│   │   ├── entity/
│   │   ├── exception/
│   │   ├── mapper/
│   │   ├── repository/
│   │   ├── security/
│   │   ├── service/
│   │   │   └── impl/
│   │   └── util/
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/parking/
```

## ⚙️ Prerequisites

Install the following before running the application:

- Java 21 or later compatible with the project configuration
- Maven, or use the included Maven Wrapper
- PostgreSQL
- Postman or another REST API client

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/DnyaneshwarBajad/Smart_parking.git
cd Smart_parking
```

### 2. Create the PostgreSQL database

Open PostgreSQL and execute:

```sql
CREATE DATABASE smart_parking;
```

### 3. Configure the database

Update `src/main/resources/application.properties` with your local configuration:

```properties
spring.application.name=smart-parking

spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/smart_parking}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Set `DB_PASSWORD` in your environment before starting the application. Adjust the configuration to match your existing project settings. Never commit real passwords or other secrets.

### 4. Run the application

Using Maven:

```bash
mvn spring-boot:run
```

Or, on Windows with the Maven Wrapper:

```powershell
.\mvnw.cmd spring-boot:run
```

The application will normally be available at:

```text
http://localhost:8080
```

## 📡 API Endpoints

Base URL: `http://localhost:8080`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/users/register` | Register a user |
| POST | `/api/parking-lots` | Create a parking lot |
| GET | `/api/parking-lots` | List parking lots with pagination and sorting |
| GET | `/api/parking-lots/{id}` | Get a parking lot |
| PUT | `/api/parking-lots/{id}` | Update a parking lot |
| DELETE | `/api/parking-lots/{id}` | Delete a parking lot |
| GET | `/api/parking-lots/{id}/occupancy` | Get occupancy statistics |
| GET | `/api/parking-lots/search` | Search parking lots |
| POST | `/api/parking-spots` | Create a parking spot |
| GET | `/api/parking-spots/{id}` | Get a parking spot |
| GET | `/api/parking-spots/available` | Find available spots |
| POST | `/api/vehicles` | Register a vehicle |
| GET | `/api/vehicles/user/{userId}` | Get vehicles by user |
| POST | `/api/parking-sessions/entry` | Start a parking session |
| POST | `/api/parking-sessions/{sessionId}/exit` | Complete a parking session |
| GET | `/api/parking-sessions/active` | Retrieve active sessions |
| GET | `/api/parking-sessions/search` | Search parking sessions |
| POST | `/api/payments` | Create a simulated payment |
| GET | `/api/payments/{id}` | Get payment details |

Refer to the controllers for the complete endpoint list and request requirements.

## 🧪 Example Requests

### Register a user

`POST /api/users/register`

```json
{
  "name": "Demo User",
  "email": "demo@example.com",
  "password": "StrongPassword123"
}
```

### Create a parking lot

`POST /api/parking-lots`

```json
{
  "name": "City Mall Parking",
  "location": "Nanded",
  "totalSpots": 100
}
```

### Create a parking spot

`POST /api/parking-spots`

```json
{
  "spotNumber": "C-01",
  "spotType": "CAR",
  "parkingLotId": 1
}
```

### Register a vehicle

`POST /api/vehicles`

```json
{
  "vehicleNumber": "MH12AB1234",
  "vehicleType": "CAR",
  "userId": 1
}
```

### Start a parking session

`POST /api/parking-sessions/entry`

```json
{
  "vehicleId": 1,
  "parkingLotId": 1,
  "spotType": "CAR"
}
```

### Complete a parking session

`POST /api/parking-sessions/1/exit`

The response includes the exit time, calculated amount, and completed session status.

### Make a simulated payment

`POST /api/payments`

```json
{
  "parkingSessionId": 1,
  "paymentMethod": "UPI"
}
```

**Note:** The payment endpoint simulates a successful payment. It is not connected to a real payment gateway.

## 🧠 Engineering Concepts

This project demonstrates:

- Layered architecture and separation of concerns.
- RESTful API design and HTTP status codes.
- DTO-based request and response handling.
- Entity relationships and relational database modeling.
- Spring Data JPA derived queries and JPQL.
- Transaction management using `@Transactional`.
- Custom exception handling.
- Pagination, sorting, and filtering.
- Business rules for parking allocation and billing.

## 🗺️ Roadmap

- [ ] Expand automated unit and integration testing with JUnit 5 and Mockito.
- [ ] Implement configurable dynamic parking pricing.
- [ ] Add Redis caching and concurrency controls for spot allocation.
- [ ] Containerize the application and PostgreSQL using Docker Compose.
- [ ] Integrate Kafka for asynchronous parking and payment events.
- [ ] Implement Spring Security, JWT authentication, and role-based authorization.
- [ ] Add manager and administrator management.
- [ ] Add email notifications and scheduled tasks.
- [ ] Add Swagger/OpenAPI documentation.
- [ ] Build a React frontend and admin dashboard.
- [ ] Deploy the application and document the live API.

## 🔐 Security Notes

- Public registration assigns the `CUSTOMER` role by default.
- BCrypt is used for password hashing.
- Full JWT authentication and endpoint authorization are planned.
- The simulated payment implementation does not process real financial transactions.
- Production deployment requires secure secrets management and production database configuration.

