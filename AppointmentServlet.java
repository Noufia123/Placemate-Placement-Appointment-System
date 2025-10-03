package com.placement.servlet;

import com.placement.dao.AppointmentDAO;
import com.placement.dao.CompanyDAO;
import com.placement.dao.StudentDAO;
import com.placement.model.Appointment;
import com.placement.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/appointment")
public class AppointmentServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AppointmentDAO appointmentDAO;
    private StudentDAO studentDAO;
    private CompanyDAO companyDAO;

    public void init() {
        appointmentDAO = new AppointmentDAO();
        studentDAO = new StudentDAO();
        companyDAO = new CompanyDAO();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("book".equals(action)) {
            bookAppointment(request, response);
        } else {
            response.sendRedirect("error.jsp?message=Invalid action");
        }
    }

    private void bookAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentName = request.getParameter("studentName");
        String studentPhone = request.getParameter("studentPhone");
        int companyId = Integer.parseInt(request.getParameter("companyId"));
        String day = request.getParameter("appointmentDay");
        String slot = request.getParameter("slotTime");

        Student student = new Student();
        student.setName(studentName);
        student.setPhone(studentPhone);
        int studentId = studentDAO.addStudent(student);
        
        if (studentId > 0) {
            Appointment appointment = new Appointment();
            appointment.setStudentId(studentId);
            appointment.setCompanyId(companyId);
            appointment.setAppointmentDay(day);
            appointment.setSlotTime(slot);
            
            int appointmentId = appointmentDAO.addAppointment(appointment);
            
            if (appointmentId > 0) {
                appointment.setStudentName(studentName);
                appointment.setStudentPhone(studentPhone);
                appointment.setCompanyName(companyDAO.getCompanyById(companyId).getCompanyName());
                
                request.getSession().setAttribute("bookedAppointment", appointment);
                
                // Changed redirect to success.jsp
                response.sendRedirect("success.jsp?message=Appointment booked successfully!");
            } else {
                response.sendRedirect("addAppointment.jsp?error=Failed to book appointment.");
            }
        } else {
            response.sendRedirect("addAppointment.jsp?error=Failed to add student.");
        }
    }
}