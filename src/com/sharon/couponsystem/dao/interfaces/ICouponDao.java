package com.sharon.couponsystem.dao.interfaces;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import com.sharon.couponsystem.beans.Coupon;
import com.sharon.couponsystem.enums.CouponType;
import com.sharon.couponsystem.exceptions.CouponSystemException;

public interface ICouponDao {

	public void addCouponForACompany(Coupon coupon, long companyId) throws CouponSystemException;
	public void removeCoupon(long couponId) throws CouponSystemException;
	public void updateCoupon(long couponID, Timestamp updatedEndDate, int updatedPrice) throws CouponSystemException;
	public boolean isCouponExistsById(long couponId);
	public Coupon getCoupon(long couponId) throws CouponSystemException;
	public Collection<Coupon> getAllCoupons() throws CouponSystemException;
	public Collection<Coupon> getCouponsByType(CouponType coupontype) throws CouponSystemException;
	public boolean isCouponExistByTitle(String title) throws CouponSystemException;
	public Collection<Coupon> getCouponsByCompany(long companyId) throws CouponSystemException;
	public List<Coupon> getCouponsByCustomer(long customerId) throws CouponSystemException;
	public boolean isCustomerAlreadyHasCoupon(long customerId, long couponId) throws CouponSystemException;
	public boolean isCouponIsInStock(long couponId) throws CouponSystemException;
	public boolean isCreatedCouponDateIsValid(Coupon coupon) throws CouponSystemException;
	public boolean isExistingCouponsDateIsValid(long couponId) throws CouponSystemException;
	public void purchaseCouponByCustomer(long customerId, long couponId) throws CouponSystemException;
	public Collection<Coupon> getAllCouponsByMaxPrice(int maxCouponPrice) throws CouponSystemException ;
	public List<Coupon> getCouponsByCustomerAndType(long customerId, CouponType coupontype) throws CouponSystemException;
	public List<Coupon> getCouponsByCustomerAndPrice(long customerId, int maxPrice) throws CouponSystemException;
	public List<Coupon> getCouponsByCompanyAndType(long companyId, CouponType coupontype) throws CouponSystemException;
	public void removeExpiredCoupons()throws CouponSystemException;
}
