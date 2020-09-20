package com.login.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.login.model.Comment;

public class CommentDao {
	DBConnection db;
	
	public CommentDao() {
		db = new DBConnection("jdbc:postgresql://localhost:5432/fjesbok", "postgres", "pass");
	}
	
	public Comment getComment(int commentId) {
		String query = "select * from comments where comment_id = ?";
		try {
			db.establishConnection();
			db.preparedStatement = db.connection.prepareStatement(query);
			db.preparedStatement.setInt(1, commentId);
			db.resultSet = db.preparedStatement.executeQuery();
			
			if(db.resultSet.isBeforeFirst() == false) {
				System.out.println("Error fetching comment");
				return null;
			}
			
			db.resultSet.next();
			Comment comment = new Comment();
			comment.setCommentId(db.resultSet.getInt("comment_id"));
			comment.setPostId(db.resultSet.getInt("post_id"));
			comment.setUserId(db.resultSet.getInt("user_id"));
			comment.setContent(db.resultSet.getString("comment"));
			comment.setDate(db.resultSet.getTimestamp("date"));
			return comment;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally {
			db.closeConnection();
		}
	}
	
	
	public boolean addComment(int userId, int postId, String comment) {
		String query = "insert into comments(post_id, user_id, comment, date) values(?,?,?, now())";
		
		try {
			db.establishConnection();
			db.preparedStatement = db.connection.prepareStatement(query);
			db.preparedStatement.setInt(1, postId);
			db.preparedStatement.setInt(2, userId);
			db.preparedStatement.setString(3, comment);
			
			int insertedRows = db.preparedStatement.executeUpdate();
			if(insertedRows == 0) {
				System.out.println("Error adding comment");
				return false;
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally {
			db.closeConnection();
		}
	}
	
	
	public List<Comment> getCommentsForPost(int postId){
		String query = "select * from comments where post_id = ? order by date desc";
		
		try {
			db.establishConnection();
			db.preparedStatement = db.connection.prepareStatement(query);
			db.preparedStatement.setInt(1, postId);
			
			db.resultSet = db.preparedStatement.executeQuery();
			if(db.resultSet.isBeforeFirst() == false) {
				return null;
			}
			
			List<Comment> comments = new ArrayList<Comment>();
			while(db.resultSet.next()) {
				Comment c = new Comment();
				c.setCommentId(db.resultSet.getInt("comment_id"));
				c.setPostId(db.resultSet.getInt("post_id"));
				c.setUserId(db.resultSet.getInt("user_id"));
				c.setContent(db.resultSet.getString("comment"));
				c.setDate(db.resultSet.getTimestamp("date"));
				
				comments.add(c);
			}
			return comments;
		} catch (SQLException e) {
			System.out.println("Error fetching comments...   " + e.getMessage());
			return null;
		}
		finally {
			db.closeConnection();
		}
	}
}
