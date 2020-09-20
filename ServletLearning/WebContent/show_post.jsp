<%@page import="com.login.model.Post"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="css/show_post.css">
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
			<!-- Post class -->  			
			<div class="post">
				<form class="author" action="getUser" method="get">
					<input type="submit" value="${post.username}" name="user">
				</form>
				<h1>${post.heading}</h1>
		        <div class="content">
					<p>${post.content}</p>
				</div>
	            <div class="content-date">    	
	            	<c:if test="${not empty post.updateDate}">
      		    		<p>Updated: ${post.updateDate}</p>
	            	</c:if>
	            	<p>Created: ${post.date}</p>
	            </div>
	            
	            <!-- If logged in, include edit button on own posts -->
	            <c:if test="${not empty username}">
	            	<c:if test="${username eq post.username}">
	            		<form action="edit_post.jsp" method="post">
	            			<input type="hidden" value="${post.id}" name="postId">
	              	<input type="hidden" value="${post.heading}" name="postHeading">
	              	<input type="hidden" value="${post.content}" name="postContent">
	              	<input type="submit" value="edit">
	            		</form>
	            	</c:if>
	            	
	            	<!-- Like action for each post -->
	             <div class="post-actions">
	             	<form action="">
	             		<input type="submit" value="Like">
	             	</form>
	             </div>
	            </c:if>    
			</div> <!-- End Post class  -->
			
			
			<!-- Comments for the post -->  
            <div class="comment-container">
            	<!-- Adding comments -->
            	<div class="add-comment">
            		<c:if test="${empty username}">
            			<h1>Login to comment</h1>
	            	</c:if>
	            	
	            	<c:if test="${not empty username}">
	            		<form class="comment-form" action="addComment" method="post">
	            			<h1>Add comment</h1>
	            			<input type="hidden" value="${post.id}" name="postId">
	            			<input type="hidden" value="${post.username}" name="postUname">
		                	<input type="hidden" value="${post.heading}" name="postHeading">
		                	<input type="hidden" value="${post.content}" name="postContent">
	            			<textarea id="comment" name="comment"></textarea>
	            			<input type="submit" value="add comment">
	            		</form>
	            	</c:if>
	            	<div class="msg"></div>
            	</div> <!-- Add comment end -->
            	
            	
            	<c:forEach items="${post.comments}" var="c">
            		<div class="comment">
            			<p>${c.content}</p>
            			<p>${c.userId}</p>
            		</div>
            	</c:forEach>	
            </div> <!-- End comments -->
		</section>
		<script type="text/javascript" src="loginValidation.js" defer></script>
		<script type="text/javascript" src="commentValidation.js" defer></script>
	</body>
</html>