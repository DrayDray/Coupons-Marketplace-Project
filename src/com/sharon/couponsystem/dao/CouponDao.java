package com.sharon.couponsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.sharon.couponsystem.beans.Coupon;
import com.sharon.couponsystem.dao.interfaces.ICouponDao;
import com.sharon.couponsystem.enums.CouponType;
import com.sharon.couponsystem.enums.ErrorType;
import com.sharon.couponsystem.exceptions.CouponSystemException;
import com.sharon.couponsystem.utils.JdbcUtils;


public class CouponDao implements ICouponDao {

	public CouponDao(){
	}

	public void addCouponForACompany(Coupon coupon, long companyId) throws CouponSystemException {
		PreparedStatement preparedStatement = null;
		long couponId = 0;
		Connection connection = null;

		try 
		{
			// Getting a connection from the connections manager (getConnection is a static method)
			connection = JdbcUtils.getConnection();

			preparedStatement = connection.prepareStatement("insert into coupons (TITLE, START_DATE,"
					+ " END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE) values (?,?,?,?, ?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, coupon.getTitle());
			preparedStatement.setTimestamp(2, coupon.getStartDate());
			preparedStatement.setTimestamp(3, coupon.getEndDate());
			preparedStatement.setInt(4, coupon.getAmount());
			preparedStatement.setString(5, coupon.getType().getName());
			preparedStatement.setString(6, coupon.getMessage());
			preparedStatement.setDouble(7, coupon.getPrice());
			preparedStatement.setString(8, coupon.getImage());

			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if(resultSet.next()) {
				couponId = resultSet.getLong(1);
			}
			linkCouponToCompany(companyId, couponId, connection);
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		} 
		finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	private void linkCouponToCompany(long companyId, long couponId, Connection connection) throws SQLException{
		//Here we are adding to the company-coupons linkage to the company-coupon table
		
		PreparedStatement preparedStatement2 = null;
		preparedStatement2 = connection.prepareStatement("insert into company_coupons"
				+ "(company_ID, coupon_ID) values (?, ?)");
		preparedStatement2.setLong(1, companyId);
		preparedStatement2.setLong(2, couponId);

		preparedStatement2.executeUpdate();
	}

	public void removeCoupon(long couponId) throws CouponSystemException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;		

		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM coupons "
					+ "WHERE ID = ?");
			preparedStatement.setLong(1, couponId);
			preparedStatement.executeUpdate();
			
			removeCouponLinkToCompany(connection, couponId);
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	private void removeCouponLinkToCompany(Connection connection, long couponId) throws SQLException{
		PreparedStatement preparedStatement = null;	
		preparedStatement = connection.prepareStatement("DELETE FROM company_coupons "
				+ "WHERE coupon_ID = ?");
		preparedStatement.setLong(1, couponId);
		preparedStatement.executeUpdate();
	}

	public void updateCoupon(long couponID, Timestamp updatedEndDate, int updatedPrice) throws CouponSystemException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE coupons "
					+ "SET END_DATE = ?, PRICE = ? "
					+ "WHERE ID = ?;");
			preparedStatement.setTimestamp(1, updatedEndDate);
			preparedStatement.setInt(2, updatedPrice);
			preparedStatement.setLong(3, couponID);

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

	public boolean isCouponExistsById(long couponId){
		try {
			if (this.getCoupon(couponId) != null) {
				return true;
			}
		} 
		catch (CouponSystemException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Coupon getCoupon(long couponId) throws CouponSystemException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		ResultSet resultSet = null;
		Coupon coupon = null;

		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("select * from coupons where id = ?");
			preparedStatement.setLong(1, couponId);

			resultSet = preparedStatement.executeQuery();

			// If the coupon does not exist, return null:
			if (!resultSet.next()) {
				return null;
			} 

			//if the coupon does exist, retrieve the values of that row and "recreate" the coupon in a Java format
			coupon = extractCouponFromResultSet(resultSet);
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return coupon;
	}

	public Collection<Coupon> getAllCoupons() throws CouponSystemException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		ResultSet resultSet = null;
		Coupon coupon = null;

		// preparing an arrayList to hold the data
		List<Coupon> couponsList = new ArrayList<Coupon>();

		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("select * from coupons");			

			resultSet = preparedStatement.executeQuery();

			// Loop as long as resultSet.next returns true.
			while (resultSet.next()){
				coupon = extractCouponFromResultSet(resultSet);

				//add the coupon to the arrayList
				couponsList.add(coupon);		
			}	
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return couponsList;
	}

