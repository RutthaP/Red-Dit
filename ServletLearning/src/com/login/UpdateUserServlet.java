package com.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.UserDao;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {
	UserDao userDao = null;
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		if(session.getAttribute("username") == null) {
			response.sendRedirect("home");
			return;
		}
		
		String username = (String)session.getAttribute("username");
		String about = request.getParameter("about");
		
		userDao = new UserDao();
		if(userDao.updateUser(username, about)) {
			response.sendRedirect("getUser?checkUser="+username);
		}else {	
			out.println("Update error\nPlease try again...");
		}
		
	}
}
