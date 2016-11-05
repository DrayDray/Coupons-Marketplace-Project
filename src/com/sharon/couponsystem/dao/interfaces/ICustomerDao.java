package com.sharon.couponsystem.dao.interfaces;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import com.sharon.couponsystem.beans.Coupon;
import com.sharon.couponsystem.beans.Customer;
import com.sharon.couponsystem.exceptions.CouponSystemException;

public interface ICustomerDao {

	public void addCustomer(Customer customer) throws CouponSystemException;
	public void removeCustomer(long customerId) throws CouponSystemException;
	public void updateCustomer(long customerId, String updatedPassword) throws CouponSystemException;
	public Customer getCustomer(long customerId) throws CouponSystemException;
	public Collection<Customer> getAllCustomer() throws CouponSystemException;
	public boolean login(long customerId, String password) throws CouponSystemException;
	public boolean isCustomerExistsById(long customerId);
	public boolean isCustomerExistsByTitle(Customer customer) throws CouponSystemException;
	
}
