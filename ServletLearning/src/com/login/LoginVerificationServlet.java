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
import javax.xml.ws.Response;

import com.login.dao.UserDao;

@WebServlet("/login")
public class LoginVerificationServlet extends HttpServlet {
	UserDao userDao = new UserDao("test", "test");
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String uname = request.getParameter("uname");
		String psw = request.getParameter("pass");
		
		if(userDao.verifyUser(uname, psw)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", uname);
			response.sendRedirect("index.html");
		}else {
			
			response.sendRedirect("index.html");
		}
	}
}
