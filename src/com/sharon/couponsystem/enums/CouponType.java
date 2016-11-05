package com.sharon.couponsystem.enums;

public enum CouponType {
//In next stage, it would be good if I change the parentheses  to lower-case and make this enum
//like the ErrorType
	
	DINING("DINING"),
	ELECTRIC("ELECTRIC"),
	FOOD("FOOD"),
	HEALTH("HEALTH"),
	SPORTS("SPORTS"),
	CAMPING("CAMPING"),
	TRAVEL("TRAVEL");
	
	private String name;
	
	CouponType(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
}
