-- ============================================
-- Java Web Application - Database Setup Script
-- ============================================
-- This script creates the database and tables required for the application
-- Run this script before deploying the application

-- Create Database
CREATE DATABASE IF NOT EXISTS webapp_db;
USE webapp_db;

-- ============================================
-- Table: users
-- Purpose: Store user authentication data (Part A)
-- ============================================
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================
-- Table: employees
-- Purpose: Store employee records (Part B)
-- ============================================
CREATE TABLE IF NOT EXISTS employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    department VARCHAR(50) NOT NULL,
    position VARCHAR(50) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    hire_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_department (department),
    INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================
-- Table: attendance
-- Purpose: Store student attendance records (Part C)
-- ============================================
CREATE TABLE IF NOT EXISTS attendance (
    attendance_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    student_name VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    status ENUM('Present', 'Absent', 'Late') NOT NULL,
    subject VARCHAR(50) NOT NULL,
    remarks TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_student_id (student_id),
    INDEX idx_date (date),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ============================================
-- Insert Sample Data
-- ============================================

-- Sample Users (Part A)
-- Password: admin123, user123, teacher123
INSERT INTO users (username, password, email, full_name) VALUES
('admin', 'admin123', 'admin@example.com', 'Admin User'),
('john_doe', 'user123', 'john.doe@example.com', 'John Doe'),
('teacher1', 'teacher123', 'teacher@example.com', 'Sarah Teacher'),
('student1', 'student123', 'student@example.com', 'Mike Student');

-- Sample Employees (Part B)
INSERT INTO employees (first_name, last_name, email, department, position, salary, hire_date) VALUES
('John', 'Smith', 'john.smith@company.com', 'IT', 'Software Engineer', 75000.00, '2022-01-15'),
('Sarah', 'Johnson', 'sarah.johnson@company.com', 'HR', 'HR Manager', 65000.00, '2021-03-20'),
('Michael', 'Williams', 'michael.williams@company.com', 'Finance', 'Financial Analyst', 70000.00, '2022-06-10'),
('Emily', 'Brown', 'emily.brown@company.com', 'IT', 'Senior Developer', 85000.00, '2020-11-05'),
('David', 'Jones', 'david.jones@company.com', 'Sales', 'Sales Manager', 72000.00, '2021-08-15'),
('Lisa', 'Garcia', 'lisa.garcia@company.com', 'Marketing', 'Marketing Specialist', 60000.00, '2023-01-10'),
('Robert', 'Martinez', 'robert.martinez@company.com', 'IT', 'Database Administrator', 78000.00, '2021-12-01'),
('Jennifer', 'Rodriguez', 'jennifer.rodriguez@company.com', 'HR', 'Recruiter', 55000.00, '2023-02-14'),
('William', 'Lee', 'william.lee@company.com', 'Finance', 'Accountant', 62000.00, '2022-09-20'),
('Jessica', 'Walker', 'jessica.walker@company.com', 'Sales', 'Sales Executive', 68000.00, '2022-04-05'),
('Daniel', 'Hall', 'daniel.hall@company.com', 'IT', 'System Administrator', 73000.00, '2021-07-12'),
('Michelle', 'Allen', 'michelle.allen@company.com', 'Marketing', 'Content Writer', 52000.00, '2023-03-22'),
('Christopher', 'Young', 'christopher.young@company.com', 'Finance', 'Financial Controller', 90000.00, '2020-05-18'),
('Amanda', 'King', 'amanda.king@company.com', 'HR', 'HR Coordinator', 48000.00, '2023-04-01'),
('Matthew', 'Wright', 'matthew.wright@company.com', 'IT', 'DevOps Engineer', 82000.00, '2021-10-08');

-- Sample Attendance Records (Part C)
INSERT INTO attendance (student_id, student_name, date, status, subject, remarks) VALUES
(101, 'Alice Johnson', '2025-10-20', 'Present', 'Mathematics', 'On time'),
(102, 'Bob Williams', '2025-10-20', 'Present', 'Mathematics', 'Active participation'),
(103, 'Charlie Brown', '2025-10-20', 'Absent', 'Mathematics', 'Medical leave'),
(104, 'Diana Davis', '2025-10-20', 'Late', 'Mathematics', 'Arrived 10 minutes late'),
(105, 'Eve Martinez', '2025-10-20', 'Present', 'Mathematics', NULL),

(101, 'Alice Johnson', '2025-10-21', 'Present', 'Science', 'Excellent performance'),
(102, 'Bob Williams', '2025-10-21', 'Present', 'Science', NULL),
(103, 'Charlie Brown', '2025-10-21', 'Present', 'Science', 'Back from leave'),
(104, 'Diana Davis', '2025-10-21', 'Present', 'Science', NULL),
(105, 'Eve Martinez', '2025-10-21', 'Absent', 'Science', 'Sick'),

(101, 'Alice Johnson', '2025-10-22', 'Present', 'English', NULL),
(102, 'Bob Williams', '2025-10-22', 'Late', 'English', 'Traffic delay'),
(103, 'Charlie Brown', '2025-10-22', 'Present', 'English', NULL),
(104, 'Diana Davis', '2025-10-22', 'Present', 'English', NULL),
(105, 'Eve Martinez', '2025-10-22', 'Present', 'English', 'Recovered'),

(101, 'Alice Johnson', '2025-10-23', 'Present', 'Computer Science', 'Lab work completed'),
(102, 'Bob Williams', '2025-10-23', 'Present', 'Computer Science', NULL),
(103, 'Charlie Brown', '2025-10-23', 'Present', 'Computer Science', NULL),
(104, 'Diana Davis', '2025-10-23', 'Absent', 'Computer Science', 'Family emergency'),
(105, 'Eve Martinez', '2025-10-23', 'Present', 'Computer Science', NULL);

-- ============================================
-- Verify Data
-- ============================================

SELECT 'Users Table' as 'Table Name', COUNT(*) as 'Record Count' FROM users
UNION ALL
SELECT 'Employees Table', COUNT(*) FROM employees
UNION ALL
SELECT 'Attendance Table', COUNT(*) FROM attendance;

-- ============================================
-- Useful Queries for Testing
-- ============================================

-- View all users
-- SELECT * FROM users;

-- View all employees
-- SELECT * FROM employees;

-- View all attendance records
-- SELECT * FROM attendance ORDER BY date DESC, student_id;

-- View attendance by student
-- SELECT * FROM attendance WHERE student_id = 101 ORDER BY date DESC;

-- View attendance by date
-- SELECT * FROM attendance WHERE date = '2025-10-20';

-- Attendance statistics
-- SELECT 
--     status,
--     COUNT(*) as count,
--     ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM attendance), 2) as percentage
-- FROM attendance
-- GROUP BY status;

-- ============================================
-- End of Script
-- ============================================

