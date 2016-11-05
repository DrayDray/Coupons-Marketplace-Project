package com.sharon.couponsystem.api;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sharon.couponsystem.beans.Customer;
import com.sharon.couponsystem.dataobjects.CustomerPasswordUpdateData;
import com.sharon.couponsystem.exceptions.CouponSystemException;
import com.sharon.couponsystem.facade.CustomerFacade;

@Path("/customers")
public class CustomersApi {

	private CustomerFacade customerFacade;
	
	public CustomersApi(){
		this.customerFacade = new CustomerFacade();
	}
	
	//works
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addCustomer(Customer customer) throws CouponSystemException{
		customerFacade.addCustomer(customer);
	}
	
	//works
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCustomer(CustomerPasswordUpdateData customerPasswordUpdateData) throws CouponSystemException{
		long customerId = customerPasswordUpdateData.getCustomerId();
		String updatedPassword = customerPasswordUpdateData.getPassword();
		
		customerFacade.updateCustomer(customerId, updatedPassword);
	}
	
	//works
	@GET
	@Path("/getCustomer/{customerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(@PathParam("customerId") long customerId) throws CouponSystemException{
		return customerFacade.getCustomer(customerId);
	}
	
	//works
	@GET
	@Path("/getAllCustomers")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Customer> getAllCustomers() throws CouponSystemException{
		return customerFacade.getAllCustomers();
	}
	
	//works
	@DELETE
	@Path("/{customerId}")
	public void removeCustomer(@PathParam("customerId")long customerId) throws CouponSystemException{
		customerFacade.removeCustomer(customerId);
	}
	
	
}
