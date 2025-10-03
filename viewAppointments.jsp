<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.placemates.dao.AppointmentDAO, com.placemates.dao.StudentDAO, com.placemates.dao.CompanyDAO" %>
<%@ page import="com.placemates.model.Appointment, com.placemates.model.Student, com.placemates.model.Company" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Appointments</title>
<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
<h2>All Appointments</h2>

<%
    AppointmentDAO appointmentDAO = new AppointmentDAO();
    List<Appointment> appointments = appointmentDAO.getAllAppointments();
    
    StudentDAO studentDAO = new StudentDAO();
    CompanyDAO companyDAO = new CompanyDAO();
%>

<table>
    <tr>
        <th>ID</th>
        <th>Student Name</th>
        <th>Company Name</th>
        <th>Appointment Day</th>
        <th>Time Slot</th>
        <th>Token Number</th>
    </tr>
<%
    for(Appointment a : appointments) {
        Student s = studentDAO.getStudentById(a.getStudentId());
        Company c = companyDAO.getCompanyById(a.getCompanyId());
%>
    <tr>
        <td><%=a.getAppointmentId()%></td>
        <td><%=s != null ? s.getName() : "N/A" %></td>
        <td><%=c != null ? c.getCompanyName() : "N/A" %></td>
        <td><%=a.getAppointmentDay()%></td>
        <td><%=a.getSlotTime()%></td>
        <td><%=a.getTokenNumber()%></td>
    </tr>
<% } %>
</table>

<br>
<a href="index.jsp">Back to Home</a>
</body>
</html>
