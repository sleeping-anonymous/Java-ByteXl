# Java Web Application - Assignment Submission

A beginner Java web application demonstrating Servlets, JSP, and JDBC.

## Assignment Parts

### Part A: User Login Using Servlet and HTML Form
- Login page with username and password
- Servlet-based authentication
- Session management

### Part B: Display Employee Records with JDBC and Servlet Integration
- Display employee data from MySQL database
- Filter employees by department
- Dynamic HTML table generation

### Part C: Student Attendance Portal Using JSP and Servlet
- Mark student attendance (Present/Absent/Late)
- View attendance records
- Filter by student ID or date

## Technologies Used

- Java 8
- Servlets & JSP
- MySQL Database
- Apache Maven
- Apache Tomcat

## Setup Instructions

### 1. Prerequisites
- Java JDK 8 or higher
- Apache Maven
- MySQL Server
- Apache Tomcat 9+

### 2. Database Setup

Open MySQL and run:
```sql
CREATE DATABASE webapp_db;
```

Import the database:
```bash
mysql -u root -p webapp_db < src/main/resources/database.sql
```

### 3. Configure Database Connection

Edit `src/main/java/com/webapp/util/DatabaseConnection.java` and update your MySQL credentials:
```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/webapp_db";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "your_password";
```

### 4. Build the Project

```bash
mvn clean install
```

### 5. Deploy to Tomcat

Copy `target/java-web-project.war` to your Tomcat `webapps/` folder and start Tomcat.

### 6. Access the Application

Open browser: `http://localhost:8080/java-web-project/`

## Demo Credentials

**Login (Part A):**
- Username: `admin`
- Password: `admin123`

**Student IDs for Attendance (Part C):**
- 101, 102, 103, 104, 105

## Project Structure

```
src/main/
├── java/com/webapp/
│   ├── servlets/         # Servlet controllers
│   ├── dao/              # Database access layer
│   ├── model/            # Data models
│   └── util/             # Database connection
└── webapp/
    ├── WEB-INF/
    │   └── web.xml       # Servlet configuration
    ├── css/
    │   └── style.css
    ├── index.html        # Home page
    ├── login.html        # Part A
    ├── employee.html     # Part B
    ├── attendance.jsp    # Part C
    └── viewAttendance.jsp
```

## Common Issues

**Cannot connect to database:**
- Verify MySQL is running
- Check credentials in DatabaseConnection.java
- Ensure webapp_db database exists

**404 Error:**
- Check Tomcat is running
- Verify WAR file is deployed
- Check URL context path

---

**Assignment submission for educational purposes**
