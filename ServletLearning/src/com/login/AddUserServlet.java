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
	UserDao userDao = new UserDao();
	
	/* Checks if a valid uname/pass was given with string.trim().
	 * If a user was successfully added, redirect to home, else 
	 * print error message.
	 * 
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		PrintWriter out = response.getWriter();
		
		if(uname.trim().length() <= 0) {
			out.println("Provide valid username\nPlease try again...");
			return;	
		}
		
		if(pass.trim().length() <= 0) {
			out.println("Provide valid password\nPlease try again...");
			return;	
		}
		
		if(userDao.addUser(uname, pass) == true) {
			HttpSession session = request.getSession();
			session.setAttribute("username", uname);
			response.sendRedirect("home");
		}
		else {
			out.println("Error andding user\nPlease try again");
		}
	}
}
