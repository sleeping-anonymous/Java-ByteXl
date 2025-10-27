<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.webapp.dao.AttendanceDAO" %>
<%@ page import="com.webapp.model.Attendance" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Attendance Records</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .attendance-records {
            max-width: 1200px;
            margin: 30px auto;
            padding: 20px;
        }
        .page-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 40px;
            border-radius: 10px;
            text-align: center;
            margin-bottom: 30px;
        }
        .filter-section {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }
        .filter-section input,
        .filter-section button {
            padding: 10px;
            margin-right: 10px;
            border: 2px solid #ddd;
            border-radius: 5px;
        }
        .filter-section button {
            background: #667eea;
            color: white;
            border: none;
            cursor: pointer;
        }
        table {
            width: 100%;
            background: white;
            border-collapse: collapse;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        th {
            background: #667eea;
            color: white;
            padding: 15px;
            text-align: left;
            font-weight: 600;
        }
        td {
            padding: 12px 15px;
            border-bottom: 1px solid #f0f0f0;
        }
        tr:hover {
            background: #f9f9f9;
        }
        .status-badge {
            display: inline-block;
            padding: 5px 12px;
            border-radius: 20px;
            font-size: 12px;
            font-weight: 600;
        }
        .status-present {
            background: #d4edda;
            color: #155724;
        }
        .status-absent {
            background: #f8d7da;
            color: #721c24;
        }
        .status-late {
            background: #fff3cd;
            color: #856404;
        }
        .no-records {
            text-align: center;
            padding: 60px 20px;
            background: white;
            border-radius: 8px;
            color: #999;
        }
        .stats-section {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }
        .stat-card {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            text-align: center;
        }
        .stat-card h3 {
            font-size: 32px;
            margin: 10px 0;
            color: #667eea;
        }
        .stat-card p {
            color: #666;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <%
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        String filterStudentId = request.getParameter("studentId");
        String filterDate = request.getParameter("date");
        
        List<Attendance> attendanceList;
        
        if (filterStudentId != null && !filterStudentId.trim().isEmpty()) {
            attendanceList = attendanceDAO.getAttendanceByStudentId(Integer.parseInt(filterStudentId));
        } else if (filterDate != null && !filterDate.trim().isEmpty()) {
            attendanceList = attendanceDAO.getAttendanceByDate(filterDate);
        } else {
            attendanceList = attendanceDAO.getAllAttendance();
        }
        
        // Calculate statistics
        int totalRecords = attendanceList.size();
        int presentCount = 0;
        int absentCount = 0;
        int lateCount = 0;
        
        for (Attendance att : attendanceList) {
            if ("Present".equals(att.getStatus())) presentCount++;
            else if ("Absent".equals(att.getStatus())) absentCount++;
            else if ("Late".equals(att.getStatus())) lateCount++;
        }
    %>
    
    <div class="attendance-records">
        <div class="page-header">
            <h1>Attendance Records</h1>
            <p>View and filter student attendance records</p>
        </div>

        <div class="stats-section">
            <div class="stat-card">
                <p>Total Records</p>
                <h3><%= totalRecords %></h3>
            </div>
            <div class="stat-card">
                <p>Present</p>
                <h3 style="color: #28a745;"><%= presentCount %></h3>
            </div>
            <div class="stat-card">
                <p>Absent</p>
                <h3 style="color: #dc3545;"><%= absentCount %></h3>
            </div>
            <div class="stat-card">
                <p>Late</p>
                <h3 style="color: #ffc107;"><%= lateCount %></h3>
            </div>
        </div>

        <div class="filter-section">
            <form method="get" style="display: inline;">
                <input type="number" name="studentId" placeholder="Student ID" 
                       value="<%= filterStudentId != null ? filterStudentId : "" %>" min="1">
                <button type="submit">Filter by Student</button>
            </form>
            
            <form method="get" style="display: inline; margin-left: 10px;">
                <input type="date" name="date" 
                       value="<%= filterDate != null ? filterDate : "" %>">
                <button type="submit">Filter by Date</button>
            </form>
            
            <a href="viewAttendance.jsp" style="margin-left: 10px; padding: 10px 15px; background: #6c757d; color: white; text-decoration: none; border-radius: 5px;">Clear Filters</a>
        </div>

        <% if (attendanceList.isEmpty()) { %>
            <div class="no-records">
                <h2>No Attendance Records Found</h2>
                <p>There are no attendance records matching your criteria.</p>
                <a href="attendance.jsp" class="btn" style="margin-top: 20px;">Mark Attendance</a>
            </div>
        <% } else { %>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Student ID</th>
                        <th>Student Name</th>
                        <th>Date</th>
                        <th>Subject</th>
                        <th>Status</th>
                        <th>Remarks</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Attendance att : attendanceList) { 
                        String statusClass = "status-present";
                        if ("Absent".equals(att.getStatus())) {
                            statusClass = "status-absent";
                        } else if ("Late".equals(att.getStatus())) {
                            statusClass = "status-late";
                        }
                    %>
                    <tr>
                        <td><%= att.getAttendanceId() %></td>
                        <td><%= att.getStudentId() %></td>
                        <td><%= att.getStudentName() %></td>
                        <td><%= att.getDate() %></td>
                        <td><%= att.getSubject() %></td>
                        <td>
                            <span class="status-badge <%= statusClass %>">
                                <%= att.getStatus() %>
                            </span>
                        </td>
                        <td><%= att.getRemarks() != null ? att.getRemarks() : "-" %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        <% } %>

        <div style="text-align: center; margin-top: 30px;">
            <a href="attendance.jsp" class="btn">Mark New Attendance</a>
            <a href="index.html" class="btn" style="margin-left: 10px;">Home</a>
        </div>
    </div>
</body>
</html>

