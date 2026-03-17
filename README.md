# AC Shop Management Software

A professional, enterprise-grade Java Desktop Application for managing air conditioning shop clients, service schedules, and packages. Refactored from a monolithic script into a clean, modular MVC (Model-View-Controller) architecture using Maven and JDBC.

## Features

- **Client Management**: Create, read, update, and delete (CRUD) client records reliably.
- **Smart Service Tracking**: Automatically calculates the next service date based on purchase date or the last service date.
- **Service Packages**: Manage and renew 3-service packages seamlessly, decrementing remaining services upon completion.
- **Search Functionality**: Quickly lookup clients by Name or by Next Service Date.
- **Persistent Data Storage**: Utilizes MySQL to ensure all records are safely permanently stored.
- **Professional GUI**: A clean, intuitive Java Swing interface for easy daily operations.

## Architecture

The application was refactored with industry standards in mind:

- **Model (`com.acshop.model`)**: Pure Java Objects (POJO) representing data entities (e.g., `Client`).
- **DAO (`com.acshop.dao`)**: Data Access Object pattern abstracts and encapsulates all access to the data source.
- **Controller (`com.acshop.controller`)**: Acts as a bridge between the View and the DAO, handling the application logic.
- **View (`com.acshop.view`)**: The presentation layer (`MainFrame`) built with Java Swing.
- **Utils (`com.acshop.util`)**: Reusable components like `DBConnection` (Singleton) and `DateUtil`.

## Prerequisites

- **Java JDK 17** or higher
- **MySQL Server** (Running on `localhost:3306`)
- **Maven** (Optional, for building the executable)

## Setup and Installation

1. **Database Setup**:
   Create a database named `ac_shop` in your local MySQL server.
   ```sql
   CREATE DATABASE ac_shop;
   USE ac_shop;
   CREATE TABLE clients (
       name VARCHAR(255) PRIMARY KEY,
       dop DATE,
       address VARCHAR(255),
       phone BIGINT,
       acModel VARCHAR(255),
       price INT,
       description TEXT,
       remainingServices INT,
       currentPackDate DATE,
       nextServiceDate DATE
   );
   ```

2. **Database Configuration**:
   Verify your MySQL credentials in `src/main/resources/db.properties`. The default is:
   ```properties
   db.url=jdbc:mysql://localhost:3306/ac_shop
   db.user=root
   db.password=
   db.driver=com.mysql.cj.jdbc.Driver
   ```

3. **Running the Application**:
   If Maven is installed, you can build the project:
   ```bash
   mvn clean package
   java -jar target/ac-shop-management-1.0-SNAPSHOT.jar
   ```

## Author

**Abdur Rahman**
GitHub: [@Abdurrehman510](https://github.com/Abdurrehman510)
