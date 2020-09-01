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
	
		User user = (User)request.getAttribute("user");
		out.println(user.getUsername());
		out.println(user.getCreatedAt());
		
		if(user.getName() != null)
			out.println(user.getName());
		if(user.getAbout() != null)
			out.println(user.getAbout());
		
	%>
	
	<form action="logout" method="post">
	<button type="submit">Logout</button>
	</form>
</body>
</html>