package com.sharon.couponsystem.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtils {

	static
	{
		try {
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
