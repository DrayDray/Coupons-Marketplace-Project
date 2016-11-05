package com.sharon.couponsystem.facade;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import com.sharon.couponsystem.beans.Coupon;
import com.sharon.couponsystem.dao.CouponDao;
import com.sharon.couponsystem.exceptions.CouponSystemException;
import com.sharon.couponsystem.facade.interfaces.ICouponClientFacade;
import com.sharon.couponsystem.enums.CouponType;
import com.sharon.couponsystem.enums.ErrorType;

public class CouponFacade implements ICouponClientFacade{

	private CustomerFacade customerFacade;
	private CompanyFacade companyFacade;
	private CouponDao couponDao;

	public CouponFacade(){
		this.customerFacade = new CustomerFacade();
		this.companyFacade = new CompanyFacade();
		this.couponDao = new CouponDao();
	}

	public boolean login(long userId, String password) throws CouponSystemException {
		if (userId == 1111 & password.equals("1234")){
			return true;
		}
		throw new CouponSystemException(ErrorType.LOGIN_FAILED);
	}
	
	public void addCouponForACompany(Coupon coupon, long companyId)throws CouponSystemException{
		//First ensure that there is no coupon already existing under the same name:
		String couponTitle = coupon.getTitle();
		if(couponDao.isCouponExistByTitle(couponTitle)){
			throw new CouponSystemException(ErrorType.COUPON_NAME_ALREADY_EXISTS);
		}
		
		//and that the coupon's date is valid
		if(!couponDao.isCreatedCouponDateIsValid(coupon)){
			throw new CouponSystemException(ErrorType.COUPON_EXPIRED);
		}
		
		//and that the company exists in the system
		companyFacade.ensureCompanyExistsById(companyId);

		//then add the coupon for the company
		couponDao.addCouponForACompany(coupon, companyId);
	}

	public void removeCoupon(long couponId) throws CouponSystemException{
		//We will remove the coupon from the coupon table, and the company-coupon table.
		//We will not remove the coupon from the customer-coupon table because we want to have a history
		//of customer purchases

		couponDao.isCouponExistsById(couponId);
		couponDao.removeCoupon(couponId);
	}
	
	private void updateCoupon(long couponId, Timestamp updatedEndDate, int updatedPrice) throws CouponSystemException{
		couponDao.isCouponExistsById(couponId);
		couponDao.updateCoupon(couponId, updatedEndDate, updatedPrice);
	}
	
	public void updateCouponForACompany(long companyId, long couponId, Timestamp updatedEndDate, int updatedPrice) throws CouponSystemException{
		companyFacade.ensureCompanyExistsById(companyId);
		couponDao.isCouponExistsById(couponId);
		
		//Make sure the coupon is owned by this company (aka that they have "permission" to modify the coupon)
		if (!isCouponOwnedByThisCompany(companyId, couponId)){
			throw new CouponSystemException(ErrorType.COUPON_NOT_OWNED_BY_THIS_COMPANY);
		}
		couponDao.updateCoupon(couponId, updatedEndDate, updatedPrice);
	}

	private boolean isCouponOwnedByThisCompany(long companyId, long couponId) throws CouponSystemException{
		Collection<Coupon> companysCoupons = couponDao.getCouponsByCompany(companyId);
		for (Coupon coupon : companysCoupons) {
			if(coupon.getId() == couponId){
				return true;
			}
		}
		return false;
	}
	
	//Why do i have this function?
	public Coupon getCouponByCompany(long couponId, long companyId) throws CouponSystemException{
		companyFacade.ensureCompanyExistsById(companyId);
		couponDao.isCouponExistsById(couponId);
		return couponDao.getCoupon(couponId);
	}

	public Collection<Coupon> getAllCouponsByCompany(long companyId) throws CouponSystemException{
		companyFacade.ensureCompanyExistsById(companyId);
		return couponDao.getCouponsByCompany(companyId);
	}

	public Collection<Coupon> getAllCouponsByCompanyAndType(long companyId, CouponType couponType)throws CouponSystemException{
		companyFacade.ensureCompanyExistsById(companyId);
		return couponDao.getCouponsByCompanyAndType(companyId, couponType);
	}

	public void purchaseCoupon(long customerId, long couponId) throws CouponSystemException{
		//We will throw an exception if purchaseCoupon cannot be completed for any of the following reasons:
		//1- if the customer has already purchased the coupon:
		if(couponDao.isCustomerAlreadyHasCoupon(customerId, couponId)){
			//Question - should i throw the exception in the Dao class instead of here?
			throw new CouponSystemException(ErrorType.COUPON_PREVIOUSLY_PURCHASED);
		}

		//2- if the coupon is not in stock
		if(!couponDao.isCouponIsInStock(couponId)){
			throw new CouponSystemException(ErrorType.COUPON_OUT_OF_STOCK);
		}
		//3- if the coupon has expired. 
		//Technically, there is no need for this check since the CouponSystem removes expired coupons each day.
		//But for sake of good order, we will leave this check.
		if(!couponDao.isExistingCouponsDateIsValid(couponId)){
			throw new CouponSystemException(ErrorType.COUPON_EXPIRED);
		}

		//If we passed all of these tests, we can actually purchase the coupon:
		couponDao.purchaseCouponByCustomer(customerId, couponId);
	}

	public Collection<Coupon> getAllPurchasedCouponsByCustomer(long customerId) throws CouponSystemException{
		customerFacade.ensureCustomerExistsById(customerId); 
		//only if such a customer exists, retrieve his/her coupons
		return couponDao.getCouponsByCustomer(customerId);
	}

	public Collection<Coupon> getAllPurchasedCouponsByCustomerAndType(long customerId, CouponType couponType)throws CouponSystemException{
		customerFacade.ensureCustomerExistsById(customerId); 
		//only if such a customer exists, retrieve his/her coupons by Type
		return couponDao.getCouponsByCustomerAndType(customerId, couponType);
	}

	public Collection<Coupon> getAllPurchasedCouponsByCustomerAndMaxPrice(long customerId, int maxPrice)throws CouponSystemException{
		customerFacade.ensureCustomerExistsById(customerId); 
		//only if such a customer exists, retrieve his/her coupons by Type
		return couponDao.getCouponsByCustomerAndPrice(customerId, maxPrice);
	}
}




