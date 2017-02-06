package com.sharon.couponsystem.facade;

import java.util.Collection;
import com.sharon.couponsystem.beans.Customer;
import com.sharon.couponsystem.dao.CustomerDao;
import com.sharon.couponsystem.exceptions.CouponSystemException;
import com.sharon.couponsystem.facade.interfaces.ICouponClientFacade;

import com.sharon.couponsystem.enums.ErrorType;

public class CustomerFacade implements ICouponClientFacade{

	private CustomerDao customerDao;

	public CustomerFacade(){
		this.customerDao = new CustomerDao();
	}
	
	public void updateCustomer(long customerId, String updatedPassword) throws CouponSystemException{
		ensureCustomerExistsById(customerId);
		customerDao.updateCustomer(customerId, updatedPassword);
	}
	
	public Customer getCustomer(long customerId) throws CouponSystemException{
		ensureCustomerExistsById(customerId);
		return customerDao.getCustomer(customerId);
	}
	
	public Collection<Customer> getAllCustomers() throws CouponSystemException{
		return customerDao.getAllCustomer();
	}

	public void addCustomer(Customer customer)throws CouponSystemException {
		if(customerDao.isCustomerExistsByTitle(customer)){
			throw new CouponSystemException(ErrorType.CUSTOMER_NAME_ALREADY_EXISTS);
		}
		if(customerDao.isCustomerExistsById(customer.getId())){
			throw new CouponSystemException(ErrorType.CUSTOMER_ID_ALREADY_EXISTS);
		}
		customerDao.addCustomer(customer);
	}
	
	public void removeCustomer(long customerId) throws CouponSystemException{
		ensureCustomerExistsById(customerId);
		customerDao.removeCustomer(customerId);
	}
	
	//Don't need to expose this to the API, it is only used by the other facade functions.
	public void ensureCustomerExistsById(long customerId)throws CouponSystemException{
		if(!customerDao.isCustomerExistsById(customerId)){
			throw new CouponSystemException(ErrorType.CUSTOMER_DOES_NOT_EXIST);
		}
	}
	
	public boolean login(long customerId, String password) throws CouponSystemException{
		if(customerDao.login(customerId, password)){
			return true;
		}
		throw new CouponSystemException(ErrorType.LOGIN_FAILED);
	}
}
