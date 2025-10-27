package com.webapp.dao;

import com.webapp.model.Attendance;
import com.webapp.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Attendance operations
 * Handles database operations for student attendance
 */
public class AttendanceDAO {
    
    /**
     * Mark attendance for a student
     * @param attendance Attendance object
     * @return true if successful, false otherwise
     */
    public boolean markAttendance(Attendance attendance) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO attendance (student_id, student_name, date, status, subject, remarks) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, attendance.getStudentId());
            pstmt.setString(2, attendance.getStudentName());
            pstmt.setString(3, attendance.getDate());
            pstmt.setString(4, attendance.getStatus());
            pstmt.setString(5, attendance.getSubject());
            pstmt.setString(6, attendance.getRemarks());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error marking attendance: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Get all attendance records
     * @return List of all attendance records
     */
    public List<Attendance> getAllAttendance() {
        List<Attendance> attendanceList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM attendance ORDER BY date DESC, student_id";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Attendance attendance = new Attendance(
                    rs.getInt("attendance_id"),
                    rs.getInt("student_id"),
                    rs.getString("student_name"),
                    rs.getString("date"),
                    rs.getString("status"),
                    rs.getString("subject"),
                    rs.getString("remarks")
                );
                attendanceList.add(attendance);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching attendance: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return attendanceList;
    }
    
    /**
     * Get attendance by student ID
     * @param studentId Student ID
     * @return List of attendance records for the student
     */
    public List<Attendance> getAttendanceByStudentId(int studentId) {
        List<Attendance> attendanceList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM attendance WHERE student_id = ? ORDER BY date DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentId);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Attendance attendance = new Attendance(
                    rs.getInt("attendance_id"),
                    rs.getInt("student_id"),
                    rs.getString("student_name"),
                    rs.getString("date"),
                    rs.getString("status"),
                    rs.getString("subject"),
                    rs.getString("remarks")
                );
                attendanceList.add(attendance);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching attendance: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return attendanceList;
    }
    
    /**
     * Get attendance by date
     * @param date Date in YYYY-MM-DD format
     * @return List of attendance records for the date
     */
    public List<Attendance> getAttendanceByDate(String date) {
        List<Attendance> attendanceList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM attendance WHERE date = ? ORDER BY student_id";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, date);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Attendance attendance = new Attendance(
                    rs.getInt("attendance_id"),
                    rs.getInt("student_id"),
                    rs.getString("student_name"),
                    rs.getString("date"),
                    rs.getString("status"),
                    rs.getString("subject"),
                    rs.getString("remarks")
                );
                attendanceList.add(attendance);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching attendance: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return attendanceList;
    }
}

