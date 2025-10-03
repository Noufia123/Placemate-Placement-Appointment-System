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
import java.util.List;

@WebServlet("/viewAppointments")
public class ViewAppointmentsServlet extends HttpServlet {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "edit":
                showEditForm(request, response);
                break;
            case "list":
            default:
                listAppointments(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("update".equals(action)) {
            updateAppointment(request, response);
        } else if ("reschedule".equals(action)) {
            rescheduleAppointment(request, response);
        } else {
            response.sendRedirect("error.jsp?message=Invalid action");
        }
    }
    
    private void listAppointments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Appointment> appointments = appointmentDAO.getAllAppointments();
        request.setAttribute("appointments", appointments);
        request.getRequestDispatcher("/viewAppointments.jsp").forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Appointment existingAppointment = appointmentDAO.getAppointmentById(id);
            
            // Critical NullPointerException fix: Check if the appointment exists
            if (existingAppointment == null) {
                response.sendRedirect("viewAppointments?error=Appointment not found.");
                return; 
            }
            
            Student existingStudent = studentDAO.getStudentById(existingAppointment.getStudentId());
            
            request.setAttribute("appointment", existingAppointment);
            request.setAttribute("student", existingStudent);
            request.setAttribute("companies", companyDAO.getAllCompanies());
            
            request.getRequestDispatcher("/editAppointment.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("viewAppointments?error=Invalid appointment ID.");
        }
    }

    private void updateAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int companyId = Integer.parseInt(request.getParameter("companyId"));
        String studentName = request.getParameter("studentName");
        String studentPhone = request.getParameter("studentPhone");
        String day = request.getParameter("appointmentDay");
        String slot = request.getParameter("slotTime");

        Student student = new Student();
        student.setStudentId(studentId);
        student.setName(studentName);
        student.setPhone(studentPhone);
        boolean studentUpdated = studentDAO.updateStudent(student);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(appointmentId);
        appointment.setCompanyId(companyId);
        appointment.setAppointmentDay(day);
        appointment.setSlotTime(slot);
        
        boolean appointmentUpdated = appointmentDAO.updateAppointment(appointment);

        if (studentUpdated && appointmentUpdated) {
            response.sendRedirect("viewAppointments?message=Appointment updated successfully!");
        } else {
            response.sendRedirect("viewAppointments?error=Failed to update appointment.");
        }
    }
    
    private void rescheduleAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        String newDay = request.getParameter("newDay");
        String newSlot = request.getParameter("newSlot");

        boolean success = appointmentDAO.rescheduleAppointment(appointmentId, newDay, newSlot);

        if (success) {
            response.sendRedirect("viewAppointments?message=Appointment rescheduled successfully!");
        } else {
            response.sendRedirect("viewAppointments?error=Failed to reschedule appointment.");
        }
    }
}