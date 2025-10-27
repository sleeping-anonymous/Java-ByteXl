package com.webapp.model;

/**
 * Attendance Model Class
 * Represents student attendance record
 */
public class Attendance {
    private int attendanceId;
    private int studentId;
    private String studentName;
    private String date;
    private String status; // Present, Absent, Late
    private String subject;
    private String remarks;
    
    // Constructors
    public Attendance() {
    }
    
    public Attendance(int studentId, String studentName, String date, 
                     String status, String subject, String remarks) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.date = date;
        this.status = status;
        this.subject = subject;
        this.remarks = remarks;
    }
    
    public Attendance(int attendanceId, int studentId, String studentName, 
                     String date, String status, String subject, String remarks) {
        this.attendanceId = attendanceId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.date = date;
        this.status = status;
        this.subject = subject;
        this.remarks = remarks;
    }
    
    // Getters and Setters
    public int getAttendanceId() {
        return attendanceId;
    }
    
    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }
    
    public int getStudentId() {
        return studentId;
    }
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId=" + attendanceId +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", subject='" + subject + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}

