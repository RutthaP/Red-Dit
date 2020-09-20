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
			<!-- Login sidebar/welcome message -->
			<div class="login-sidebar">
				<c:if test="${empty username}">
						<div class="msg"></div>
						<form class="login" action="login" method="post">
							<input id="uname" type="text" placeholder="Enter Username" name="uname">
							<input id="pass" type="password" placeholder="Enter Password" name="pass">
							<input class="button" type="submit" value="Login">
						</form>
	
						<form class="new-user" action="new_user.jsp" method="post">
							<button type="submit">New user?</button>
						</form>
					
				</c:if>
	
				<c:if test="${not empty username}">
						<H1>Welcome ${username}!</H1>
						<form action="logout" method="post">
							<input type="submit" value="Logout">
						</form>
						<form action="getUser" method="get">
							<input type="hidden" value="${username}" name="user">
							<input type="submit" value="My profile">
						</form>
				</c:if>
			</div> <!-- Login sidebar end -->
		</div> <!-- Main header end -->
	</header>

	<div class="clear-fix"></div>
	
	<section>
	    <div class="post-heading">
			<h1>Welcome to the red-dit frontpage</h1>
		</div>
	
	    <div class="create-post">
	    
	    	<div class="msg"></div>
	    	
	  		<c:if test="${not empty username}">
	          	<label>Create post</label>
 				<form class="post-form" action="createPost" method="post">
 					<input id="heading" type="text" name="heading" placeholder="Subject">
 					<textarea id="content" name="content" placeholder="Content..."></textarea>
 					<input class="button" type="submit" value="Submit">
 				</form>
	  		</c:if>
	
	    	<c:if test="${empty username}">
  				<p>Login to create post</p>
	  		</c:if>
	    </div>
	
	
	    <div class="all-posts">
	  		
	  		<c:forEach items="${posts}" var="p"> 				
	  	
				<!-- Post class -->  			
  				<div class="post">
  					<form class="author" action="getUser" method="get">
  						<input type="submit" value="${p.username}" name="user">
  					</form>
  					
  					<h1>${p.heading}</h1>
  			        <div class="content" onClick="location.href='showPost?postId=${p.id}&uname=${p.username}'">
  						<p>${p.content}</p>
  					</div>
  					
	                <div class="content-date">
		                <c:if test="${not empty p.updateDate}">
		                	<p>Updated: ${p.updateDate}</p>
		                </c:if>
		                <p>Created: ${p.date}</p>
	                </div>
	                
	                <!-- If logged in, include edit button on own posts -->
	                <c:if test="${not empty username}">
	                	<c:if test="${username eq p.username}">
	                		<form action="edit_post.jsp" method="post">
	                			<input type="hidden" value="${p.id}" name="postId">
			                	<input type="hidden" value="${p.heading}" name="postHeading">
			                	<input type="hidden" value="${p.content}" name="postContent">
			                	<input type="submit" value="edit">
	                		</form>
	                	</c:if>
	                </c:if>    
	                
	                <!-- Like / comment action for each post -->
	                <div class="post-actions">
	                	<form action="">
	                		<input type="submit" value="Like">
	                	</form>
	                	<form action="showPost" method="get">
	                		<input type="hidden" name="postId" value="${p.id}">
	                		<input type="hidden" name="uname" value="${p.username}">
	                		<input type="submit" value="Show comments">
	                	</form>
	                </div>
	                
  				</div> <!-- End Post class  -->
	  				
	  		</c:forEach>
	  			
	    </div> <!-- End all-posts -->

		<div style="margin-top:100px;"></div>
	</section>

	<script type="text/javascript" src="loginValidation.js" defer></script>
	<script type="text/javascript" src="postValidation.js" defer></script>
</body>
</html>
