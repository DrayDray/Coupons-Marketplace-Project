package com.sharon.couponsystem.facade;

import java.util.Collection;

import com.sharon.couponsystem.beans.Company;
import com.sharon.couponsystem.dao.CompanyDao;
import com.sharon.couponsystem.exceptions.CouponSystemException;
import com.sharon.couponsystem.facade.interfaces.ICouponClientFacade;

import com.sharon.couponsystem.enums.ErrorType;

public class CompanyFacade implements ICouponClientFacade{

	private CompanyDao companyDao;
	
	public CompanyFacade(){
		this.companyDao = new CompanyDao();
	}
	
	public void addCompany(Company company) throws CouponSystemException{
		if(companyDao.isCompanyExistsByTitle(company.getCompName())){
			throw new CouponSystemException(ErrorType.COMPANY_NAME_ALREADY_EXISTS);
		}
		if(companyDao.isCompanyExistsById(company.getId())){
			throw new CouponSystemException(ErrorType.COMPANY_ID_ALREADY_EXISTS);
		}
		companyDao.addCompany(company);
	}
	
	public void removeCompany(long companyId) throws CouponSystemException{
		ensureCompanyExistsById(companyId);
		companyDao.removeCompany(companyId);
	}

	public void updateCompany(long companyId, String updatedPassword) throws CouponSystemException{
		ensureCompanyExistsById(companyId);
		companyDao.updateCompany(companyId, updatedPassword);
	}
	
	public Company getCompany(long companyId) throws CouponSystemException{
		ensureCompanyExistsById(companyId);
		return companyDao.getCompany(companyId);
	}
	
	public Collection<Company> getAllCompanies() throws CouponSystemException{
		return companyDao.getAllCompanies();
	}
	
	public boolean login(long companyId, String password) throws CouponSystemException{
		if(companyDao.login(companyId, password)){
			return true;
		}
		throw new CouponSystemException(ErrorType.LOGIN_FAILED);
	}

	//Don't need to expose this to the API, it is only used by the other facade functions.
	public void ensureCompanyExistsById(long companyId)throws CouponSystemException{
		if(companyDao.getCompany(companyId) == null){
			throw new CouponSystemException(ErrorType.COMPANY_DOES_NOT_EXIST);
		}
	}
}
