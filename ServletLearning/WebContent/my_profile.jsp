<%@page import="com.login.model.User"%>
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
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		if(session.getAttribute("username") == null){
			response.sendRedirect("index.html");
			return;
		}
		
	%>
	
	<div class="UserInfo">
		<ul>
			<li>Username: ${user.username}</li>
			<li>Creation date: ${user.createdAt}</li>
			<li>Name: ${user.name}</li>
			<li>About me: ${user.about}</li>
		</ul>
	</div>
	
	
	
	<form action="logout" method="post">
	<button type="submit">Logout</button>
	</form>
</body>
</html>