package com.sharon.couponsystem.dataobjects;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


//Need to review if this is right...
@XmlAccessorType(XmlAccessType.NONE) 
@XmlRootElement(name = "CouponUpdateData") 
public class CouponUpdateData {
	@XmlElement(name = "couponId", required = false)
	private long couponId;
	
	@XmlElement(name = "updatedEndDate", required = true) 
	@XmlJavaTypeAdapter(com.sharon.couponsystem.utils.TimestampAdapter.class)
	private Timestamp updatedEndDate;
	
	@XmlElement(name = "updatedPrice", required = true)
	private int updatedPrice;
	
	@XmlElement(name = "companyId", required = false)
	private long companyId;
	
	public CouponUpdateData(){
	}

	
	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public Timestamp getUpdatedEndDate() {
		return updatedEndDate;
	}

	public void setUpdatedEndDate(Timestamp updatedEndDate) {
		this.updatedEndDate = updatedEndDate;
	}

	public int getUpdatedPrice() {
		return updatedPrice;
	}

	public void setUpdatedPrice(int updatedPrice) {
		this.updatedPrice = updatedPrice;
	}


	public long getCompanyId() {
		return companyId;
	}


	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	
	
}
