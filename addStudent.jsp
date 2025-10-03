<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Student</title>
<link rel="stylesheet" type="text/css" href="assets/css/style.css">
<script src="assets/js/script.js"></script>
</head>
<body>
<h2>Add Student</h2>
<form name="addStudentForm" action="addStudent" method="post" onsubmit="return confirmSubmit('addStudentForm')">
    Name: <input type="text" name="name" required><br>
    Phone: <input type="text" name="phone" required><br>
    <button type="submit">Add Student</button>
</form>
<br>
<a href="index.jsp">Back to Home</a>
</body>
</html>
