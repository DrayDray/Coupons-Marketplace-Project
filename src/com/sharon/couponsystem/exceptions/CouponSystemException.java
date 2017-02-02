package com.sharon.couponsystem.exceptions;

import com.sharon.couponsystem.enums.ErrorType;

public class CouponSystemException extends Exception {

	private ErrorType errorType;
	
	public CouponSystemException(ErrorType internalErrorType){
		this.errorType = internalErrorType;
	}

	public CouponSystemException(ErrorType internalErrorType, Exception e){
		super(e);
		this.errorType = internalErrorType;
	}
	
	public ErrorType getInternalErrorType() {
		return errorType;
	}
	
}
