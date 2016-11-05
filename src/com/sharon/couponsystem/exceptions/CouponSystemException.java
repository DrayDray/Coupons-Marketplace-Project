package com.sharon.couponsystem.exceptions;

import com.sharon.couponsystem.enums.ErrorType;

public class CouponSystemException extends Exception {

	private ErrorType internalErrorType;
	
	public CouponSystemException(ErrorType internalErrorCode){
		this.internalErrorType = internalErrorCode;
	}

	public CouponSystemException(ErrorType internalErrorCode, Exception e){
		super(e);
		this.internalErrorType = internalErrorCode;
	}
	
	public ErrorType getInternalErrorCode() {
		return internalErrorType;
	}
	
}
