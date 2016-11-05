package com.sharon.couponsystem.dao.interfaces;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import com.sharon.couponsystem.beans.Company;
import com.sharon.couponsystem.beans.Coupon;
import com.sharon.couponsystem.exceptions.CouponSystemException;

public interface ICompanyDao {

	public void addCompany(Company company) throws CouponSystemException;
	public void removeCompany(long companyId) throws CouponSystemException;
	public void updateCompany(long companyId, String updatedPassword) throws CouponSystemException;
	public Company getCompany(long companyId) throws CouponSystemException;
	public Collection<Company> getAllCompanies() throws CouponSystemException;
	public boolean login(long companyId, String password) throws CouponSystemException;
	public boolean isCompanyExistsByTitle(String companyName) throws CouponSystemException;
	
}
