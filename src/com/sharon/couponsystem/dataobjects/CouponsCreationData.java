package com.sharon.couponsystem.dataobjects;

import javax.xml.bind.annotation.XmlRootElement;

import com.sharon.couponsystem.beans.Coupon;

@XmlRootElement
public class CouponsCreationData {

	private Coupon coupon;
	private long companyId;
	
	public CouponsCreationData(){
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	
}
