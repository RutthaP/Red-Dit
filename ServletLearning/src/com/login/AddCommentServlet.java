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

import com.login.dao.CommentDao;
import com.login.dao.UserDao;
import com.login.model.User;

/*
 * Servlet for adding comment, communicates with userDAO and commentDAO.
 * Gets the logged in user using userDAO, then adds the comment using commentDAO. 
 * When successful, it redirects to the post-page it was on using postID and postUname.
 */
@WebServlet("/addComment")
public class AddCommentServlet extends HttpServlet {
	UserDao userDao = null;
	CommentDao commentDao = null;
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if(session.getAttribute("username") == null) {
			out.println("Login to add comment...");
			return;
		}
		
		userDao = new UserDao();
		commentDao = new CommentDao();
		
		User user = userDao.getUser((String)session.getAttribute("username"));
		if(user == null) {
			out.println("Error getting userid");
			return;
		}
		
		int userId = user.getId();
		int postId = Integer.parseInt(request.getParameter("postId"));
		String comment = request.getParameter("comment");
		
		if(commentDao.addComment(userId, postId, comment) == false) {
			out.println("Error adding comment...");
		}else {
			response.sendRedirect("showPost?postId="+postId+
					"&uname="+request.getParameter("postUname"));
		}
	}
}
