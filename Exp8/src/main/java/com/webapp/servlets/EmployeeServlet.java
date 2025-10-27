package com.webapp.servlets;

import com.webapp.dao.EmployeeDAO;
import com.webapp.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet for displaying employee records
 * Part B: Display Employee Records with JDBC and Servlet Integration
 */
@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDAO employeeDAO;
    
    @Override
    public void init() throws ServletException {
        employeeDAO = new EmployeeDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Get department filter if provided
        String department = request.getParameter("department");
        
        List<Employee> employees;
        if (department != null && !department.trim().isEmpty() && !department.equals("all")) {
            employees = employeeDAO.getEmployeesByDepartment(department);
        } else {
            employees = employeeDAO.getAllEmployees();
        }
        
        displayEmployeeRecords(out, employees, department);
        out.close();
    }
    
    /**
     * Display employee records in HTML table
     */
    private void displayEmployeeRecords(PrintWriter out, List<Employee> employees, String selectedDept) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Employee Records</title>");
        out.println("    <link rel='stylesheet' href='css/style.css'>");
        out.println("    <style>");
        out.println("        .employee-container {");
        out.println("            max-width: 1200px;");
        out.println("            margin: 30px auto;");
        out.println("            padding: 20px;");
        out.println("        }");
        out.println("        .header {");
        out.println("            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);");
        out.println("            color: white;");
        out.println("            padding: 30px;");
        out.println("            border-radius: 10px;");
        out.println("            margin-bottom: 30px;");
        out.println("            text-align: center;");
        out.println("        }");
        out.println("        .filter-section {");
        out.println("            background: white;");
        out.println("            padding: 20px;");
        out.println("            border-radius: 8px;");
        out.println("            box-shadow: 0 2px 4px rgba(0,0,0,0.1);");
        out.println("            margin-bottom: 20px;");
        out.println("        }");
        out.println("        table {");
        out.println("            width: 100%;");
        out.println("            background: white;");
        out.println("            border-collapse: collapse;");
        out.println("            border-radius: 8px;");
        out.println("            overflow: hidden;");
        out.println("            box-shadow: 0 2px 4px rgba(0,0,0,0.1);");
        out.println("        }");
        out.println("        th {");
        out.println("            background: #667eea;");
        out.println("            color: white;");
        out.println("            padding: 15px;");
        out.println("            text-align: left;");
        out.println("            font-weight: 600;");
        out.println("        }");
        out.println("        td {");
        out.println("            padding: 12px 15px;");
        out.println("            border-bottom: 1px solid #f0f0f0;");
        out.println("        }");
        out.println("        tr:hover {");
        out.println("            background: #f5f5f5;");
        out.println("        }");
        out.println("        .no-records {");
        out.println("            text-align: center;");
        out.println("            padding: 40px;");
        out.println("            color: #999;");
        out.println("            font-size: 18px;");
        out.println("        }");
        out.println("        .total-count {");
        out.println("            margin: 20px 0;");
        out.println("            font-size: 16px;");
        out.println("            color: #555;");
        out.println("        }");
        out.println("        select {");
        out.println("            padding: 10px;");
        out.println("            border: 2px solid #ddd;");
        out.println("            border-radius: 5px;");
        out.println("            font-size: 14px;");
        out.println("        }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='employee-container'>");
        out.println("        <div class='header'>");
        out.println("            <h1>Employee Records</h1>");
        out.println("            <p>Part B: Display Employee Records with JDBC and Servlet Integration</p>");
        out.println("        </div>");
        
        // Filter section
        out.println("        <div class='filter-section'>");
        out.println("            <form method='get' action='employees'>");
        out.println("                <label for='department'>Filter by Department: </label>");
        out.println("                <select name='department' id='department' onchange='this.form.submit()'>");
        out.println("                    <option value='all' " + (selectedDept == null || selectedDept.equals("all") ? "selected" : "") + ">All Departments</option>");
        out.println("                    <option value='IT' " + ("IT".equals(selectedDept) ? "selected" : "") + ">IT</option>");
        out.println("                    <option value='HR' " + ("HR".equals(selectedDept) ? "selected" : "") + ">HR</option>");
        out.println("                    <option value='Finance' " + ("Finance".equals(selectedDept) ? "selected" : "") + ">Finance</option>");
        out.println("                    <option value='Sales' " + ("Sales".equals(selectedDept) ? "selected" : "") + ">Sales</option>");
        out.println("                    <option value='Marketing' " + ("Marketing".equals(selectedDept) ? "selected" : "") + ">Marketing</option>");
        out.println("                </select>");
        out.println("            </form>");
        out.println("        </div>");
        
        if (employees.isEmpty()) {
            out.println("        <div class='no-records'>");
            out.println("            <p>No employee records found.</p>");
            out.println("        </div>");
        } else {
            out.println("        <div class='total-count'>");
            out.println("            <strong>Total Employees:</strong> " + employees.size());
            out.println("        </div>");
            out.println("        <table>");
            out.println("            <thead>");
            out.println("                <tr>");
            out.println("                    <th>ID</th>");
            out.println("                    <th>First Name</th>");
            out.println("                    <th>Last Name</th>");
            out.println("                    <th>Email</th>");
            out.println("                    <th>Department</th>");
            out.println("                    <th>Position</th>");
            out.println("                    <th>Salary</th>");
            out.println("                    <th>Hire Date</th>");
            out.println("                </tr>");
            out.println("            </thead>");
            out.println("            <tbody>");
            
            for (Employee emp : employees) {
                out.println("                <tr>");
                out.println("                    <td>" + emp.getEmployeeId() + "</td>");
                out.println("                    <td>" + emp.getFirstName() + "</td>");
                out.println("                    <td>" + emp.getLastName() + "</td>");
                out.println("                    <td>" + emp.getEmail() + "</td>");
                out.println("                    <td>" + emp.getDepartment() + "</td>");
                out.println("                    <td>" + emp.getPosition() + "</td>");
                out.println("                    <td>$" + String.format("%.2f", emp.getSalary()) + "</td>");
                out.println("                    <td>" + emp.getHireDate() + "</td>");
                out.println("                </tr>");
            }
            
            out.println("            </tbody>");
            out.println("        </table>");
        }
        
        out.println("        <div style='text-align: center; margin-top: 30px;'>");
        out.println("            <a href='index.html' class='btn'>Back to Home</a>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");
    }
}

