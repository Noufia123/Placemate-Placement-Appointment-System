<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Appointment</title>
<link rel="stylesheet" type="text/css" href="assets/css/style.css">
<script src="assets/js/script.js"></script>
</head>
<body>
<h2>Book Appointment</h2>
<form name="bookAppointmentForm" action="bookAppointment" method="post" onsubmit="return confirmSubmit('bookAppointmentForm')">

    <!-- Student Name (from DB) -->
    Student:
    <select name="studentId" required>
        <option value="">Select Student</option>
        <%
            com.placemates.dao.StudentDAO sdao = new com.placemates.dao.StudentDAO();
            java.util.List<com.placemates.model.Student> students = sdao.getAllStudents();
            for(com.placemates.model.Student s : students){
        %>
        <option value="<%=s.getStudentId()%>"><%=s.getName()%></option>
        <% } %>
    </select><br>

    <!-- Company Name -->
    Company:
    <select name="companyId" required>
        <option value="">Select Company</option>
        <option value="1">TCS</option>
        <option value="2">IBM</option>
        <option value="3">Infosys</option>
        <option value="4">Wipro</option>
        <option value="5">Tech Mahindra</option>
        <option value="6">HCL</option>
    </select><br>

    <!-- Appointment Day -->
    Appointment Day:
    <select name="appointmentDay" required>
        <option value="">Select Day</option>
        <option value="Monday">Monday</option>
        <option value="Tuesday">Tuesday</option>
        <option value="Wednesday">Wednesday</option>
        <option value="Thursday">Thursday</option>
        <option value="Friday">Friday</option>
    </select><br>

    <!-- Time Slot -->
    Slot Time:
    <select name="slotTime" required>
        <option value="">Select Time Slot</option>
        <option value="10:00-11:00">10:00-11:00</option>
        <option value="11:00-12:00">11:00-12:00</option>
        <option value="13:00-14:00">1:00-2:00</option>
    </select><br>

    <button type="submit">Book Appointment</button>
</form>
<br>
<a href="index.jsp">Back to Home</a>
</body>
</html>
