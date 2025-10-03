<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Add Company</title>
<link rel="stylesheet" type="text/css" href="assets/css/style.css">
<script src="assets/js/script.js"></script>
</head>
<body>
<h2>Add Company</h2>
<form name="addCompanyForm" action="addCompany" method="post" onsubmit="return confirmSubmit('addCompanyForm')">
    Company Name: <input type="text" name="companyName" required><br>
    <button type="submit">Add Company</button>
</form>
<br>
<a href="index.jsp">Back to Home</a>
</body>
</html>
