package com.sharon.couponsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sharon.couponsystem.beans.Company;
import com.sharon.couponsystem.beans.Coupon;
import com.sharon.couponsystem.dao.interfaces.ICompanyDao;
import com.sharon.couponsystem.enums.CouponType;
import com.sharon.couponsystem.enums.ErrorType;
import com.sharon.couponsystem.exceptions.CouponSystemException;
import com.sharon.couponsystem.utils.JdbcUtils;

public class CompanyDao implements ICompanyDao {

	public CompanyDao(){
	}

	public void addCompany(Company company) throws CouponSystemException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try 
		{
			connection = JdbcUtils.getConnection();

			preparedStatement = connection.prepareStatement("insert into companies (ID, companyName,"
					+ " PASSWORD, EMAIL) values (?,?,?,?)");

			// Replacing question mark #1 with the value inside coupon 
			preparedStatement.setLong(1, company.getId());
			preparedStatement.setString(2, company.getCompName());
			preparedStatement.setString(3, company.getPassword());
			preparedStatement.setString(4, company.getEmail());

			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		} 
		finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public void removeCompany(long companyId) throws CouponSystemException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			//1- remove company from companies
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM companies "
					+ "WHERE ID = ?");
			preparedStatement.setLong(1, companyId);
			preparedStatement.executeUpdate();
			
			//2- remove company from company_coupons table
			removeCompanyFromCompanyCoupons(companyId, connection);
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	private void removeCompanyFromCompanyCoupons(long companyId, Connection connection) throws SQLException {
		PreparedStatement preparedStatement2 = null;
		preparedStatement2 = connection.prepareStatement("DELETE FROM company_coupons "
					+ "WHERE company_ID = ?");
		preparedStatement2.setLong(1, companyId);
		preparedStatement2.executeUpdate();
	}

	public void updateCompany(long companyId, String updatedPassword) throws CouponSystemException {
		//In our system updateCompany is an update of the password.
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		ResultSet resultSet = null;

		try{
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE companies "
					+ "SET PASSWORD = ? "
					+ "WHERE ID = ?;");

			preparedStatement.setString(1, updatedPassword);
			preparedStatement.setLong(2, companyId);

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

	public Company getCompany(long companyId) throws CouponSystemException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		ResultSet resultSet = null;
		Company company = null;

		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("select * from companies where ID = ?");
			preparedStatement.setLong(1, companyId);

			resultSet = preparedStatement.executeQuery();

			// if the company does not exist, return null:
			if (!resultSet.next()) {
				return null;
			} 

			//If the company does exist, we retrieve the values of that row and "recreate" the coupon in a Java format
			company = extractCompanyFromResultSet(resultSet);
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return company;
	}
	
	private Company extractCompanyFromResultSet(ResultSet resultSet) throws SQLException {
		//extract all the company values
		long id = resultSet.getLong("ID");
		String companyName = resultSet.getString("companyName");
		String password = resultSet.getString("PASSWORD");
		String email = resultSet.getString("EMAIL");

		//"Recreate" the company
		Company company = new Company(id, companyName, password, email);
		return company;		
	}

	public Collection<Company> getAllCompanies() throws CouponSystemException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Company company = null;

		// preparing an arrayList to hold the data
		List<Company> companyList = new ArrayList<Company>();

		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("select * from companies");			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){
				company = extractCompanyFromResultSet(resultSet);

				//add the company to the arrayList
				companyList.add(company);		
			}	
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return companyList;
	}

	public boolean login(long companyId, String password) throws CouponSystemException {
		if(getCompany(companyId)!=null){
			if(isPasswordCorrectById(companyId, password)){
				return true;
			}
		}
		return false;
	}

	private boolean isPasswordCorrectById(long companyId, String password) throws CouponSystemException {
		//We test if the password is matching here

		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("SELECT PASSWORD FROM companies where ID = ?");
			preparedStatement.setLong(1, companyId);

			resultSet = preparedStatement.executeQuery();

			//first go to the first line of the data
			resultSet.next();
			//then we retrieve the correct password and compare to the password given
			String correctPassword = resultSet.getString("PASSWORD");
			if (correctPassword.equals(password)) {
				return true;
			} 
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public boolean isCompanyExistsByTitle(String companyName) throws CouponSystemException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("select * from companies where companyName = ?");
			preparedStatement.setString(1, companyName);

			resultSet = preparedStatement.executeQuery();

			// If there's something in the resultSet then a company with the same companyName exists in the system
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
