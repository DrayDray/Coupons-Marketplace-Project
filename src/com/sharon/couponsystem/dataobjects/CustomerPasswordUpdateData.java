package com.sharon.couponsystem.dataobjects;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerPasswordUpdateData {
	
	private long customerId;
	private String password;
	
	public CustomerPasswordUpdateData(){
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
