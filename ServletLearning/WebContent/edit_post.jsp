<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		<%
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			if(session.getAttribute("username") == null){
				response.sendRedirect("home");
				return;
			}
			
		%>
		
		<div class="create-post">
			<form class="post-form" action="editPost" method="post">
				<input type="hidden" name="postId" value="${param['postId']}">
				<input type="text" name="heading" value="${param['postHeading']}">
				<textarea name="content" placeholder="Content...">${param["postContent"]}</textarea>
				<input class="button" type="submit" value="Submit edit">
			</form>
		</div>
	</body>
</html>