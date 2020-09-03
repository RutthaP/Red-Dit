<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Red-It</title>
	<link rel="stylesheet" type="text/css"
		href="css/style.css">

</head>
<body>
	<header>
		<div class="main-header">
			<a href="#">
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

	<div class="clear-fix"></div>

	<form action="getUser" method="post">
		<input class="my-profile" type="submit" value="My profile">
	</form>

	<div class="clear-fix"></div>

	<section>
		<c:if test="${not empty username}">
			<div class="create-post">
				<form class="post-form" action="createPost" method="post">
					<input type="text" name="heading" placeholder="Subject">
					<textarea name="content" placeholder="Content..."></textarea>
					<input class="button" type="submit" value="Submit">
				</form>
			</div>
		</c:if>
		
		<c:if test="${empty username}">
			<p>Login to create post</p>
		</c:if>

		<c:if test="${not empty posts}">
			<c:forEach items="${posts}" var="p">
				<div class="post">
					<form action="getUser" method="get">
						By <input type="submit" value="${p.userID}" name="checkUser"> 
					</form>
					<h1>${p.heading}</h1>
					
			        <div class="content">
			        	<p>${p.content}</p>
			          	<p>Date: ${p.date}</p>
			        </div>
				</div>
			</c:forEach>

		</c:if>

		<div style="margin-top:100px;"></div>
	</section>


</body>
</html>
