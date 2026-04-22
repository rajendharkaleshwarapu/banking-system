# Banking System

This is an advanced Java Spring Boot application for a core banking system. It provides RESTful APIs for managing customers, accounts, and transactions with full CRUD operations, validation, exception handling, and security.

## Features

- **Customer Management**: Create, read, update, delete customers with validation
- **Account Management**: Create accounts, deposit, withdraw, transfer funds
- **Transaction History**: View transaction logs for accounts
- **Security**: Basic authentication for API access
- **Database**: MySQL with JPA/Hibernate, H2 for testing
- **Validation**: Bean validation with custom constraints
- **Exception Handling**: Global exception handler for REST APIs
- **Testing**: Unit tests with Mockito and Spring Boot Test

## Technologies

- Java 17
- Spring Boot 3.1.0
- Spring Data JPA
- Spring Security
- Hibernate
- MySQL / H2
- Maven
- JUnit 5
- Mockito

## Structure

- `src/main/java/com/bankingsystem/controller`: REST controllers
- `src/main/java/com/bankingsystem/model`: JPA entities
- `src/main/java/com/bankingsystem/repository`: Spring Data repositories
- `src/main/java/com/bankingsystem/service`: Business logic services
- `src/main/java/com/bankingsystem/util`: Utility classes
- `src/main/java/com/bankingsystem/exception`: Custom exceptions and global handler
- `src/main/java/com/bankingsystem/config`: Security configuration
- `src/main/java/com/bankingsystem/main`: Application entry point
- `src/main/resources`: Configuration, SQL scripts, static web files
- `src/test/java/com/bankingsystem`: Unit tests

## Setup

1. **Database**: Ensure MySQL is running. Update `application.properties` with your MySQL credentials.
2. **Build**: `mvn clean install`
3. **Run**: `mvn spring-boot:run`
4. **Test**: `mvn test`

## API Endpoints

### Customers
- `GET /api/customers` - Get all customers
- `GET /api/customers/{id}` - Get customer by ID
- `POST /api/customers` - Create customer
- `PUT /api/customers/{id}` - Update customer
- `DELETE /api/customers/{id}` - Delete customer

### Accounts
- `GET /api/accounts/{id}` - Get account by ID
- `GET /api/accounts/customer/{customerId}` - Get accounts by customer
- `POST /api/accounts` - Create account
- `POST /api/accounts/{id}/deposit` - Deposit money
- `POST /api/accounts/{id}/withdraw` - Withdraw money
- `POST /api/accounts/transfer` - Transfer between accounts
- `GET /api/accounts/{id}/balance` - Get account balance

### Transactions
- `GET /api/transactions/{id}` - Get transaction by ID
- `GET /api/transactions/account/{accountId}` - Get transactions by account
- `GET /api/transactions` - Get all transactions

## Authentication

Use Basic Auth with username `admin` and password `password` (or the generated one from logs).

## Web Interface

Visit `http://localhost:8080` for a simple web interface to test the APIs.

## Testing

Run unit tests: `mvn test`

The application includes comprehensive unit tests for services with mocked dependencies.
