# The Bank of Banks 🏦

A Java-based banking system simulation that demonstrates object-oriented programming principles and clean architecture.

## Project Overview

The Bank of Banks is a console application that simulates basic banking operations including:

- Client management (CRUD operations)
- Account management (Current and Savings accounts)
- Banking operations (deposits, withdrawals, transfers)
- Interest calculations for savings accounts
- Overdraft facility for current accounts

## Technologies Used

- Java 21
- Maven
- JUnit 5
- Mockito

## Key Features

- Builder Pattern for object creation
- Repository Pattern for data access
- Interface segregation
- Clean Architecture principles
- Input validation
- Error handling
- Unit testing

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven

### Installation

1. Clone the repository

```bash
git clone https://github.com/yourusername/TheBankOfBanks.git
```

2. Build the project

```bash
mvn clean install
```

2. Run the application

```bash
mvn exec:java
```

## Running Tests

```bash
mvn test
```

## Project Highlights

- ✨ **Robust Input Validation**

  - CPF format verification
  - Numeric input handling
  - Date format validation
  - Empty input prevention

- 🎯 **Clean Code Principles**

  - Single Responsibility Principle
  - Interface Segregation
  - Meaningful naming conventions

- 🏗️ **Design Patterns**

  - Builder pattern for object creation
  - Repository pattern for data access
  - Command pattern for menu operations

- 🧪 **Comprehensive Testing**

  - Unit tests with JUnit 5
  - Mock objects with Mockito
