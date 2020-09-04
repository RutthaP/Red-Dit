<%@page import="com.login.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="css/profile.css">
	</head>
	<body>
		<%
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			if(session.getAttribute("username") == null){
				response.sendRedirect("home");
				return;
			}

		%>

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
			<ul>
				<li>Username: ${user.username}</li>
				<li>Creation date: ${user.createdAt}</li>
				<li>Name: ${user.name}</li>
				<li>About me: ${user.about}</li>
			</ul>
		</div>

		<div class="all-posts">
      <label>My posts</label>
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
	</body>
</html>
