package com.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	String dbName, dbUsername, dbPassword;
	
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	
	public DBConnection(String dbName, String dbUsername, String dbPassword) {
		this.dbName = dbName;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot regiser databasedriver    " + e.getMessage());
		}
	}
	
	
	public void establishConnection() {
		try {
			connection = DriverManager.getConnection(dbName, dbUsername, dbPassword);
		} catch (SQLException e) {
			System.out.println("Error establishing connection   " + e.getMessage());
		}
	}
	
	
	public void closeConnection() {
		try {
			if(resultSet != null)
				resultSet.close();
			if(preparedStatement != null)
				preparedStatement.close();
			if(connection != null)
				connection.close();
			
		} catch (SQLException e) {
			System.out.println("Error closing connection   " + e.getMessage());
		}
		
	}
}
