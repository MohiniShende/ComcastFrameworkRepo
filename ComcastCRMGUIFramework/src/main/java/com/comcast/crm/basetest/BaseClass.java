package com.comcast.crm.basetest;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseUtility.DataBaseUtility;
import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.fileUtility;
import com.comcast.crm.generic.webDriverUtility.JavaUtility;
import com.comcast.crm.generic.webDriverUtility.UtilityClassObject;
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.LoginPage;

public class BaseClass {
	//craete object
	public DataBaseUtility dlib= new DataBaseUtility();
	public fileUtility flib= new fileUtility();
	public ExcelUtility elib=  new ExcelUtility();
	public JavaUtility jlib= new JavaUtility();
	public  WebDriver driver= null;
	public static WebDriver sdriver= null;
  //  public ExtentSparkReporter spark;
    public ExtentReports report;
	
	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	public void configBS() throws Throwable {
		
		System.out.println("===connect to DB, Report config");
		dlib.getDbconnection();
		
		/*//spark report configuration
		spark= new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		//add envirnment  information and create test
		 report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-11");
		report.setSystemInfo("BROWSER", "CHROME-100");
		*/
	}
	
	
	
	@Parameters("BROWSER")
	@BeforeClass(groups = {"smokeTest","regressionTest"})
	public void configBC(String browser) throws Throwable {
		System.out.println("Launch Browser");
		String BROWSER=browser;
		//String BROWSER = flib.getDataFromPropertiesFile("browser");
		
	
		
		if(BROWSER.equals("chrome"))
	       {
	    	  driver= new ChromeDriver(); 
	       }
	       //else if(browser.equals("firefox")) {
	    	   else if(BROWSER.equals("firefox")) {
	    	   driver= new FirefoxDriver();
	       }
	       //else if(browser.equals("edge")) {
	    	   else if(BROWSER.equals("edge")) {
	    	   driver= new EdgeDriver();
	       }
	       //if user forget to enter the data in runtime
	       else {
	    	   driver= new ChromeDriver();
	       }
		sdriver=driver;
		UtilityClassObject.setDrievr(driver);
		
	}
		@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void configBM() throws Throwable {
		System.out.println("Login ");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		LoginPage lp = new LoginPage(driver);
		lp.loginToapp(URL, USERNAAME, PASSWORD);
	}
	
	
	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void configAM() {
		System.out.println("Logout");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}
	
	@AfterClass(groups = {"smokeTest","regressionTest"})
	public void configAC()  {
		System.out.println("Closed the Browser");
		driver.quit();
		
		
	}
	@AfterSuite(groups = {"smokeTest","regressionTest"})
	public void configAS() throws SQLException {
		System.out.println("===Closed DB ,  Report backup");
		dlib.closeDbconnection();
		report.flush();
	}
	
	

}
