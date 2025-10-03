<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Appointment</title>
<link rel="stylesheet" type="text/css" href="assets/css/style.css">
<script src="assets/js/script.js"></script>
</head>
<body>
<h2>Update Appointment</h2>
<form name="updateAppointmentForm" action="updateAppointment" method="post" onsubmit="return confirmSubmit('updateAppointmentForm')">
    
    <!-- Appointment ID -->
    Appointment ID: <input type="number" name="appointmentId" placeholder="Enter Appointment ID" required><br>

    <!-- New Appointment Day -->
    New Appointment Day:
    <select name="appointmentDay" required>
        <option value="">Select Day</option>
        <option value="Monday">Monday</option>
        <option value="Tuesday">Tuesday</option>
        <option value="Wednesday">Wednesday</option>
        <option value="Thursday">Thursday</option>
        <option value="Friday">Friday</option>
    </select><br>

    <!-- New Time Slot -->
    New Slot Time:
    <select name="slotTime" required>
        <option value="">Select Time Slot</option>
        <option value="10:00-11:00">10:00-11:00</option>
        <option value="11:00-12:00">11:00-12:00</option>
        <option value="13:00-14:00">1:00-2:00</option>
    </select><br>

    <button type="submit">Update Appointment</button>
</form>
<br>
<a href="index.jsp">Back to Home</a>
</body>
</html>
