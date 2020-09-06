<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>User Profile</title>
		<link rel="stylesheet" type="text/css" href="css/profile.css">
	</head>
	
	<body>
		<header>
			<div class="main-header">
				<a href="home">
					<img src="Images/redditLogo.png" alt="Logo">
				</a>
	
				<c:if test="${empty username}">
					<div class="login-sidebar">
						<form class="login" action="login" method="post">
							<input type="text" placeholder="Enter Username" name="uname">
							<input type="password" placeholder="Enter Password" name="pass">
							<input class="button" type="submit" value="Login">
						</form>
	
						<form class="new-user" action="new_user.jsp" method="post">
							<button type="submit">New user?</button>
						</form>
					</div>
				</c:if>
	
				<c:if test="${not empty username}">
					<div class="login-sidebar">
						<H1>Welcome ${username}!</H1>
						<form action="logout" method="post">
							<input type="submit" value="Logout">
						</form>
					</div>
				</c:if>
	
			</div>
		</header>
	
		<div class="header-divider"></div>
		
		<div class="user-info">
			<table>
				<tr>
					<th>Username:</th>
					<td>${user.username}</td>
				</tr>
				<tr>
					<th>Name:</th>
					<td>${user.name}</td>
				</tr>
			</table>
			
			<h1>About me:</h1>
			<c:if test="${empty username}">
				<p>${user.about}</p>
			</c:if>
			
			<c:if test="${not empty username}">
				<c:if test="${username eq user.username}">
					<form action="updateUser" method="post">
						<textarea name="about">${user.about}</textarea>
						<input type="submit" value="Confirm edit">
					</form>
				</c:if>
				
				<c:if test="${username ne user.username}">
					<p>${user.about}</p>
				</c:if>
			</c:if>
		</div>
		
		<content>
			<div class="all-posts">
				<h1>${user.username}'s posts</h1>
				<c:forEach items="${userPosts}" var="post">
					<div class="post">
						<h1>${post.heading}</h1>
						<div class="content">
							<p>${post.content}</p>
						</div>
						<div class="content-date">
							<p>${post.date}</p>
							<p>${post.updateDate}</p>
						</div>
						
					</div>
				</c:forEach>
				
			</div>
		</content>
	
	</body>
</html>