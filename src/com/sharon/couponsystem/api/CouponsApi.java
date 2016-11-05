package com.sharon.couponsystem.api;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;


import com.sharon.couponsystem.beans.Coupon;
import com.sharon.couponsystem.dataobjects.CompanyPasswordUpdateData;
import com.sharon.couponsystem.dataobjects.CouponUpdateData;
import com.sharon.couponsystem.dataobjects.CouponsCreationData;
import com.sharon.couponsystem.enums.CouponType;
import com.sharon.couponsystem.exceptions.CouponSystemException;
import com.sharon.couponsystem.facade.CompanyFacade;
import com.sharon.couponsystem.facade.CouponFacade;

@Path("/coupons")
public class CouponsApi {

	private CouponFacade couponFacade;
	
	public CouponsApi(){
		couponFacade = new CouponFacade();
	}
	
	//works
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addCouponForACompany(@Context HttpServletRequest request, CouponsCreationData couponCreationData) throws CouponSystemException{
		Coupon coupon = couponCreationData.getCoupon();
		long companyId = couponCreationData.getCompanyId();
		couponFacade.addCouponForACompany(coupon, companyId);
	}
	
	//works
	@DELETE
	@Path("/{couponId}")
	public void removeCoupon(@PathParam("couponId")long couponId) throws CouponSystemException{
		couponFacade.removeCoupon(couponId);
	}
	
	//works
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCouponForACompany(CouponUpdateData couponUpdateData) throws CouponSystemException{
		long couponId = couponUpdateData.getCouponId();
		long companyId = couponUpdateData.getCompanyId();
		Timestamp updatedEndDate = couponUpdateData.getUpdatedEndDate();
		int updatedPrice = couponUpdateData.getUpdatedPrice();
		
		couponFacade.updateCouponForACompany(companyId, couponId, updatedEndDate, updatedPrice);
	}
	
	//works
	@GET
	@Path("/getCouponByCompany/{couponId}/{companyId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Coupon getCouponByCompany(@PathParam("couponId")long couponId, @PathParam("companyId")long companyId) throws CouponSystemException{
		return couponFacade.getCouponByCompany(couponId, companyId);
	}
	
	//works
	@GET
	@Path("/getAllCouponsByCompany/{companyId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getAllCouponsByCompany(@PathParam("companyId")long companyId) throws CouponSystemException{
		return couponFacade.getAllCouponsByCompany(companyId);
	}
	
	//works
	@GET
	@Path("/getAllCouponsByCompanyAndType/{companyId}/{couponType}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getAllCouponsByCompanyAndType(@PathParam("companyId")long companyId, @PathParam("couponType") CouponType couponType) throws CouponSystemException{
		return couponFacade.getAllCouponsByCompanyAndType(companyId, couponType);
	}
	
	//works
	//QUESTION - should I make this a @put instead? 
	@GET
	@Path("/purchaseCoupon/{customerId}/{couponId}")
	public void purchaseCoupon(@PathParam("customerId") long customerId, @PathParam("couponId") long couponId) throws CouponSystemException{
		couponFacade.purchaseCoupon(customerId, couponId);
	}
	
	//works
	@GET
	@Path("/getAllPurchasedCouponsByCustomer/{customerId}")
	public Collection<Coupon> getAllPurchasedCouponsByCustomer(@PathParam("customerId") long customerId) throws CouponSystemException{
		return couponFacade.getAllPurchasedCouponsByCustomer(customerId);
	}
	
	//works
	@GET
	@Path("/getAllPurchasedCouponsByCustomerAndType/{customerId}/{couponType}")
	public Collection<Coupon> getAllPurchasedCouponsByCustomerAndType(@PathParam("customerId") long customerId, @PathParam("couponType") CouponType couponType) throws CouponSystemException{
		return couponFacade.getAllPurchasedCouponsByCustomerAndType(customerId, couponType);
	}
	
	//works
	@GET
	@Path("/getAllPurchasedCouponsByCustomerAndMaxPrice/{customerId}/{maxPrice}")
	public Collection<Coupon> getAllPurchasedCouponsByCustomerAndMaxPrice(@PathParam("customerId")long customerId, @PathParam("maxPrice") int maxPrice)throws CouponSystemException{
		return couponFacade.getAllPurchasedCouponsByCustomerAndMaxPrice(customerId, maxPrice);
	}
	
}
