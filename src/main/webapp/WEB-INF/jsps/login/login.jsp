<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
    ${error}
	<h2>Login Here</h2>
	<form action="verifyLogin" method="post">
		emailId <input type="text" name="emailId">
		password <input type="password" name="password">
		<input type="submit" value="login">
	</form>
</body>
</html>