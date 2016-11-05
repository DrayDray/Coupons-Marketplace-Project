package com.sharon.couponsystem.testing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.sharon.couponsystem.beans.Company;
import com.sharon.couponsystem.beans.Coupon;
import com.sharon.couponsystem.beans.Customer;
import com.sharon.couponsystem.couponSystem.CouponSystem;
import com.sharon.couponsystem.dao.CompanyDao;
import com.sharon.couponsystem.dao.CouponDao;
import com.sharon.couponsystem.dao.CustomerDao;
import com.sharon.couponsystem.enums.CouponType;
import com.sharon.couponsystem.exceptions.CouponSystemException;
import com.sharon.couponsystem.facade.CouponFacade;
import com.sharon.couponsystem.facade.CompanyFacade;
import com.sharon.couponsystem.facade.CustomerFacade;
import com.sharon.couponsystem.utils.JdbcUtils;


public class Tester {

	public static void main(String[] args) {

		//ALL TESTS HERE

//		java.util.Date date= new java.util.Date();
//		Timestamp startDate = new Timestamp(date.getTime());
//
//		date.setMonth(12);
//		Timestamp endDate = new Timestamp(date.getTime());
//		
//	//	CouponSystem instanceCouponSystem = CouponSystem.getInstanceCouponSystem();
//		Customer customer = new Customer(511, "Sharon Balaron", "hola");
//		Company company = new Company(120, "SportsCo5", "3434", "SportsCo5@gmail.com");
//		Coupon coupon = new Coupon("Health Coupon6", startDate, endDate, 20, CouponType.HEALTH, "Health6", 225, "Health6");
//		
//		CompanyFacade companyFacade = new CompanyFacade();
//		CouponFacade adminFacade = new CouponFacade();
//		CustomerFacade customerFacade = new CustomerFacade();
//		
//		try {
//			customerFacade.purchaseCoupon(506, 47);
//		} catch (CouponSystemException e) {
//			System.out.println(e.getInternalErrorCode());
//			e.printStackTrace();
//		}
//
//
//
//		//		

		//		Company company = new Company(118, "SportsCo2", "1212", "SportsCo2@gmail.com");
		//		Customer customer = new Customer(507, "Sharon Stones", "Hello3");
		//		Coupon coupon = new Coupon("Health Coupon3", startDate, endDate, 20, CouponType.HEALTH, "Health", 175, "Health");
		//		CompanyFacade companyFacade = new CompanyFacade();
		//		try {
		//			companyFacade.addCouponForACompany(coupon, 118);
		//		} catch (CouponSystemException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

		//		CouponSystem instanceCouponSystem = CouponSystem.getInstanceCouponSystem();
		//		try {
		//			System.out.println(instanceCouponSystem.login("admin", 111, "1234"));
		//		} catch (CouponSystemException e) {
		//			System.out.println(e.getInternalErrorCode());
		//			System.out.println("Error Code#" + e.getInternalErrorCode().getInternalErrorCode());
		//			e.printStackTrace();
		//		}

		//		Coupon coupon = new Coupon(33, "GP's something", startDate, endDate, 5, CouponType.DINING, "yum", 50, "Yea");

		//Tester
		//how to print an error here
		//		CustomerFacade customerFacade = new CustomerFacade();
		//		try {
		//			customerFacade.login(103, "1");
		//		} catch (CouponSystemException e) {
		//			// TODO Auto-generated catch block
		//			System.out.println(e.getInternalErrorCode());
		//			e.printStackTrace();
		//		}


		//TEST
		//		if CustomerFacade getAllPurchasedCouponsByCustomer works:
		//		CustomerFacade customerFacade = new CustomerFacade();	
		//
		//		Collection<Coupon> couponList = customerFacade.getAllPurchasedCouponsByCustomerAndMaxPrice(201201201, 300);
		//		//and print them all:
		//		for (Coupon coupon : couponList) {
		//			System.out.println(coupon.toString());
		//			System.out.println();
		//		}

		//		if CouponDao getAllPurchasedCouponsByCustomerAndPrice works:
		//		Create the CouponDao object:
		//		CouponDao couponDao = new CouponDao();	
		//		CustomerDao customerDao = new CustomerDao();
		//
		//		//Get the CouponList 
		//		Collection<Coupon> couponList = couponDao.getCouponsByCustomerAndPrice(60606060, 350);
		//		//and print them all:
		//		for (Coupon coupon : couponList) {
		//			System.out.println(coupon.toString());
		//			System.out.println();
		//		}


		//add coupon per company FACADE - have to do admin first!
		//		java.util.Date date= new java.util.Date();
		//		Timestamp startDate = new Timestamp(date.getTime());
		//
		//		date.setMonth(10);
		//		Timestamp endDate = new Timestamp(date.getTime());Coupon coupon1 = new Coupon("Land&Sea Daily Discount", startDate, endDate, 10, CouponType.DINING, "Daily 15% Discount", 200, "Pancakes");
		//		CouponDao couponDao = new CouponDao();
		//
		//		CompanyFacade companyFacade = new CompanyFacade();
		//		CustomerFacade customerFacade = new CustomerFacade();
		//		Coupon coupon = new Coupon("Bob's BarberShop Discount", startDate, endDate, 10, CouponType.HEALTH, "Discount Haircut", 50, "Nice Hair");
		//		Company company = new Company(115, "Bob's Barbershop", "Cuts123", "Bobs@gmail.com");
		//		companyFacade.addCouponForACompany(coupon, 115);



		//TEST
		//		//if CouponjFacade getAllPurchasedCouponsByCustomer works:
		//		//Create the CouponFacade object:
		//		CustomerFacade customerFacade = new CustomerFacade();				
		//		//Get the CouponList 
		//		Collection<Coupon> couponList = customerFacade.getAllPurchasedCouponsByCustomer(201201201);
		//		//and print them all:
		//		for (Coupon coupon : couponList) {
		//			System.out.println(coupon.toString());
		//			System.out.println();
		//		}

		//TEST
		//		//if CouponjFacade getAllPurchasedCoupons works:
		//		//Create the CouponFacade object:
		//		CustomerFacade customerFacade = new CustomerFacade();				
		//
		//		//Get the CouponList 
		//		List<Coupon> couponList = (ArrayList<Coupon>) customerFacade.getAllPurchasedCoupons();
		//		//and print them all:
		//		for (Coupon coupon : couponList) {
		//			System.out.println(coupon.toString());
		//			System.out.println();
		//		}

		//TEST
		//		if purchaseCoupon works
		//										CustomerFacade customerFacade = new CustomerFacade();
		//										customerFacade.purchaseCoupon(201201201, 6);
		//TEST
		//		//how would i look into the exception to see the coupon already exists error?
		//		CouponDao couponDao = new CouponDao();
		//		boolean test = couponDao.isCouponExistByTitle("CompanyA's Camping Coupon");
		//		System.out.println(test);

		//		TEST
		//		if CompanyDao login works
		//				CompanyDao companyDao = new CompanyDao();
		//				boolean test = companyDao.login((long) 5, "6666");
		//				System.out.println(test);

		//TEST
		//add coupon per company
		//				java.util.Date date= new java.util.Date();
		//				Timestamp startDate = new Timestamp(date.getTime());
		//		
		//			    date.setMonth(9);
		//				Timestamp endDate = new Timestamp(date.getTime());Coupon coupon1 = new Coupon("Land&Sea Daily Discount", startDate, endDate, 10, CouponType.DINING, "Daily 15% Discount", 200, "Pancakes");
		//				CouponDao couponDao = new CouponDao();
		//				CompanyDao companyDao = new CompanyDao();
		//				Coupon coupon2 = new Coupon("Green Papaya Monthly Discount", startDate, endDate, 5, CouponType.DINING, "Monthly 15% Discount", 120, "Thai Salads");
		//				
		//				couponDao.addCouponForACompany(coupon2, 110);
		//TEST
		//if customerDao.login works
		//		CustomerDao customerDao = new CustomerDao();
		//		boolean test = customerDao.login((long)05050505, "87654");
		//		System.out.println(test);

		//TEST
		//if getCouponsByCompany works
		//		CouponDao couponDao = new CouponDao();
		//
		//		Collection<Coupon> couponList =  couponDao.getCouponsByCompany(105);
		//		//and print them all:
		//		for (Coupon coupon : couponList) {
		//			System.out.println(coupon);
		//			System.out.println();
		//		}

		//		addCouponforCompany works
		//		

		//		Company company = new Company(108, "Land&Sea", "lala12", "Land&Sea@gmail.com");
		//		
		//		CouponDao couponDao = new CouponDao();
		//		couponDao.addCouponForACompany(coupon1, 108);
		//		couponDao.addCouponForACompany(coupon2, 108);
		//		couponDao.addCouponForACompany(coupon3, 108);

		//TEST
		//if getAllCustomers works:
		//		CustomerDao customerDao = new CustomerDao();
		//				//Get the CompanyList 
		//				List<Customer> customerList = (ArrayList<Customer>) customerDao.getAllCustomer();
		//				//and print them all:
		//				for (Customer customer1 : customerList) {
		//					System.out.println(customer1.toString());
		//					System.out.println();
		//				}


		//		//if CompanyDao.getAllCompanies works:
		//		//Create the CouponDao object:
		//		CompanyDao companyDao = new CompanyDao();
		//
		//		//Get the CompanyList 
		//		List<Company> companyList = (ArrayList<Company>) companyDao.getAllCompanies();
		//		//and print them all:
		//		for (Company company : companyList) {
		//			System.out.println(company.toString());
		//			System.out.println();
		//		}

		//TEST
		//if getCustomer works
		//		CustomerDao customerDao = new CustomerDao();
		//		System.out.println(customerDao.getCustomer(201201201));

		//TEST
		//if getCompany works
		//		CompanyDao companyDao = new CompanyDao();
		//		System.out.println(companyDao.getCompany(105));

		//TEST
		//that update company works
		//		CompanyDao companyDao = new CompanyDao();
		//		companyDao.updateCompany((long) 105, "6666");

		//TEST
		//that update customer works
		//		CustomerDao customerDao = new CustomerDao();
		//		customerDao.updateCustomer((long) 202202202, "6666");
		//		

		//TEST
		//that removeCompany works
		//				CompanyDao companyDao = new CompanyDao();
		//				companyDao.removeCompany(102);

		//TEST
		//that removecustomer works
		//		CustomerDao customerDao = new CustomerDao();
		//		customerDao.removeCustomer(102);

		//TEST
		//		that addCompany works
		//						Company company = new Company(116, "ElectricCo2", "444", "ElectricCo2@gmail.com");
		//						CompanyDao companyDao = new CompanyDao();
		//				
		//						companyDao.createCompany(company);

		//TEST
		////		that addCustomer works
		//				Customer customer = new Customer(60606060 ,"Mary Burger", "56565656");
		//				CustomerDao customerDao = new CustomerDao();
		//				
		//				customerDao.addCustomer(customer);

		//EXAMPLE FROM CLASS
		//One example for pushing the date forward - I can use this. 
		//		java.util.Date date= new java.util.Date();
		//		Timestamp timestamp = new Timestamp(date.getTime());
		//
		//		date.setMonth(7);
		//		Timestamp endDate = new Timestamp(date.getTime());

		//EXAMPLE FROM CLASS
		//Another exmaple of how to do timestamps
		//		long now = Calendar.getInstance().getTimeInMillis();
		//		Timestamp startDate = new Timestamp(now);
		//		
		//		long endDate = now + 9999999;
		//		Timestamp edDate = new Timestamp(endDate);

		//		//TEST
		//		//if CouponDao.updateCoupon works:
		//		//Create the CouponDao object:
		//		CouponDao CouponDao1 = new CouponDao();
		//		
		//		 java.util.Date date= new java.util.Date();
		//		 Timestamp timestamp = new Timestamp(date.getTime());
		//		 
		//		 CouponDao1.updateCoupon((long)1, timestamp, 1000);

		//		//TEST
		//		//if couponDao.remove works
		//		//Create the CouponDao object:
		//		CouponDao CouponDao1 = new CouponDao();
		//		
		//		//remove the Coupon
		//		CouponDao1.removeCoupon(4);
		//		
		//		//TEST
		//		//if couponDao.getCouponByType works
		//		//Create the CouponDao object:
		//		CouponDao CouponDao1 = new CouponDao();
		//		
		//		//Get the CouponList
		//		List<Coupon> couponList = (ArrayList<Coupon>) CouponDao1.getCouponByType(CouponType.DINING);
		//		//and print them all:
		//		for (Coupon coupon : couponList) {
		//			System.out.println(coupon.toString());
		//			System.out.println();
		//		}

		//TEST
		//		//if CouponDao.getAllCoupons works:
		//		//Create the CouponDao object:
		//		CouponDao CouponDao1 = new CouponDao();
		//
		//		//Get the CouponList 
		//		List<Coupon> couponList = (ArrayList<Coupon>) CouponDao1.getAllCoupons();
		//		//and print them all:
		//		for (Coupon coupon : couponList) {
		//			System.out.println(coupon.toString());
		//			System.out.println();
		//		}

		//TEST
		//		if CouponDao.getCoupon works:

		//		Create the CouponDao object:
		//		CouponDao CouponDao1 = new CouponDao();

		//Get a Coupon and print it

		//		System.out.println(CouponDao1.getCoupon(2));

		//		TEST
		//if CouponDao's addCoupon works:
		//Create a current timestamp:
		//		Calendar calendar = Calendar.getInstance();
		//		Timestamp currentTimestamp = new Timestamp(calendar.getTime().getTime());
		//
		//		//		//Create a Coupon:
		//		Coupon coupon = new Coupon("Indian Food", currentTimestamp, currentTimestamp, 10, CouponType.DINING, "Curry Discount", 30, "Curry Food Pic");
		//		//		
		//		//		//Create the CouponDao object which adds coupon to the mySQL database:
		//		CouponDao firstCouponDao = new CouponDao();
		//		//		
		//		//		//Add the Coupon to mySQL database using createCoupon:
		//		firstCouponDao.addCouponForACompany(coupon, 101);

		//TEST
		//if the connection works:
		//		try{
		//			Connection connection = JdbcUtils.getConnection();
		//			System.out.println("Connection successfully created.");
		//		}
		//		catch (SQLException e){
		//			System.out.println("FAILED TO CONNECT");
		//			e.printStackTrace();
		//		}

		//EXAMPLE FROM CLASS
		//..name -> takes the logic name of any enum and returns a string form of it
		//using .name is terrible, don't do it.

		//ANOTHER EXAMPLE FROM CLASS
		//VALUE OF checks the string value of the NAME of the enum (in the situation you didn't put anything
		//in parentices in the enum)
		//		CouponType couponType = CouponType.valueOf("TRAVEL");
		//		System.out.println(couponType.name());

	}
}
