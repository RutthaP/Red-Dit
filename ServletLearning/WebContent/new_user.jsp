<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		if(session.getAttribute("username") != null){
			out.println("You\'re already logged in");
			response.sendRedirect("index.html");
			return;
		}
	%>
	<form action="addUser" method="post">
		<input type="text" placeholder="Enter username" name="uname">
		<input type="password" placeholder="Enter password" name="pass">
		<button type="submit">Create new user!</button>
	</form>
</body>
</html>