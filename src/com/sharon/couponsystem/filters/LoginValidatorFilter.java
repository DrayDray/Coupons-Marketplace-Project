package com.sharon.couponsystem.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginValidatorFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//HttpServletRequest is the son of ServletRequest, and only it can get a session.
		//We first cast the request to an HttpServletRequest.
		HttpServletRequest req = (HttpServletRequest) request;

		//Here we check if a session already exists or not. If the user has logged in, the session will exist.
		if (req.getSession(false) != null){
			//if we get through (aka if there is session available) then i allow to pass forward to the JERSEY servlet
			System.out.println("FOR TEST - we just passed through doFilter of LoginValidatorFilter successfully");
			chain.doFilter(request, response);
		}
		else{
			//first we cast, so that we will be able to set the status.
			HttpServletResponse res = (HttpServletResponse) response;
			System.out.println("FOR TEST- We did not successfully pass the filter because the session already expired.");
			
			//if there is no session we want to set the status that a failure occured
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			
			//httpServletResponse is the son, who can set a Status, while the ServletResponse cannot
			res.setHeader("ErrorCause", "There is no valid session so the filter did not let the request pass.");
		}
	}

	public void destroy() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
