package com.login;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.PostDao;
import com.login.dao.UserDao;
import com.login.model.User;

@WebServlet("/createPost")
public class CreatePostServlet extends HttpServlet {
	UserDao userDao = new UserDao("test", "test");
	PostDao postDao = new PostDao();
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("username") == null) {
			response.sendRedirect("home");
			return;
		}
		
		String heading = request.getParameter("heading");
		String content = request.getParameter("content");
		
		if(heading.equals("")) {
			response.sendRedirect("home");
			return;
		}
		
		String username = session.getAttribute("username").toString();
		User user = userDao.getUser(username);
		int userID = user.getId();
		
		postDao.addPost(userID, username, heading, content);
		response.sendRedirect("home");
	}
}
