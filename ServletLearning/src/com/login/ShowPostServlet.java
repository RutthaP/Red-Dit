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

import com.login.dao.CommentDao;
import com.login.dao.PostDao;
import com.login.model.Comment;
import com.login.model.Post;

/*
 * 
 */
@WebServlet("/showPost")
public class ShowPostServlet extends HttpServlet {
	PostDao postDao = null;
	CommentDao commentDao = null;
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		
		int postId = Integer.parseInt(request.getParameter("postId"));
		postDao = new PostDao();
		Post post = postDao.getPostById(postId);
		if(post == null) {
			out.println("Error finding post");
			return;
		}
		commentDao = new CommentDao();
		List<Comment> comments = commentDao.getCommentsForPost(postId);
		if(comments != null)
			post.setComments(comments);
		
		
		request.setAttribute("post", post);
		RequestDispatcher dispatcher = request.getRequestDispatcher("show_post.jsp");
		dispatcher.forward(request, response);
	}
}
