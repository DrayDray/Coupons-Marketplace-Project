package com.sharon.couponsystem.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtils {

	//The Class class is located in the java.lang package, so it is distributed with java, and imported automatically into every class.
	
	static
	{
		try {
			//Class.forName(className) loads the class with the specified className.
			//When you let Java load the driver class with the forName call, the driver will register itself so that it can be used.
			//basically calling new com.mysql.jdbc.Driver()
			//see: http://stackoverflow.com/questions/12933113/better-understaning-class-fornamecom-mysql-jdbc-driver-newinstance
			
			//A JDBC driver is a software component enabling a Java application to interact with a database.
			//To connect with individual databases, JDBC (the Java Database Connectivity API) requires drivers for each database.
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException{
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/couponsystem","root", 
				"1234");
		// localhost means "THIS (LOCAL) COMPUTER"
		//3306 is the default port
		// SCHEMA_NAME_YOU_NEED_TO_RENAME is the DB name, also known as Schema name.
		return connection;
	}

	public static void closeResources(Connection connection, PreparedStatement preparedStatement){
		try {
			if(connection!=null){
				connection.close();
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if(preparedStatement!=null){
				preparedStatement.close();
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		closeResources(connection, preparedStatement);
		try {
			if(resultSet!=null){
				resultSet.close();
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeResources(Connection connection) {
		try {
			if(connection!=null){
				connection.close();
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
