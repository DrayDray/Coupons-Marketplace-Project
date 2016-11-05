package com.sharon.couponsystem.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.sharon.couponsystem.dataobjects.UserLoginDetails;
import com.sharon.couponsystem.exceptions.CouponSystemException;
import com.sharon.couponsystem.facade.CouponFacade;
import com.sharon.couponsystem.facade.CompanyFacade;
import com.sharon.couponsystem.facade.CustomerFacade;
import com.sharon.couponsystem.facade.interfaces.ICouponClientFacade;

@Path("/userservices")
public class UserServicesApi {

	
	//works
	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	//receives UserLoginDetails in a type JSON
	public void login(@Context HttpServletRequest request, @Context HttpServletResponse response, UserLoginDetails userLoginDetails) throws CouponSystemException{
		//We don't know which facade we will need yet so we initialize the interface first:
		ICouponClientFacade facade = null;
		
		//Here we convert the JSON details into separate variables
		long userId = userLoginDetails.getUserId();
		String password = userLoginDetails.getPassword();
		String clientType = userLoginDetails.getClientType(); 
		
		//in order to know which login to go to, I check the clientType
		//although the facade will only be used one time within this function, in order check the login
		//and will be erased after leaving this function, so as not to make us stateful
		if (clientType.equals("admin")){			
			facade = new CouponFacade();	
		}
		else if (clientType.equals("customer")){
			facade = new CustomerFacade();
		}
		else if(clientType.equals("company")){
			facade = new CompanyFacade();
		}
		//I check if the login details are correct
		//If the login fails, the following function will throw an exception:
		//(and an exception means bypassing the session creation)
		facade.login(userId, password);
		
		//only if the login succeeded will we reach the following lines:
		
		//1-create a session
		HttpSession session = request.getSession();
		
		//2- Mark what type of client this is in a their cookie:
		//create a new cookie
		Cookie cookie = new Cookie("clientType", clientType);
		
		//the fact that i changed the userId to a string here might be a problem -  test this
		Cookie cookie2 = new Cookie("userId", ""+userId);
		cookie.setPath("/");
		cookie2.setPath("/");
		
		//add the cookie to the response.
		response.addCookie(cookie);
		response.addCookie(cookie2);
		
		System.out.println("Login Succeded");
	}
}
