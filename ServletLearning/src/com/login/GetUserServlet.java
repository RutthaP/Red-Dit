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
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		userDao = new UserDao();
		postDao = new PostDao();
		
		if(request.getParameter("user") == null) {
			response.sendRedirect("home");
			return;
		}
		String username = request.getParameter("user").toString();
		
		user = userDao.getUser(username);
		if(user == null) {
			out.println("Error getting user");
			return;
		}

		userPosts = postDao.getUserPosts(user.getId());
		if(userPosts == null) {
			out.println("Error getting user posts");
			return;
		}
		
		request.setAttribute("user", user);
		request.setAttribute("userPosts", userPosts);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("user_profile.jsp");
		requestDispatcher.forward(request, response);
	}
}
