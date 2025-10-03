package com.placement.model;

public class Appointment {
    private int appointmentId;
    private int studentId;
    private int companyId;
    private String appointmentDay;
    private String slotTime;
    private String tokenNumber;
    private String status;
    private String rescheduledDay; // Not directly used in this flow, but kept for schema consistency
    private String rescheduledSlot; // Not directly used in this flow, but kept for schema consistency

    // Added fields for displaying in JSP (fetched via joins)
    private String studentName;
    private String studentPhone;
    private String companyName;

    public Appointment() {
    }

    // --- Existing Getters and Setters ---
    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public int getCompanyId() { return companyId; }
    public void setCompanyId(int companyId) { this.companyId = companyId; }
    public String getAppointmentDay() { return appointmentDay; }
    public void setAppointmentDay(String appointmentDay) { this.appointmentDay = appointmentDay; }
    public String getSlotTime() { return slotTime; }
    public void setSlotTime(String slotTime) { this.slotTime = slotTime; }
    public String getTokenNumber() { return tokenNumber; }
    public void setTokenNumber(String tokenNumber) { this.tokenNumber = tokenNumber; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getRescheduledDay() { return rescheduledDay; }
    public void setRescheduledDay(String rescheduledDay) { this.rescheduledDay = rescheduledDay; }
    public String getRescheduledSlot() { return rescheduledSlot; }
    public void setRescheduledSlot(String rescheduledSlot) { this.rescheduledSlot = rescheduledSlot; }

    // --- New Getters and Setters for joined data ---
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getStudentPhone() { return studentPhone; }
    public void setStudentPhone(String studentPhone) { this.studentPhone = studentPhone; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
}