# Implementation Plan - Refactor AC Shop Management System

Refactor the existing monolithic [AcManagementSystem.java](file:///d:/CSE_23002171210076_DS_DBMS_JAVA2/AcManagementSystem.java) into a professional, industry-standard project using Maven, proper design patterns (DAO, MVC), and organized package structure.

## Proposed Changes

### Project Structure [NEW]
- Initialize a Maven project structure.
- Rename the root directory to `AC-Shop-Management-Software`.
- Create `pom.xml` with dependencies for MySQL and potentially a logging framework.

### `com.acshop.model` [NEW]
- #### [NEW] [Client.java](file:///d:/AC-Shop-Management-Software/src/main/java/com/acshop/model/Client.java)
    - Pure POJO for Client data.

### `com.acshop.dao` [NEW]
- #### [NEW] [ClientDAO.java](file:///d:/AC-Shop-Management-Software/src/main/java/com/acshop/dao/ClientDAO.java)
    - Interface for database operations.
- #### [NEW] [ClientDAOImpl.java](file:///d:/AC-Shop-Management-Software/src/main/java/com/acshop/dao/ClientDAOImpl.java)
    - Implementation of JDBC operations.

### `com.acshop.util` [NEW]
- #### [NEW] [DBConnection.java](file:///d:/AC-Shop-Management-Software/src/main/java/com/acshop/util/DBConnection.java)
    - Utility class for managing database connections.
- #### [NEW] [DateUtil.java](file:///d:/AC-Shop-Management-Software/src/main/java/com/acshop/util/DateUtil.java)
    - Static utility methods for date calculations currently in [Client](file:///d:/CSE_23002171210076_DS_DBMS_JAVA2/AcManagementSystem.java#14-195).

### `com.acshop.view` [NEW]
- #### [NEW] [MainFrame.java](file:///d:/AC-Shop-Management-Software/src/main/java/com/acshop/view/MainFrame.java)
    - Refactored UI code, separating component initialization from logic.

### `com.acshop.controller` [NEW]
- #### [NEW] [ClientController.java](file:///d:/AC-Shop-Management-Software/src/main/java/com/acshop/controller/ClientController.java)
    - Coordinates between `MainFrame` and `ClientDAO`.

### Configuration
- #### [NEW] [db.properties](file:///d:/AC-Shop-Management-Software/src/main/resources/db.properties)
    - Store database credentials.

### Cleanup [DELETE]
- #### [DELETE] [AcManagementSystem.java](file:///d:/CSE_23002171210076_DS_DBMS_JAVA2/AcManagementSystem.java)

## Verification Plan

### Automated Tests
- Since this is a UI-heavy application, automated unit tests will be written for:
    - `DateUtil` (verifying 4-month service intervals).
    - `ClientDAOImpl` (using an H2 in-memory database or a test schema if available).
- Command: `mvn test`

### Manual Verification
1. **Database Setup**: Ensure MySQL is running and the `ac_shop` database exists with the `clients` table.
2. **Build**: Run `mvn clean package` to generate the executable JAR.
3. **Execution**: Run `java -jar target/ac-shop-management-1.0-SNAPSHOT.jar`.
4. **CRUD Operations**:
    - Add a client and verify it appears in the database.
    - Search for a client by name and date.
    - Update client details.
    - Perform a service and verify `remainingServices` decrements and `nextServiceDate` updates.
    - Remove a client.
5. **UI Check**: Verify the UI is responsive and professional-looking.
