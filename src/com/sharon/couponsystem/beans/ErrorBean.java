package com.sharon.couponsystem.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorBean {
	
	private int internalErrorCode;
	private String message;
	
	public ErrorBean() {
	}

	public ErrorBean(int internalErrorCode, String message) {
		this.internalErrorCode = internalErrorCode;
		this.message = message;
	}

	public int getInternalErrorCode() {
		return internalErrorCode;
	}

	public void setInternalErrorCode(int internalErrorCode) {
		this.internalErrorCode = internalErrorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Internal Error Code " + internalErrorCode + ": " + message;
	}
	
	
}
