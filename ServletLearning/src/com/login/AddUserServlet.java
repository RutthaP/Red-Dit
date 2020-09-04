package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.UserDao;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
	UserDao userDao = new UserDao("test", "test");
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		PrintWriter out = response.getWriter();
		
		Pattern p = Pattern.compile("[\\w\\W0-9]");
		Matcher m = p.matcher(uname);
		if(m.find() == false) {
			out.println("Error andding user " + uname);
			return;	
		}
		
		p = Pattern.compile("[\\w\\W0-9]");
		m = p.matcher(pass);
		if(m.find() == false) {
			out.println("Error andding user " + pass);
			return;
		}
		
		
		if(userDao.addUser(uname, pass) == true) {
			HttpSession session = request.getSession();
			session.setAttribute("username", uname);
			response.sendRedirect("home");
		}
		else {
			out.println("Error andding user");
		}
	}
}
