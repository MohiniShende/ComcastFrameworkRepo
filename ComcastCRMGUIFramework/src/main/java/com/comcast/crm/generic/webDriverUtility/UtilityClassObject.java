package com.comcast.crm.generic.webDriverUtility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
/// it is used to  share my static variable in multiple thread in case of parallel execution
public class UtilityClassObject {
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public static ExtentTest getTest() {
		return test.get();
		
	}
	public static void  setTest(ExtentTest actTest) {
	 test.set(actTest);
		
	}
	
	public static WebDriver getDriver() {
		return driver.get();
		
	}
	public static void  setDrievr(WebDriver actDriver) {
		driver.set(actDriver);
		
	}

}
