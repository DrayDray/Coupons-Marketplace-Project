package com.sharon.couponsystem.thread;

import java.util.Timer;

import com.sharon.couponsystem.dao.CouponDao;
import com.sharon.couponsystem.exceptions.CouponSystemException;

public class DailyExpiredCouponDeletionThread extends Thread {

	private CouponDao couponDao;
	
	public DailyExpiredCouponDeletionThread(){
		couponDao = new CouponDao();
	}
	
	@Override
	public void run(){
		try{
			couponDao.removeExpiredCoupons();
		}
		catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

}
