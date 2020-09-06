package com.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.dao.PostDao;
import com.login.model.Post;

/*
 * Home servlet. Loads all the posts in the database and forwards 
 * them to index.jsp.
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	PostDao postDao = new PostDao();
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		List<Post> allPosts = postDao.getAllPosts();
		request.setAttribute("posts", allPosts);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
}
