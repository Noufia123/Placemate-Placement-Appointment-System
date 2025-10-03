package com.placement.dao;

import com.placement.model.Appointment;
import com.placement.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    
    public int addAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointments (student_id, company_id, appointment_day, slot_time, token_number, status) VALUES (?, ?, ?, ?, ?, ?)";
        int appointmentId = -1;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, appointment.getStudentId());
            pstmt.setInt(2, appointment.getCompanyId());
            pstmt.setString(3, appointment.getAppointmentDay());
            pstmt.setString(4, appointment.getSlotTime());
            
            String token = generateToken();
            pstmt.setString(5, token); 
            appointment.setTokenNumber(token); 
            
            pstmt.setString(6, "BOOKED");
            
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                appointmentId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentId;
    }
    
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT a.*, s.name as student_name, s.phone as student_phone, c.company_name " +
                     "FROM appointments a " +
                     "JOIN students s ON a.student_id = s.student_id " +
                     "JOIN companies c ON a.company_id = c.company_id " +
                     "ORDER BY a.appointment_day, a.slot_time";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(rs.getInt("appointment_id"));
                appointment.setStudentId(rs.getInt("student_id"));
                appointment.setCompanyId(rs.getInt("company_id"));
                appointment.setAppointmentDay(rs.getString("appointment_day"));
                appointment.setSlotTime(rs.getString("slot_time"));
                appointment.setTokenNumber(rs.getString("token_number"));
                appointment.setStatus(rs.getString("status"));
                appointment.setStudentName(rs.getString("student_name"));
                appointment.setStudentPhone(rs.getString("student_phone"));
                appointment.setCompanyName(rs.getString("company_name"));
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    public Appointment getAppointmentById(int appointmentId) {
        String sql = "SELECT a.*, s.name as student_name, s.phone as student_phone, c.company_name " +
                     "FROM appointments a " +
                     "JOIN students s ON a.student_id = s.student_id " +
                     "JOIN companies c ON a.company_id = c.company_id " +
                     "WHERE a.appointment_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, appointmentId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(rs.getInt("appointment_id"));
                appointment.setStudentId(rs.getInt("student_id"));
                appointment.setCompanyId(rs.getInt("company_id"));
                appointment.setAppointmentDay(rs.getString("appointment_day"));
                appointment.setSlotTime(rs.getString("slot_time"));
                appointment.setTokenNumber(rs.getString("token_number"));
                appointment.setStatus(rs.getString("status"));
                appointment.setStudentName(rs.getString("student_name"));
                appointment.setStudentPhone(rs.getString("student_phone"));
                appointment.setCompanyName(rs.getString("company_name"));
                return appointment;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateAppointment(Appointment appointment) {
        String sql = "UPDATE appointments SET company_id = ?, appointment_day = ?, slot_time = ?, status = 'RESCHEDULED' WHERE appointment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, appointment.getCompanyId());
            pstmt.setString(2, appointment.getAppointmentDay());
            pstmt.setString(3, appointment.getSlotTime());
            pstmt.setInt(4, appointment.getAppointmentId());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean rescheduleAppointment(int appointmentId, String newDay, String newSlot) {
        String sql = "UPDATE appointments SET appointment_day = ?, slot_time = ?, status = 'RESCHEDULED' WHERE appointment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, newDay);
            pstmt.setString(2, newSlot);
            pstmt.setInt(3, appointmentId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String generateToken() {
        DecimalFormat df = new DecimalFormat("0000"); 
        long uniqueNum = Instant.now().toEpochMilli() % 1000000;
        return "PLT" + df.format(uniqueNum);
    }
}