package com.sharon.couponsystem.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ErrorType {
	
	GENERAL_ERROR(1, "GeneralError"),
	CUSTOMER_NAME_ALREADY_EXISTS(2, "CustomerNameAlreadyExists"),
	COMPANY_NAME_ALREADY_EXISTS(3, "CompanyNameAlreadyExists"),
	COUPON_NAME_ALREADY_EXISTS(4, "CouponNameAlreadyExists"),
	CUSTOMER_DOES_NOT_EXIST(5, "CustomerDoesNotExist"),
	COMPANY_DOES_NOT_EXIST(6, "CompanyDoesNotExist"),
	LOGIN_FAILED(7, "LoginFailed"),
	COUPON_PREVIOUSLY_PURCHASED(8, "CouponPreviouslyPurchased"),
	COUPON_OUT_OF_STOCK(9, "CouponOutOfStock"),
	COUPON_EXPIRED(10, "CouponExpired"),
	COUPON_NOT_OWNED_BY_THIS_COMPANY(11, "CouponNotOwnedByThisCompany");
	
	private int internalErrorCode;
	private String name;
	private static Map<String, ErrorType> nameToTypeMap = new HashMap<String, ErrorType>();
	
	//this is a static block, therefore it is called immediately as the class is uploaded 
	//creating a map of the string name of the errortype (key) to the actual errortype which contains both the name and internalerrorcode (value)
	static {
		for (ErrorType errorType : EnumSet.allOf(ErrorType.class)){
			String name = errorType.getName();			
			nameToTypeMap.put(name, errorType);
		}		
	}

	ErrorType(int internalErrorCode, String name){
		this.internalErrorCode = internalErrorCode;
		this.name = name;		
	}
	
	public int getInternalErrorCode() {
		return internalErrorCode;
	}

	public String getName() {
		return name;
	}
	
	public static ErrorType getEnumByName(String name){
		return nameToTypeMap.get(name);
	}
}
