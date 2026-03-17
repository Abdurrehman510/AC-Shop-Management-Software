<h1 align="center">AC Shop Management Software ❄️💻</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/>
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven"/>
  <img src="https://img.shields.io/badge/Swing-GUI-blue?style=for-the-badge" alt="Java Swing"/>
</p>

<p align="center">
  <strong>A professional, enterprise-grade Java Desktop Application designed to streamline the daily operations of air conditioning shops.</strong>
</p>

---

## 📌 Project Overview

Managing customer records, tracking service histories, and ensuring timely air conditioning maintenance can be overwhelming. **AC Shop Management Software** solves this problem by providing a robust, standalone Desktop Desktop Application. 

Originally starting as a monolithic script, this project has been fully re-architected into a **clean, modular MVC (Model-View-Controller)** pattern. It leverages **JDBC** for secure, permanent database storage and features an intuitive Graphical User Interface (GUI) built with Java Swing.

This software isn't just a data entry tool; it's a smart administrative assistant that tracks complex 3-step service packages and calculates the exact dates when your customers need their next AC service.

---

## ✨ Key Features

- 👤 **Comprehensive Client Management**: Create, read, update, and delete (CRUD) detailed client records seamlessly.
- 📅 **Smart Service Tracking**: Never miss a maintenance window. The system automatically calculates the exact date for the next service based on the date of purchase or previous service.
- 📦 **Service Package Lifecycle**: Easily manage 3-service maintenance packages. Performing a service automatically decrements the remaining balance and pushes the next service date forward by 4 months.
- 🔍 **Advanced Search**: Instantly lookup clients by their Name or by their exact Next Service Date.
- 💾 **Persistent SQL Storage**: No data is lost when you close the app. Everything is safely backed up in a local MySQL relational database.
- 🖥️ **Professional GUI**: A clean, responsive, and user-friendly Java Swing interface tailored for shop administrators.

---

## 🏗️ Architecture

The codebase adheres strictly to enterprise Java industry standards:

| Layer | Package | Description |
| :--- | :--- | :--- |
| **Model** | `com.acshop.model` | Pure Java Objects (POJO) representing data entities (e.g., `Client`). |
| **DAO** | `com.acshop.dao` | Data Access Object pattern abstracts and encapsulates all raw database SQL queries. |
| **Controller** | `com.acshop.controller` | The application logic bridge connecting the View to the DAO. |
| **View** | `com.acshop.view` | The presentation layer (`MainFrame`) built with `javax.swing`. |
| **Utilities** | `com.acshop.util` | Reusable singleton database connections (`DBConnection`) and smart date calculators (`DateUtil`). |

---

## 🚀 Getting Started

### Prerequisites

Ensure you have the following installed on your local machine:
- **Java Development Kit (JDK) 17** or higher
- **MySQL Server** (Running on `localhost:3306`)
- *(Optional)* **Maven** for compiling from source.

### 1. Database Setup

Create the required database and schemas in your MySQL server:

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

### 2. Configuration Configuration

Verify your MySQL secure credentials by editing the properties file located at: `src/main/resources/db.properties`. 

```properties
db.url=jdbc:mysql://localhost:3306/ac_shop
db.user=root
db.password=        # Add your MySQL password here if you have one
db.driver=com.mysql.cj.jdbc.Driver
```

### 3. Running the Application

**Option A: Using the provided Windows Batch Script**
Simply double-click the `run.bat` file in the root directory!

**Option B: Using Maven (If installed)**
```bash
mvn clean package
java -jar target/ac-shop-management-1.0-SNAPSHOT.jar
```

---

## 👨‍💻 Author

**Abdur Rahman**
- GitHub: [@Abdurrehman510](https://github.com/Abdurrehman510)

---
<p align="center">
  <i>If you find this project useful, please consider giving it a ⭐ on GitHub!</i>
</p>
