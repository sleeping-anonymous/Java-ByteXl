package com.webapp.servlets;

import com.webapp.dao.UserDAO;
import com.webapp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet for handling user login
 * Part A: User Login Using Servlet and HTML Form
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    
    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Redirect to login page
        response.sendRedirect("login.html");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Get form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Validate input
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            displayError(out, "Username and password are required!");
            return;
        }
        
        // Validate user credentials
        User user = userDAO.validateUser(username.trim(), password.trim());
        
        if (user != null) {
            // Create session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            session.setMaxInactiveInterval(30 * 60); // 30 minutes
            
            // Display success page
            displaySuccess(out, user);
        } else {
            displayError(out, "Invalid username or password!");
        }
        
        out.close();
    }
    
    /**
     * Display success page after login
     */
    private void displaySuccess(PrintWriter out, User user) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Login Success</title>");
        out.println("    <link rel='stylesheet' href='css/style.css'>");
        out.println("    <style>");
        out.println("        .success-container {");
        out.println("            max-width: 600px;");
        out.println("            margin: 50px auto;");
        out.println("            background: white;");
        out.println("            padding: 40px;");
        out.println("            border-radius: 10px;");
        out.println("            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);");
        out.println("            text-align: center;");
        out.println("        }");
        out.println("        .success-icon {");
        out.println("            font-size: 64px;");
        out.println("            color: #4CAF50;");
        out.println("            margin-bottom: 20px;");
        out.println("        }");
        out.println("        .user-info {");
        out.println("            background: #f5f5f5;");
        out.println("            padding: 20px;");
        out.println("            border-radius: 5px;");
        out.println("            margin: 20px 0;");
        out.println("        }");
        out.println("        .nav-links {");
        out.println("            margin-top: 30px;");
        out.println("        }");
        out.println("        .nav-links a {");
        out.println("            display: inline-block;");
        out.println("            margin: 10px;");
        out.println("            padding: 12px 30px;");
        out.println("            background: #2196F3;");
        out.println("            color: white;");
        out.println("            text-decoration: none;");
        out.println("            border-radius: 5px;");
        out.println("            transition: background 0.3s;");
        out.println("        }");
        out.println("        .nav-links a:hover {");
        out.println("            background: #1976D2;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='success-container'>");
        out.println("        <div class='success-icon'>✓</div>");
        out.println("        <h1>Login Successful!</h1>");
        out.println("        <div class='user-info'>");
        out.println("            <h3>Welcome, " + user.getFullName() + "!</h3>");
        out.println("            <p><strong>Username:</strong> " + user.getUsername() + "</p>");
        out.println("            <p><strong>Email:</strong> " + user.getEmail() + "</p>");
        out.println("        </div>");
        out.println("        <div class='nav-links'>");
        out.println("            <a href='index.html'>Home</a>");
        out.println("            <a href='employee.html'>View Employees</a>");
        out.println("            <a href='attendance.jsp'>Attendance Portal</a>");
        out.println("            <a href='logout'>Logout</a>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    /**
     * Display error page
     */
    private void displayError(PrintWriter out, String errorMessage) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Login Error</title>");
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
        out.println("        .error-message {");
        out.println("            color: #f44336;");
        out.println("            font-size: 18px;");
        out.println("            margin: 20px 0;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='error-container'>");
        out.println("        <div class='error-icon'>✕</div>");
        out.println("        <h2>Login Failed</h2>");
        out.println("        <p class='error-message'>" + errorMessage + "</p>");
        out.println("        <a href='login.html' class='btn'>Try Again</a>");
        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");
    }
}

