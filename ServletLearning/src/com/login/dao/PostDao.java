package com.login.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.login.model.Post;

public class PostDao {
	DBConnection db;
	
	public PostDao() {
		db = new DBConnection("jdbc:postgresql://localhost:5432/fjesbok", "postgres", "pass");
	}
	
	public boolean addPost(int userID, String username, String heading, String content) {
		String query = "insert into posts(user_id, username, heading, content, date)"
				+ " values(?, ?, ?, ?,now())";
		
		try {
			db.establishConnection();
			db.preparedStatement = db.connection.prepareStatement(query);
			db.preparedStatement.setInt(1, userID);
			db.preparedStatement.setString(2, username);
			db.preparedStatement.setString(3, heading);
			db.preparedStatement.setString(4, content);
			int insertedRows = db.preparedStatement.executeUpdate();
			
			if(insertedRows == 0) {
				return false;
			}
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			db.closeConnection();
		}
	}
	
	
	public boolean updatePost(int postId, String username, String heading, String content) {
		String query = "update posts "
				+ "set heading = ?, content = ?, update_date = now() "
				+ "where username = ? and id = " + postId;
		try {
			db.establishConnection();
			db.preparedStatement = db.connection.prepareStatement(query);
			db.preparedStatement.setString(1, heading);
			db.preparedStatement.setString(2, content);
			db.preparedStatement.setString(3, username);
			
			int updatedRows = db.preparedStatement.executeUpdate();
			if(updatedRows == 0) {
				return false;
			}
			return true;
					
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			db.closeConnection();
		}
	}
	
	
	public List<Post> getAllPosts() {
		String query = "select * from posts order by date desc";
		
		try {
			db.establishConnection();
			db.statement = db.connection.createStatement();
			db.resultSet = db.statement.executeQuery(query);
			
			if(db.resultSet.isBeforeFirst() == false) {
				return null;
			}
			
			List<Post> result = new ArrayList<Post>();
			while(db.resultSet.next()) {
				Post post = new Post();
				post.setId(db.resultSet.getInt("id"));
				post.setUserID(db.resultSet.getInt("user_id"));
				post.setUsername(db.resultSet.getString("username"));
				post.setHeading(db.resultSet.getString("heading"));
				post.setDate(db.resultSet.getTimestamp("date"));
				if(db.resultSet.getString("content") != null) {
					post.setContent(db.resultSet.getString("content"));
				}
				if(db.resultSet.getTimestamp("update_date") != null) {
					post.setUpdateDate(db.resultSet.getTimestamp("update_date"));
				}
				result.add(post);
			}
			
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Post> getUserPosts(int userID){
		String query = "select * from posts where user_id=" + userID + "order by date desc";
		
		db.establishConnection();
		try {
			db.statement = db.connection.createStatement();
			db.resultSet = db.statement.executeQuery(query);
			
			if(db.resultSet.isBeforeFirst() == false) {
				return null;
			}
			
			List<Post> result = new ArrayList<Post>();
			while(db.resultSet.next()) {
				Post post = new Post();
				post.setId(db.resultSet.getInt("id"));
				post.setUserID(userID);
				post.setUsername(db.resultSet.getString("username"));
				post.setHeading(db.resultSet.getString("heading"));
				post.setDate(db.resultSet.getTimestamp("date"));
				if(db.resultSet.getString("content") != null)
					post.setContent(db.resultSet.getString("content"));
				if(db.resultSet.getTimestamp("update_date") != null)
					post.setUpdateDate(db.resultSet.getTimestamp("update_date"));
				
				result.add(post);
			}
			
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
