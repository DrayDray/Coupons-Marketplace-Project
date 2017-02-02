package com.sharon.couponsystem.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.sharon.couponsystem.beans.ErrorBean;
import com.sharon.couponsystem.enums.ErrorType;

@Provider
public class ExceptionsHandler extends Exception implements ExceptionMapper<Throwable> 
{
    @Override
    public Response toResponse(Throwable exception) 
    {
    	System.out.println("Just entered into the exceptions mapper");
    	if (exception instanceof CouponSystemException){
    		CouponSystemException e = (CouponSystemException) exception;
    		
    		//get errorType from exception
    		ErrorType errorType = e.getInternalErrorType();
    		
    		//get internal error code from the errorType
    		int internalErrorCode = errorType.getInternalErrorCode();
    		
    		//get internal error name from errorType
    		String internalErrorName = errorType.getName();
    		
    		//if i wanted the exception message (from CouponSystemException, originally from Throwable)
    		//String message = e.getMessage();
    		
    		//Create ErrorBean which encapsulates what went wrong
    		ErrorBean errorBean = new ErrorBean(internalErrorCode, internalErrorName);
    		return Response.status(700).entity(errorBean).build();
    	}
    	
    	System.out.println("Returning 500 as the http status");
        return Response.status(500).entity("General failure").build();
    }
}