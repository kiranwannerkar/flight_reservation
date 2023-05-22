<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Registration</title>
</head>
<body>
	<h2>Create New Account</h2>
	<form action="saveRegistration" method="post">
	<pre> <!-- this tag use for every attrubute in new line -->
	first name<input type="text" name="firstName"><!-- this firstname ->name should match with entity class attributes -->
	last name<input type="text" name="lastName">
	email<input type="text" name="email">
	password<input type="text" name="password">
	<input type="submit" value="save">
	</pre>
	
	</form>
</body>
</html>