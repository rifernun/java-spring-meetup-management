# Meetup Management System

A robust and scalable Meetup Management System built with **Spring Boot 4.0.5** and **Java 21**. This project follows a modular architecture (Modular Monolith) to manage participants, events, and enrollments with strict business rules and high-performance standards.

## Technical Stack

- **Backend:** Java 21, Spring Boot 4.0.5
- **Persistence:** PostgreSQL 18, Spring Data JPA
- **Database Migrations:** Flyway
- **Infrastructure:** Docker & Docker Compose
- **Utilities:** Lombok, MapStruct (implied by mapper structure), Jakarta Validation
- **Architecture:** Modular Monolith with DDD influences

## Architecture & Design Patterns

The project is structured into cohesive modules, each responsible for a specific domain:

- **Participant Module:** Manages user registration and profiles.
- **Event Module:** Handles meetup details, schedules, and capacity limits.
- **Enrollment Module:** Orchestrates the relationship between Participants and Events, enforcing business constraints.
- **Shared Module:** Contains cross-cutting concerns like global exception handling, base entities, and shared utilities.

### Key Patterns Applied:
- **SOLID Principles:** High decoupling and single responsibility per service.
- **Layered Architecture:** Clear separation between Controller, Service, and Repository layers.
- **Global Exception Handling:** Centralized error management using `@ControllerAdvice`.
- **Base Entity:** Reusable audit fields and ID management.

##️ Core Logic & Business Rules

### Enrollment Lifecycle
1. **Validation:** Before creating an enrollment, the system verifies if both the Participant and the Event exist.
2. **Uniqueness:** A participant cannot have more than one `ACTIVE` enrollment for the same event.
3. **Capacity Management:** (Planned/In-progress) The system utilizes a `CountRemainingSpots` utility to calculate availability based on active enrollments and event `maxCapacity`.
4. **Cancellations:** Enrollments are not hard-deleted; they transition to a `CANCELLED` status.

## Setup & Installation

### Prerequisites
- Docker & Docker Compose
- JDK 21
- Maven 3.9+

### Running with Docker
The project includes a `docker-compose.yml` for easy database setup:

```bash
docker-compose up -d
```

### Running the Application
1. Clone the repository.
2. Configure your `.env` or environment variables (see `docker-compose.yml` defaults).
3. Run the application using Maven:

```bash
./mvnw spring-boot:run
```

## 🔌 API Documentation

The API documentation is automatically generated using **Swagger (OpenAPI 3)**.

### Swagger UI
You can access the interactive API documentation and test the endpoints directly from your browser:
- **URL:** `http://localhost:8080/swagger-ui.html`

### Authentication (`/api/v1/auth`)
- `POST /login`: Authenticate a user and receive a JWT token.
- `POST /register`: Register a new user account.

### Users (Admin) (`/api/v1/admin/users`)
- `PATCH /{id}/role`: Update a user's role to ADMIN.

### Participants (`/api/v1/participants`)
- `GET /{id}`: Get details of a participant.
- `DELETE /{id}`: Remove a participant.
- `PUT /{id}`: Update participant profile.
- `PUT /me`: Update currently authenticated participant's profile.
- `GET /me`: Get currently authenticated participant's details.
- `GET /`: List all participants.

### Events (`/api/v1/events`)
- `POST /`: Create a new meetup (Admin only).
- `GET /{id}`: Get event details.
- `DELETE /{id}`: Delete an event (Admin only).
- `PUT /{id}`: Update event info (Admin only).
- `GET /`: List all events.

### Enrollments (`/api/v1/enrollments`)
- `POST /`: Enroll a participant in an event.
- `PATCH /{id}/cancel`: Cancel an existing enrollment.
- `GET /{id}`: Get enrollment details.
- `GET /`: List all enrollments.

## Security & Quality
- **Validation:** All inputs are validated using Jakarta Bean Validation.
- **Audit:** Entities extend `BaseEntity` to track creation and update metadata.
- **Error Responses:** Standardized error format including timestamp, status code, and detailed messages.
