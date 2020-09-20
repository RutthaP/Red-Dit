package com.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.PostDao;
import com.login.dao.UserDao;
import com.login.model.User;

/* Servlet for creating and adding a post to the database.
 */
@WebServlet("/createPost")
public class CreatePostServlet extends HttpServlet {
	UserDao userDao = new UserDao();
	PostDao postDao = new PostDao();
	
	/* 
	 * A post needs a valid heading, checks that with string.trim().
	 * Then fetches the user id from the database, then adds the post 
	 * using post DAO.
	 * 
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		if(session.getAttribute("username") == null) {
			response.sendRedirect("home");
			return;
		}
		
		String heading = request.getParameter("heading");
		String content = request.getParameter("content");
		
		String username = session.getAttribute("username").toString();
		User user = userDao.getUser(username);
		if(user == null) {
			out.println("Error validating user");
			return;
		}
		
		int userID = user.getId();
		
		if(postDao.addPost(userID, username, heading, content) == true){
			response.sendRedirect("home");
		}else {
			out.println("Error creating post\nPlease try again...");
		}
		
	}
	
}
