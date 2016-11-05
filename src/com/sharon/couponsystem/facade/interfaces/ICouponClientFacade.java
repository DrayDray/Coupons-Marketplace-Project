package com.sharon.couponsystem.facade.interfaces;

import com.sharon.couponsystem.exceptions.CouponSystemException;

public interface ICouponClientFacade {

	//for now we are not receiving clientType, in the next stage we will add & use this parameter
	public boolean login(long customerId, String password) throws CouponSystemException;
	
}
