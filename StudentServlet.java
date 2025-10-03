package com.placement.servlet;

import com.placement.dao.StudentDAO;
import com.placement.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        
        Student student = new Student();
        student.setName(name);
        student.setPhone(phone);
        
        int studentId = studentDAO.addStudent(student);
        
        // Redirect to a confirmation page or back to the appointment booking form
        if (studentId > 0) {
            request.setAttribute("message", "Student added successfully with ID: " + studentId);
            request.getRequestDispatcher("/addAppointment.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Failed to add student.");
            request.getRequestDispatcher("/addStudent.jsp").forward(request, response);
        }
    }
}