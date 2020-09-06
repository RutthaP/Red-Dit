package com.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.PostDao;

@WebServlet("/editPost")
public class EditPostServlet extends HttpServlet {
	PostDao postDao = null;
	
	/*
	 * Checks if the heading is valid(contains only whitespaces).
	 * Updates the post using post DAO.
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		
		String heading = request.getParameter("heading");
		String content = request.getParameter("content");
		int postId = Integer.parseInt(request.getParameter("postId"));
		
		if(heading.trim().length() <= 0) {
			out.println("Needs a valid heading\nPlease try again...");
			return;
		}
		
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		postDao = new PostDao();
		
		if(postDao.updatePost(postId, username, heading, content) == true) {
			response.sendRedirect("home");
			return;
		}else {
			out.println("Error updating post\nPlease try again...");
		}
	}
}
