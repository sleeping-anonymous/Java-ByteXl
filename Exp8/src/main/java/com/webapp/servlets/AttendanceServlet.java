package com.webapp.servlets;

import com.webapp.dao.AttendanceDAO;
import com.webapp.model.Attendance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet for handling student attendance operations
 * Part C: Student Attendance Portal Using JSP and Servlet
 */
@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AttendanceDAO attendanceDAO;
    
    @Override
    public void init() throws ServletException {
        attendanceDAO = new AttendanceDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Redirect to attendance JSP page
        response.sendRedirect("attendance.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Get form parameters
        String action = request.getParameter("action");
        
        if ("mark".equals(action)) {
            markAttendance(request, response, out);
        } else {
            response.sendRedirect("attendance.jsp");
        }
        
        out.close();
    }
    
    /**
     * Mark attendance for a student
     */
    private void markAttendance(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
        try {
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            String studentName = request.getParameter("studentName");
            String date = request.getParameter("date");
            String status = request.getParameter("status");
            String subject = request.getParameter("subject");
            String remarks = request.getParameter("remarks");
            
            // Create attendance object
            Attendance attendance = new Attendance(studentId, studentName, date, status, subject, remarks);
            
            // Mark attendance in database
            boolean success = attendanceDAO.markAttendance(attendance);
            
            if (success) {
                displaySuccess(out, attendance);
            } else {
                displayError(out, "Failed to mark attendance. Please try again.");
            }
        } catch (NumberFormatException e) {
            displayError(out, "Invalid student ID format.");
        } catch (Exception e) {
            displayError(out, "An error occurred: " + e.getMessage());
        }
    }
    
    /**
     * Display success message
     */
    private void displaySuccess(PrintWriter out, Attendance attendance) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Attendance Marked</title>");
        out.println("    <link rel='stylesheet' href='css/style.css'>");
        out.println("    <style>");
        out.println("        .success-container {");
        out.println("            max-width: 600px;");
        out.println("            margin: 50px auto;");
        out.println("            background: white;");
        out.println("            padding: 40px;");
        out.println("            border-radius: 10px;");
        out.println("            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);");
        out.println("        }");
        out.println("        .success-icon {");
        out.println("            text-align: center;");
        out.println("            font-size: 64px;");
        out.println("            color: #4CAF50;");
        out.println("            margin-bottom: 20px;");
        out.println("        }");
        out.println("        .attendance-details {");
        out.println("            background: #f9f9f9;");
        out.println("            padding: 20px;");
        out.println("            border-radius: 5px;");
        out.println("            margin: 20px 0;");
        out.println("        }");
        out.println("        .detail-row {");
        out.println("            display: flex;");
        out.println("            justify-content: space-between;");
        out.println("            padding: 10px 0;");
        out.println("            border-bottom: 1px solid #ddd;");
        out.println("        }");
        out.println("        .detail-row:last-child {");
        out.println("            border-bottom: none;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='success-container'>");
        out.println("        <div class='success-icon'>✓</div>");
        out.println("        <h2 style='text-align: center; color: #4CAF50;'>Attendance Marked Successfully!</h2>");
        out.println("        <div class='attendance-details'>");
        out.println("            <div class='detail-row'>");
        out.println("                <strong>Student ID:</strong>");
        out.println("                <span>" + attendance.getStudentId() + "</span>");
        out.println("            </div>");
        out.println("            <div class='detail-row'>");
        out.println("                <strong>Student Name:</strong>");
        out.println("                <span>" + attendance.getStudentName() + "</span>");
        out.println("            </div>");
        out.println("            <div class='detail-row'>");
        out.println("                <strong>Date:</strong>");
        out.println("                <span>" + attendance.getDate() + "</span>");
        out.println("            </div>");
        out.println("            <div class='detail-row'>");
        out.println("                <strong>Status:</strong>");
        out.println("                <span>" + attendance.getStatus() + "</span>");
        out.println("            </div>");
        out.println("            <div class='detail-row'>");
        out.println("                <strong>Subject:</strong>");
        out.println("                <span>" + attendance.getSubject() + "</span>");
        out.println("            </div>");
        out.println("            <div class='detail-row'>");
        out.println("                <strong>Remarks:</strong>");
        out.println("                <span>" + (attendance.getRemarks() != null ? attendance.getRemarks() : "N/A") + "</span>");
        out.println("            </div>");
        out.println("        </div>");
        out.println("        <div style='text-align: center; margin-top: 20px;'>");
        out.println("            <a href='attendance.jsp' class='btn'>Mark Another</a>");
        out.println("            <a href='viewAttendance.jsp' class='btn' style='margin-left: 10px;'>View Records</a>");
        out.println("            <a href='index.html' class='btn' style='margin-left: 10px;'>Home</a>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    /**
     * Display error message
     */
    private void displayError(PrintWriter out, String errorMessage) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Error</title>");
        out.println("    <link rel='stylesheet' href='css/style.css'>");
        out.println("    <style>");
        out.println("        .error-container {");
        out.println("            max-width: 500px;");
        out.println("            margin: 100px auto;");
        out.println("            background: white;");
        out.println("            padding: 40px;");
        out.println("            border-radius: 10px;");
        out.println("            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);");
        out.println("            text-align: center;");
        out.println("        }");
        out.println("        .error-icon {");
        out.println("            font-size: 64px;");
        out.println("            color: #f44336;");
        out.println("            margin-bottom: 20px;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='error-container'>");
        out.println("        <div class='error-icon'>✕</div>");
        out.println("        <h2>Error</h2>");
        out.println("        <p style='color: #f44336; margin: 20px 0;'>" + errorMessage + "</p>");
        out.println("        <a href='attendance.jsp' class='btn'>Try Again</a>");
        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");
    }
}

