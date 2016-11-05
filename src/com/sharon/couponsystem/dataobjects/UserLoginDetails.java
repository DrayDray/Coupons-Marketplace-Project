package com.sharon.couponsystem.dataobjects;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserLoginDetails {

	private long userId;
	private String password;
	private String clientType;
	
	public UserLoginDetails() {
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

}
