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
					<form action="getUser" method="post">
						<input type="submit" value="My profile">
					</form>
				</div>
			</c:if>

		</div>
	</header>

	<div class="clear-fix"></div>

	<section>
    <div class="post-heading">
			<h1>Welcome to the red-dit frontpage</h1>
		</div>

    <div class="create-post">
  		<c:if test="${not empty username}">
          <label>Create post</label>
  				<form class="post-form" action="createPost" method="post">
  					<input type="text" name="heading" placeholder="Subject">
  					<textarea name="content" placeholder="Content..."></textarea>
  					<input class="button" type="submit" value="Submit">
  				</form>
  		</c:if>

      <c:if test="${empty username}">
  			<p>Login to create post</p>
  		</c:if>
    </div>


    <div class="all-posts">
  		<c:if test="${not empty posts}">
  			<c:forEach items="${posts}" var="p">
  				<div class="post">
  					<form class="author" action="getUser" method="get">
  						<input type="submit" value="${p.username}" name="checkUser">
  					</form>
  					<h1>${p.heading}</h1>
  			        <div class="content">
  			        	<p>${p.content}</p>
  			        </div>
                <div class="content-date">
                  <p>${p.date}</p>
                </div>
  				</div>
  			</c:forEach>
  		</c:if>
    </div>

		<div style="margin-top:100px;"></div>
	</section>


</body>
</html>
