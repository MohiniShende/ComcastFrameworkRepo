package com.crm.generic.baseUitility;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass1 {
	@BeforeSuite
	public void configBS() {
		System.out.println("===connect to DB, Report config");
	}
	
	
	
	@BeforeClass
	public void configBC() {
		System.out.println("Launch Browser");
	}
	
	@BeforeMethod
	public void configBM() {
		System.out.println("Login ");
	}
	
	
	@AfterMethod
	public void configAM() {
		System.out.println("Logout");
	}
	
	@AfterClass
	public void configAC() {
		System.out.println("Closed the Browser");
	}
	@AfterSuite
	public void configAS() {
		System.out.println("===Closed DB ,  Report backup");
	}

}
