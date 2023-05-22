<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Flights</title>
</head>
<body>
	<h2>Find Flights</h2>
	<form action="findFlights" method="post">
	from:<input type="text" name="from"><!-- from,to and depatureDate are name attribute which hold the value -->
	to:<input type="text" name="to">
	departure date:<input type="text" name="departureDate">
	<input type="submit" value="search"> <!-- as we click on search button it will call controller action=>findFlights  -->
	</form>
</body>
</html>