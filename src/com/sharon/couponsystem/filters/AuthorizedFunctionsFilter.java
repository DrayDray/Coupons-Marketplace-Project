package com.sharon.couponsystem.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oracle.jrockit.jfr.RequestableEvent;

public class AuthorizedFunctionsFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//before going to any command we will also go through this filter,
		//which will go to the database (or the cache actually once avi shows me how to add the list of allowances to cache)
		//and check if this type of client (which we will check on the cookie)
		//is authorized to do the action it wants to do. I'm not quite sure how I will do this - consult Avi.
		
		//Get the clientType from their cookie
		Cookie[] cookies = ((HttpServletRequest) request).getCookies();
		//Question - The clientType doesn't print correctly
		String clientType = cookies[0].getValue().toString();

		//Check if the URL the person is trying to access is within their AuthorizedActions
		//What to do here...
		
		//Extract the URL first:
		StringBuffer  url = ((HttpServletRequest) request).getRequestURL();
		System.out.println("The url accessed was " + url);
		
		
		System.out.println("FOR TEST - we just passed through doFilter of AuthorizedFunctionsFilter successfully"
				+ "\n The clientType is: " + clientType);
		
		//Question- what happens here if there is no session?
		chain.doFilter(request, response);
	}
	
	public void destroy() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	
}
