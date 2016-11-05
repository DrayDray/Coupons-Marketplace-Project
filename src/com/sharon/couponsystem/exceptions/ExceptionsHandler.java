package com.sharon.couponsystem.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.sharon.couponsystem.beans.ErrorBean;

 //Question - review this b/c i don't really understand
@Provider
public class ExceptionsHandler extends Exception implements ExceptionMapper<Throwable> 
{
    @Override
    public Response toResponse(Throwable exception) 
    {
    	System.out.println("Just entered into the exceptions mapper");
    	if (exception instanceof CouponSystemException){
    		CouponSystemException e = (CouponSystemException) exception;
    		
    		int internalErrorCode = e.getInternalErrorCode().getInternalErrorCode();
    		String message = e.getMessage();
    		ErrorBean errorBean = new ErrorBean(internalErrorCode, message);
    		return Response.status(700).entity(errorBean).build();
    	}
    	
    	System.out.println("Returning 500 as the http status");
        return Response.status(500).entity("General failure").build();
    }
}