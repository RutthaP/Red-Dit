<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>User Profile</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	
	<body>
		<div class="user-info">
			<ul>
				<li>Username: ${user.username}</li>
				<li>Name: ${user.name}</li>
				<li>About me: ${user.about}</li>
			</ul>
		</div>
		
		<content>
			<div class="user-posts">
			
				<c:forEach items="${userPosts}" var="post">
					<div class="post">
						<h1>${post.heading}</h1>
						<div class="content">
							<p>${post.content}</p>
							<p>${post.date}</p>
							<p>${post.updateDate}</p>
						</div>
					</div>
				</c:forEach>
				
			</div>
		</content>
	
	</body>
</html>