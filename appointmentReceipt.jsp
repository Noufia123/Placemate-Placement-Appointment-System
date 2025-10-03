<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.placemates.model.Appointment"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Appointment Receipt</title>
<link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
<h2>Appointment Confirmation</h2>

<%
    Appointment a = (Appointment) request.getAttribute("appointment");
    if(a != null){
%>
<table>
    <tr>
        <th>Student ID</th>
        <td><%=a.getStudentId()%></td>
    </tr>
    <tr>
        <th>Company ID</th>
        <td><%=a.getCompanyId()%></td>
    </tr>
    <tr>
        <th>Appointment Day</th>
        <td><%=a.getAppointmentDay()%></td>
    </tr>
    <tr>
        <th>Time Slot</th>
        <td><%=a.getSlotTime()%></td>
    </tr>
    <tr>
        <th>Token Number</th>
        <td><%=a.getTokenNumber()%></td>
    </tr>
</table>
<% } else { %>
<p>No appointment details available.</p>
<% } %>

<br>
<a href="index.jsp">Back to Home</a>
</body>
</html>
