package com.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.UserDao;
import com.login.model.User;

@WebServlet("/getUser")
public class GetUserServlet extends HttpServlet {
	UserDao userDao = new UserDao("test", "test");
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		if(session.getAttribute("username") == null) {
			response.sendRedirect("index.html");
			return;
		}
		
		User user = userDao.getUser((String)session.getAttribute("username"));
		request.setAttribute("user", user);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("my_profile.jsp");
		requestDispatcher.forward(request, response);
	}
}
