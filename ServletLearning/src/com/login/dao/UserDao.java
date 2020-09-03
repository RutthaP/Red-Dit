package com.login.dao;

import java.sql.SQLException;

import com.login.model.User;

public class UserDao {
	DBConnection db;
	String dbUsername, dbPassword;
	
	public UserDao(String dbUsername, String dbPassword){
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
		db = 
			new DBConnection("jdbc:postgresql://localhost:5432/fjesbok", "postgres", "pass");
	}
	
	
	public boolean verifyUser(String uname, String password) {	
		String query = "select username, password from users where username=? and password=?";
		try {
			db.establishConnection();
			db.preparedStatement = db.connection.prepareStatement(query);
			db.preparedStatement.setString(1, uname);
			db.preparedStatement.setString(2, password);
			db.resultSet = db.preparedStatement.executeQuery();
			
			if(db.resultSet.isBeforeFirst() == false) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			System.out.println("Error handling statement   ");
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean addUser(String uname, String password) {
		String query = "insert into users(username, password, created_at) "
				+ "values(?, ?, now())";
		try {
			db.establishConnection();
			db.preparedStatement = db.connection.prepareStatement(query);
			db.preparedStatement.setString(1, uname);
			db.preparedStatement.setString(2, password);
			int updatedRows = db.preparedStatement.executeUpdate();
			
			if(updatedRows == 0) {
				return false;
			}
			return true;
		} catch(SQLException e) {
			System.out.println("Error adding user   ");
			e.printStackTrace();
			return false;
		}
		finally {
			db.closeConnection();
		}
	}
	
	
	public User getUser(int id) {
		String query = "select * from users where id=" +id;
		
		try {
			db.establishConnection();
			db.statement = db.connection.createStatement();
			db.resultSet = db.statement.executeQuery(query);
			
			if(db.resultSet.isBeforeFirst() == false) {
				System.out.println("User not found  ");
				return null;
			}
			db.resultSet.next();
			User result = new User();
			result.setId(db.resultSet.getInt("id"));
			result.setUsername(db.resultSet.getString("username"));
			result.setName(db.resultSet.getString("name"));
			result.setAbout(db.resultSet.getString("about"));
			result.setCreatedAt(db.resultSet.getTimestamp("created_at"));
			
			return result;
		} catch (SQLException e) {
			System.out.println("Error getting user   ");
			e.printStackTrace();
			return null;
		}
		finally {
			db.closeConnection();
		}
	}
	
	public User getUser(String uname) {
		String query = "select * from users where username=?";
		
		try {
			db.establishConnection();
			db.preparedStatement = db.connection.prepareStatement(query);
			db.preparedStatement.setString(1, uname);
			db.resultSet = db.preparedStatement.executeQuery();
			
			if(db.resultSet.isBeforeFirst() == false) {
				System.out.println("User not found  ");
				return null;
			}
			db.resultSet.next();
			User result = new User();
			result.setId(db.resultSet.getInt("id"));
			result.setUsername(db.resultSet.getString("username"));
			result.setName(db.resultSet.getString("name"));
			result.setAbout(db.resultSet.getString("about"));
			result.setCreatedAt(db.resultSet.getTimestamp("created_at"));
			
			return result;
		} catch (SQLException e) {
			System.out.println("Error getting user   ");
			e.printStackTrace();
			return null;
		}
		finally {
			db.closeConnection();
		}

	}
	
}