	public Collection<Coupon> getCouponsByType(CouponType coupontype) throws CouponSystemException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		ResultSet resultSet = null;
		Coupon coupon = null;

		List<Coupon> couponsList = new ArrayList<Coupon>();

		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("select * from coupons where TYPE = ?");			
			preparedStatement.setString(1, coupontype.getName());

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){
				coupon = extractCouponFromResultSet(resultSet);
				couponsList.add(coupon);		
			}	
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return couponsList;
	}

	public boolean isCouponExistByTitle(String title) throws CouponSystemException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		ResultSet resultSet = null;
		Coupon coupon = null;

		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("select * from coupons where TITLE = ?");
			preparedStatement.setString(1, title);

			resultSet = preparedStatement.executeQuery();

			// If there's something in the resultSet then a coupon with the same title exists in the system
			if(resultSet.next()){
				return true;
			}
			//otherwise there is no such coupon 
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

	private Coupon extractCouponFromResultSet (ResultSet resultSet) throws SQLException{
		long id = resultSet.getLong("ID");
		String title = resultSet.getString("TITLE");
		Timestamp startDate = resultSet.getTimestamp("START_DATE");
		Timestamp endDate = resultSet.getTimestamp("END_DATE");
		int amount = resultSet.getInt("AMOUNT");
		CouponType type = CouponType.valueOf(resultSet.getString("TYPE"));
		String message = resultSet.getString("MESSAGE");
		int price = resultSet.getInt("PRICE");
		String image = resultSet.getString("IMAGE");

		//Recreate each coupon 
		Coupon coupon = new Coupon(id, title, startDate, endDate, amount, type, message, price, image);
		return coupon;
	}

	public Collection<Coupon> getCouponsByCompany(long companyId) throws CouponSystemException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = null;

		List<Coupon> couponsList = new ArrayList<Coupon>();

		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM company_coupons JOIN coupons "
					+ "ON company_coupons.coupon_ID = coupons.ID WHERE company_ID = ?;");			
			preparedStatement.setLong(1, companyId);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){
				coupon = extractCouponFromResultSet(resultSet);
				couponsList.add(coupon);		
			}	
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return couponsList;
	}

	public List<Coupon> getCouponsByCustomer(long customerId) throws CouponSystemException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = null;

		List<Coupon> couponsList = new ArrayList<Coupon>();

		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM coupons JOIN customer_coupons "
					+ "ON customer_coupons.coupon_ID = coupons.ID WHERE customer_coupons.customer_ID = ?;");			
			preparedStatement.setLong(1, customerId);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){
				coupon = extractCouponFromResultSet(resultSet);
				couponsList.add(coupon);		
			}	
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return couponsList;
	}

	public boolean isCustomerAlreadyHasCoupon(long customerId, long couponId) throws CouponSystemException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("SELECT coupon_ID FROM customer_coupons "
					+ "WHERE customer_ID = ?;");			
			preparedStatement.setLong(1, customerId);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){
				long customersOwnedCoupon = resultSet.getLong("coupon_ID");

				//For each customerCoupon, we check if it matches the couponID sent in as a parameter
				//If it matches, the customer already has the coupon (and will not be allowed to purchase it again)
				if(couponId == customersOwnedCoupon){
					return true;
				}
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

	public boolean isCouponIsInStock(long couponId) throws CouponSystemException {
		Connection connection = null;

		try {
			connection = JdbcUtils.getConnection();
			Coupon coupon = getCoupon(couponId);
			if (coupon.getAmount()> 0){
				//coupon is in stock
				return true;
			}
			return false;
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}

		finally {
			JdbcUtils.closeResources(connection);
		}
	}

	public boolean isCreatedCouponDateIsValid(Coupon coupon) throws CouponSystemException{
		Date date= new Date();
		Timestamp today = new Timestamp(date.getTime());

		//check if the date is valid:
		if (coupon.getEndDate().after(today)){
			return true;
		}
		return false;
	}
	public boolean isExistingCouponsDateIsValid(long couponId) throws CouponSystemException{
		Coupon coupon = getCoupon(couponId);
		
		Date date= new Date();
		Timestamp today = new Timestamp(date.getTime());

		//check if the date is valid:
		if (coupon.getEndDate().after(today)){
			return true;
		}
		return false;
	}

	public void purchaseCouponByCustomer(long customerId, long couponId) throws CouponSystemException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcUtils.getConnection();
			//First, we reduce the amount of the coupons left in the coupon table by one
			preparedStatement = connection.prepareStatement("UPDATE coupons SET AMOUNT = AMOUNT-1 WHERE ID = ?");			
			preparedStatement.setLong(1, couponId);

			preparedStatement.executeUpdate();

			//Then we link the coupon to the customer too
			linkCouponToCustomer(customerId, couponId, connection);
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}
	
	private void linkCouponToCustomer(long customerId, long couponId, Connection connection) throws SQLException{
		PreparedStatement preparedStatement2 = null;
		//Now, we also add to the customer-coupons table that the company now has this coupon
		preparedStatement2 = connection.prepareStatement("insert into customer_coupons"
				+ "(customer_ID, coupon_ID) values (?, ?)");
		preparedStatement2.setLong(1, customerId);
		preparedStatement2.setLong(2, couponId);

		preparedStatement2.executeUpdate();
	}

	public Collection<Coupon> getAllCouponsByMaxPrice(int maxCouponPrice) throws CouponSystemException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		ResultSet resultSet = null;
		Coupon coupon = null;

		List<Coupon> couponsList = new ArrayList<Coupon>();

		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * from coupons WHERE PRICE < ?");			
			preparedStatement.setInt(1, maxCouponPrice);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){
				coupon = extractCouponFromResultSet(resultSet);
				couponsList.add(coupon);		
			}	
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return couponsList;		
	}

	public List<Coupon> getCouponsByCustomerAndType(long customerId, CouponType coupontype) throws CouponSystemException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = null;

		List<Coupon> couponsList = new ArrayList<Coupon>();

		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM coupons JOIN customer_coupons "
					+ "ON customer_coupons.coupon_ID = coupons.ID WHERE customer_coupons.customer_ID = ? and coupons.TYPE = ?;");			
			preparedStatement.setLong(1, customerId);
			preparedStatement.setString(2, coupontype.getName());
			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){
				coupon = extractCouponFromResultSet(resultSet);
				couponsList.add(coupon);		
			}	
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return couponsList;
	}
	
	public List<Coupon> getCouponsByCustomerAndPrice(long customerId, int maxPrice) throws CouponSystemException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = null;

		List<Coupon> couponsList = new ArrayList<Coupon>();

		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM coupons JOIN customer_coupons "
					+ "ON customer_coupons.coupon_ID = coupons.ID WHERE customer_coupons.customer_ID = ? and coupons.PRICE < ?;");			
			preparedStatement.setLong(1, customerId);
			preparedStatement.setInt(2, maxPrice);
			
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				coupon = extractCouponFromResultSet(resultSet);
				couponsList.add(coupon);		
			}	
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return couponsList;
	}
	
	public List<Coupon> getCouponsByCompanyAndType(long companyId, CouponType coupontype) throws CouponSystemException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Coupon coupon = null;

		List<Coupon> couponsList = new ArrayList<Coupon>();

		try {
			connection = JdbcUtils.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM coupons JOIN company_coupons "
					+ "ON company_coupons.coupon_ID = coupons.ID WHERE company_coupons.company_ID = ? and coupons.TYPE = ?;");			
			preparedStatement.setLong(1, companyId);
			preparedStatement.setString(2, coupontype.getName());
			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){
				coupon = extractCouponFromResultSet(resultSet);
				couponsList.add(coupon);		
			}	
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new CouponSystemException(ErrorType.GENERAL_ERROR, e);
		}
		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return couponsList;
	}
	
	public void removeExpiredCoupons()throws CouponSystemException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try{
			connection = JdbcUtils.getConnection();

			preparedStatement = connection.prepareStatement("DELETE coupons, company_coupons, customer_coupons FROM "
					+ "coupons JOIN company_coupons ON coupons.ID = company_coupons.coupon_ID "
					+ "JOIN customer_coupons ON company_coupons.coupon_ID = customer_coupons.coupon_ID "
					+ "WHERE coupons.END_DATE < NOW();");			
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
}
