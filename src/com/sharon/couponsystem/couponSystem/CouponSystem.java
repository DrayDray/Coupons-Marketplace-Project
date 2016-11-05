package com.sharon.couponsystem.couponSystem;

import com.sharon.couponsystem.thread.DailyExpiredCouponDeletionThreadScheduler;
import com.sharon.couponsystem.enums.ErrorType;
import com.sharon.couponsystem.exceptions.CouponSystemException;
import com.sharon.couponsystem.facade.CouponFacade;
import com.sharon.couponsystem.facade.CompanyFacade;
import com.sharon.couponsystem.facade.CustomerFacade;

public class CouponSystem {
	
	//CouponSystem is an "eager" type Singleton
	private static CouponSystem instanceCouponSystem = new CouponSystem();
	private static CouponFacade instanceAdminFacade;
	private static CompanyFacade instanceCompanyFacade;
	private static CustomerFacade instanceCustomerFacade;
	private static DailyExpiredCouponDeletionThreadScheduler dailyCouponDeletionScheduler;

	private CouponSystem(){
		//We will need these Facades for the actions they enable in the CouponSystem
		instanceAdminFacade = new CouponFacade();
		instanceCompanyFacade = new CompanyFacade();
		instanceCustomerFacade = new CustomerFacade();
		
		//Our CouponSystem will immediately kick-start the following scheduler which calls
		//the thread which erases expired coupons every 24 hrs
		dailyCouponDeletionScheduler = new DailyExpiredCouponDeletionThreadScheduler();
		dailyCouponDeletionScheduler.start();
	}
	
	public static CouponSystem getInstanceCouponSystem(){
		return instanceCouponSystem;
	}
	
	public boolean login(String userType, long userId, String password) throws CouponSystemException {
		//In a future version the login will return one of the Facades, so that all of the functions
		//relevant to the given user can be used
		if(userType.equalsIgnoreCase("customer")){
			if(instanceCustomerFacade.login(userId, password)){
				return true;
			}
		}
		if(userType.equalsIgnoreCase("company")){
			if(instanceCompanyFacade.login(userId, password)){
				return true;
			}
		}
		if(userType.equalsIgnoreCase("admin")){
			if(instanceAdminFacade.login(userId, password)){
				return true;
			}
		}
		throw new CouponSystemException(ErrorType.LOGIN_FAILED);
	}
	
	public void shutdown(){
		dailyCouponDeletionScheduler.stop();
	}
}
