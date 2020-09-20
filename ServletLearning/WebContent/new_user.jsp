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
			response.sendRedirect("home");
			return;
		}
	%>
	<div class="msg"></div>
	<form class="login" action="addUser" method="post">
		<input id="uname" type="text" placeholder="Enter username" name="uname">
		<input id="pass" type="password" placeholder="Enter password" name="pass">
		<button class="button" type="submit">Create new user!</button>
	</form>
	
	<script type="text/javascript" src="loginValidation.js" defer></script>
</body>
</html>