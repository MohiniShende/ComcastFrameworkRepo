package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.fileUtility;
import com.comcast.crm.generic.webDriverUtility.JavaUtility;
import com.comcast.crm.generic.webDriverUtility.UtilityClassObject;
import com.comcast.crm.listnerutility.ListnerimplementationClass;
import com.comcast.crm.objectrepository.ContactPage;
import com.comcast.crm.objectrepository.CreatingNewContactPage;
import com.comcast.crm.objectrepository.HomePage;

public class CreatecontactTest extends BaseClass {
 
	@Test(groups= "smokeTest")
	public void createContactTest() throws Throwable {
		ListnerimplementationClass.test.log(Status.INFO,"read data from excel");
		//create object 
		//fileUtility flib= new fileUtility();
		//ExcelUtility elib=  new ExcelUtility();
		//JavaUtility jlib= new JavaUtility();
	    //  String URL = flib.getDataFromPropertiesFile("url");
	     // String BROWSER = flib.getDataFromPropertiesFile("browser");
	      //String USERNAME = flib.getDataFromPropertiesFile("username");
	      //String PASSWORD =flib.getDataFromPropertiesFile("password");
	      
	      
	      //generate random number
	   // Random is a class which is present in java.util.package
			//Random random = new Random();
			//int randomint = random.nextInt(1000);
			
			// read testscript data from excel file
			String lastname  =  elib.getDataFromExcel("Contact", 1, 2)+jlib.getRandomNumber();
	      
	      
	      
	      // WebDriver driver= null; //declaration   
	       // which browser i want to take in runtime i dont know
	       //System.out.println(BROWSER);
	       // driver object referance it will behave differently
	       // based on user input driver object will take a dicesion in runtime 
	      // if(browser.equals("chrome"))
	    	/*   if(BROWSER.equals("chrome"))
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
	    	*/   
	    	   //step 1:  LOGIN TO APP
	       
	       // i am able to read the data from property file
	     // System.out.println(BROWSER);
	     // System.out.println(URL);
	      
	     // intead of hardcoding the data i am getting the data from property file
	 
		
		
		// navigate to organization module,Click on create "organization" button
		//driver.findElement(By.linkText("Contacts")).click();
			
			
			UtilityClassObject.getTest().log(Status.INFO,"navigate to org page");
			HomePage hp = new HomePage(driver);
			hp.getContactlink().click();
		
		//Click on (+)create "contact" button
			UtilityClassObject.getTest().log(Status.INFO,"navigate to contact page");
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewOrgBtn().click();
		
		
		
		
		
		//step 4: enter  all the details and create new organization
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		
		
		
		
		
		
		//step 6: save webelement
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		// TEST CASE -01
		
		
		
		//verify header phone number info expected result
		 String actcontact = driver.findElement(By.id("dtlview_Last Name")).getText();
		SoftAssert softassert= new SoftAssert();
		 
		softassert.assertEquals(actcontact, lastname);
		 
		 String actHeader =cp.getHeaderMsg().getText(); 
		 boolean status = actHeader.contains(lastname);
		 Assert.assertEquals(status, true);
		 
		 
		 //steps- 5:logout
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		 
		 
		
	}






	}


