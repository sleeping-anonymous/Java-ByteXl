<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Attendance Portal - Part C</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .attendance-container {
            max-width: 700px;
            margin: 50px auto;
            background: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .page-header {
            text-align: center;
            margin-bottom: 30px;
        }
        .part-badge {
            display: inline-block;
            background: #667eea;
            color: white;
            padding: 5px 15px;
            border-radius: 20px;
            font-size: 12px;
            margin-bottom: 10px;
        }
        .page-header h1 {
            color: #333;
            margin-bottom: 10px;
        }
        .page-header p {
            color: #666;
            font-size: 14px;
        }
        .form-row {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            font-weight: 500;
        }
        .form-group input,
        .form-group select,
        .form-group textarea {
            width: 100%;
            padding: 12px;
            border: 2px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
            font-family: inherit;
        }
        .form-group input:focus,
        .form-group select:focus,
        .form-group textarea:focus {
            outline: none;
            border-color: #667eea;
        }
        .status-options {
            display: flex;
            gap: 15px;
        }
        .status-option {
            flex: 1;
        }
        .status-option input[type="radio"] {
            display: none;
        }
        .status-option label {
            display: block;
            padding: 12px;
            border: 2px solid #ddd;
            border-radius: 5px;
            text-align: center;
            cursor: pointer;
            transition: all 0.3s;
        }
        .status-option input[type="radio"]:checked + label {
            border-color: #667eea;
            background: #667eea;
            color: white;
        }
        .button-group {
            display: flex;
            gap: 15px;
            margin-top: 30px;
        }
        .button-group button,
        .button-group a {
            flex: 1;
        }
        .btn-secondary {
            background: #6c757d;
        }
        .btn-secondary:hover {
            background: #5a6268;
        }
    </style>
</head>
<body>
    <div class="attendance-container">
        <div class="page-header">
            <span class="part-badge">Part C</span>
            <h1>Student Attendance Portal</h1>
            <p>Student Attendance Portal Using JSP and Servlet</p>
        </div>

        <form action="attendance" method="post">
            <input type="hidden" name="action" value="mark">
            
            <div class="form-row">
                <div class="form-group">
                    <label for="studentId">Student ID: *</label>
                    <input type="number" id="studentId" name="studentId" 
                           placeholder="Enter student ID" required min="1">
                </div>
                
                <div class="form-group">
                    <label for="date">Date: *</label>
                    <input type="date" id="date" name="date" 
                           value="<%= LocalDate.now() %>" required>
                </div>
            </div>

            <div class="form-group">
                <label for="studentName">Student Name: *</label>
                <input type="text" id="studentName" name="studentName" 
                       placeholder="Enter student name" required>
            </div>

            <div class="form-group">
                <label for="subject">Subject: *</label>
                <select id="subject" name="subject" required>
                    <option value="">-- Select Subject --</option>
                    <option value="Mathematics">Mathematics</option>
                    <option value="Science">Science</option>
                    <option value="English">English</option>
                    <option value="History">History</option>
                    <option value="Computer Science">Computer Science</option>
                    <option value="Physics">Physics</option>
                    <option value="Chemistry">Chemistry</option>
                    <option value="Biology">Biology</option>
                </select>
            </div>

            <div class="form-group">
                <label>Attendance Status: *</label>
                <div class="status-options">
                    <div class="status-option">
                        <input type="radio" id="present" name="status" value="Present" required>
                        <label for="present">Present</label>
                    </div>
                    <div class="status-option">
                        <input type="radio" id="absent" name="status" value="Absent">
                        <label for="absent">Absent</label>
                    </div>
                    <div class="status-option">
                        <input type="radio" id="late" name="status" value="Late">
                        <label for="late">Late</label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="remarks">Remarks (Optional):</label>
                <textarea id="remarks" name="remarks" rows="3" 
                          placeholder="Enter any additional remarks..."></textarea>
            </div>

            <div class="button-group">
                <button type="submit" class="btn">Mark Attendance</button>
                <a href="viewAttendance.jsp" class="btn btn-secondary">View Records</a>
            </div>
        </form>

        <div style="text-align: center; margin-top: 30px;">
            <a href="index.html" style="color: #667eea; text-decoration: none;">
                ← Back to Home
            </a>
        </div>
    </div>

    <script>
        // Set today's date as default
        document.getElementById('date').valueAsDate = new Date();
    </script>
</body>
</html>

