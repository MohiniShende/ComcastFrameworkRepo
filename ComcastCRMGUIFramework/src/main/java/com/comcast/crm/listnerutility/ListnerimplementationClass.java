package com.comcast.crm.listnerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webDriverUtility.UtilityClassObject;



public class ListnerimplementationClass implements ITestListener, ISuiteListener {
	
    //public ExtentReports report;
    public static ExtentReports report;
    public static ExtentTest test;// if u want to use this report in evry script make it static

	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
		// for back up
		String time = new Date().toString().replace(" ", "_").replace(":", " ");
		
		//spark report configuration
		//ExtentSparkReporter	spark= new ExtentSparkReporter("./AdvanceReport/report.html");
		// every execution it is going to generate a report with different name
		ExtentSparkReporter	spark= new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
				spark.config().setDocumentTitle("CRM Test Suite Results");
				spark.config().setReportName("CRM Report");
				spark.config().setTheme(Theme.DARK);
				
				//add envirnment  information and create test
				 report = new ExtentReports();
				report.attachReporter(spark);
				report.setSystemInfo("OS", "Windows-11");
				report.setSystemInfo("BROWSER", "CHROME-100");
	}

	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
		
		report.flush();
	}
	
	public void onTestStart(ITestResult result) {
		System.out.println("===="+result.getMethod().getMethodName()+"===start====");
	    test = report.createTest(result.getMethod().getMethodName());
	    UtilityClassObject.setTest(test);
		test.log(Status.INFO,result.getMethod().getMethodName()+"===started===");
		
		
	}

	
	public void onTestSuccess(ITestResult result) {
		System.out.println("===="+result.getMethod().getMethodName()+"===end====");
		test.log(Status.PASS,result.getMethod().getMethodName()+"===completed===");
	}

	
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
    //EventFiringWebDriver edriver= new 	EventFiringWebDriver();
		TakesScreenshot edriver= (TakesScreenshot) BaseClass.sdriver;
		String filepath = edriver.getScreenshotAs(OutputType.BASE64); 
		//step 2  use getscreenshotAs method to get file type of screnshot
		File srcfile =edriver.getScreenshotAs(OutputType.FILE);
		String time = new Date().toString().replace(" ", "_").replace(":", " ");
		test.addScreenCaptureFromBase64String(filepath, testname+"_"+time);// attach screenshot 
		test.log(Status.FAIL,result.getMethod().getMethodName()+"===failed===");
		//step3 store screen on local driver
		/*
		try {
			FileUtils.copyFile(srcfile, new File("./Screenshot/"+testname+"+"+time+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}

	
	

}
