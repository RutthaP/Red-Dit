package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.PostDao;
import com.login.dao.UserDao;
import com.login.model.Post;
import com.login.model.User;

@WebServlet("/getUser")
public class GetUserServlet extends HttpServlet {
	UserDao userDao = null;
	PostDao postDao = null;
	User user = null;
	List<Post> userPosts = null;
	
	/*
	 * Get session user
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		// Random user
		if(session.getAttribute("username") == null) {
			response.sendRedirect("home");
			return;
		}
		
		userDao = new UserDao("test", "test");
		postDao = new PostDao();
		
		// This session user
		String username = (String)session.getAttribute("username");
		user = userDao.getUser(username);
		userPosts = postDao.getUserPosts(user.getId());
		
		
		request.setAttribute("user", user);
		request.setAttribute("userPosts", userPosts);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("my_profile.jsp");
		requestDispatcher.forward(request, response);
	}
	
	
	
	/*
	 * Get different user
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		userDao = new UserDao("test", "test");
		postDao = new PostDao();
		
		if(request.getParameter("checkUser") == null) {
			response.sendRedirect("home");
			return;
		}
		String username = request.getParameter("checkUser").toString();
		
		user = userDao.getUser(username);
		userPosts = postDao.getUserPosts(user.getId());
		
		request.setAttribute("user", user);
		request.setAttribute("userPosts", userPosts);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("user_profile.jsp");
		requestDispatcher.forward(request, response);
	}
}
