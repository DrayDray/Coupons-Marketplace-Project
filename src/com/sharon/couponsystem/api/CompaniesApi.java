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

import com.sharon.couponsystem.beans.Company;
import com.sharon.couponsystem.dataobjects.CompanyPasswordUpdateData;
import com.sharon.couponsystem.exceptions.CouponSystemException;
import com.sharon.couponsystem.facade.CompanyFacade;

@Path("/companies")
public class CompaniesApi{

	private CompanyFacade companyFacade;
	
	public CompaniesApi(){
		this.companyFacade = new CompanyFacade();
	}
	
	//Works
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addCompany(Company company) throws CouponSystemException{
		companyFacade.addCompany(company);
	}
	
	//Works
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCompany(CompanyPasswordUpdateData companyPasswordUpdateData) throws CouponSystemException{
		long companyId = companyPasswordUpdateData.getCompanyId();
		String updatedPassword = companyPasswordUpdateData.getPassword();
		
		companyFacade.updateCompany(companyId, updatedPassword);
	}
	
	//works
	@GET
	@Path("/getCompany/{companyId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Company getCompany(@PathParam("companyId") long companyId) throws CouponSystemException{
		return companyFacade.getCompany(companyId);
	}
	
	//works
	@GET
	@Path("/getAllCompanies")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Company> getAllCompanies() throws CouponSystemException{
		return companyFacade.getAllCompanies();
	}
	
	//works
	@DELETE
	@Path("/{companyId}")
	public void removeCompany(@PathParam("companyId")long companyId) throws CouponSystemException{
		companyFacade.removeCompany(companyId);
	}
	
}
