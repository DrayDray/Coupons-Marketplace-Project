package com.sharon.couponsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sharon.couponsystem.beans.Company;
import com.sharon.couponsystem.beans.Coupon;
import com.sharon.couponsystem.beans.Customer;
import com.sharon.couponsystem.dao.interfaces.ICustomerDao;
import com.sharon.couponsystem.enums.ErrorType;
import com.sharon.couponsystem.exceptions.CouponSystemException;
import com.sharon.couponsystem.utils.JdbcUtils;


public class CustomerDao implements ICustomerDao{
	public CustomerDao(){
	}
	
	public void addCustomer(Customer customer) throws CouponSystemException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;

		try{
			connection = JdbcUtils.getConnection();

			preparedStatement = connection.prepareStatement("insert into customers (ID, customer_Name, PASSWORD)"
					+ " values (?,?,?)");
			preparedStatement.setLong(1, customer.getId());
			preparedStatement.setString(2, customer.getCustomerName());
			preparedStatement.setString(3, customer.getPassword());

			preparedStatement.executeUpdate();
		}
		catch (SQLException e){
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally{
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public void removeCustomer(long customerId) throws CouponSystemException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("delete from customers "
					+ "WHERE ID = ?");
			preparedStatement.setLong(1, customerId);
			preparedStatement.executeUpdate();
			removeCustomerFromCustomerCoupons(customerId, connection);
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}
	
	private void removeCustomerFromCustomerCoupons(long customerId, Connection connection) throws SQLException {
		PreparedStatement preparedStatement2 = null;
		preparedStatement2 = connection.prepareStatement("DELETE FROM customer_coupons "
					+ "WHERE customer_ID = ?");
		preparedStatement2.setLong(1, customerId);
		preparedStatement2.executeUpdate();
	}
	
	public void updateCustomer(long customerId, String updatedPassword) throws CouponSystemException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		ResultSet resultSet = null;

		try{
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE customers "
					+ "SET PASSWORD = ? "
					+ "WHERE ID = ?;");

			preparedStatement.setString(1, updatedPassword);
			preparedStatement.setLong(2, customerId);

			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}
	
	public Customer getCustomer(long customerId) throws CouponSystemException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		ResultSet resultSet = null;
		Customer customer = null;
		
		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("select * from customers where ID = ?");
			preparedStatement.setLong(1, customerId);

			resultSet = preparedStatement.executeQuery();

			// if the customer does not exist, return null:
			if (!resultSet.next()) {
				return null;
			} 

			//if the customer does exist, retrieve the values of that row and "recreate" the coupon in a Java format
			customer = extractCustomerFromResultSet(resultSet);

		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return customer;
	}
	
	private Customer extractCustomerFromResultSet(ResultSet resultSet) throws SQLException {
		//extract all the customer values:
		long id = resultSet.getLong("ID");
		String customerName = resultSet.getString("customer_Name");
		String password = resultSet.getString("PASSWORD");

		//recreate the customer:
		Customer customer = new Customer(id, customerName, password);
		return customer;		
	}

	public Collection<Customer> getAllCustomer() throws CouponSystemException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Customer customer = null;

		// preparing an arrayList to hold the data
		List<Customer> customerList = new ArrayList<Customer>();

		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("select * from customers");			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){
				customer = extractCustomerFromResultSet(resultSet);

				//add the customer to the arrayList
				customerList.add(customer);		
			}	
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return customerList;
	}
	
	public boolean login(long customerId, String password) throws CouponSystemException {
		if(isCustomerExistsById(customerId)){
			if(isPasswordCorrectById(customerId, password)){
				return true;
			}
		}
		return false;
	}
	
	private boolean isPasswordCorrectById(long customerId, String password) throws CouponSystemException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		ResultSet resultSet = null;
		
		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("SELECT PASSWORD FROM customers where ID = ?");
			preparedStatement.setLong(1, customerId);

			resultSet = preparedStatement.executeQuery();

			//first go to the first line of the data
			resultSet.next();
			
			//then retrieve the correct password and compare to the password given
			String correctPassword = resultSet.getString("PASSWORD");
			if (correctPassword.equals(password)) {
				return true;
			} 
			return false;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public boolean isCustomerExistsById(long customerId){
		try {
			if (getCustomer(customerId) != null) {
				return true;
			}
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isCustomerExistsByTitle(Customer customer) throws CouponSystemException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("select * from customers where customer_Name = ?");
			preparedStatement.setString(1, customer.getCustomerName());

			resultSet = preparedStatement.executeQuery();

			// If there's something in the resultSet then a customer with the same CustomerName exists in the system
			if(resultSet.next()){
				return true;
			}
			//otherwise there is no such company 
			return false;
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		} 
		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}
	

}
