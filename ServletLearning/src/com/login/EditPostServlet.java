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
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String heading = request.getParameter("heading");
		String content = request.getParameter("content");
		int postId = Integer.parseInt(request.getParameter("postId"));
		
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		postDao = new PostDao();
		
		postDao.updatePost(postId, username, heading, content);
		
		response.sendRedirect("home");
	}
}
