package com.comcast.crm.orgtest;

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

public class HowToReadNumericDataFromExcel {
//TEST CASE -3 CREATE organization with phone number
	public static void main(String[] args) throws Throwable {
		// read common data from properties file
				FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
				 Properties pobj= new Properties();
			     pobj.load(fis);
			      String URL = pobj .getProperty("url");
			      String BROWSER = pobj .getProperty("browser");
			      String USERNAME = pobj .getProperty("username");
			      String PASSWORD = pobj .getProperty("password");
			      
			      
			      //generate random number
			   // Random is a class which is present in java.util.package
					Random random = new Random();
					int randomint = random.nextInt(1000);
					// it will display the random number with in this range
					// how many time ur executing that many times it will generate the random number
				
			     // Scanner sc= new Scanner(System.in);
			      //System.out.println("enter the browser");
			      // String browser= sc.next();
			      
			      // read testscript data from excel file
			      
			      FileInputStream fistream =new FileInputStream("./testscriptData/testscript.xlsx");
			  	Workbook wb = WorkbookFactory.create(fistream);
			  	Sheet sheet = wb.getSheet("org");
			  	Row row = sheet.getRow(7);
			    // insted of get string we will use tostring all the data will be converted in string, even though we have a numeric data
			        
			  	String orgname = row.getCell(2).toString() +randomint ;// concatinating random number, after reading that Twiter00_at the same time i am going to concat this random number integer
			  	String phonenumber = row.getCell(3).getStringCellValue() ;// concatinating random number, after reading that Twiter00_at the same time i am going to concat this random number integer
			 		wb.close();
			      
			       WebDriver driver= null; //declaration   
			       // which browser i want to take in runtime i dont know
			       //System.out.println(BROWSER);
			       // driver object referance it will behave differently
			       // based on user input driver object will take a dicesion in runtime 
			      // if(browser.equals("chrome"))
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
			    	   
			    	   //step 1:  LOGIN TO APP
			       
			       // i am able to read the data from property file
			     // System.out.println(BROWSER);
			     // System.out.println(URL);
			      
			     // intead of hardcoding the data i am getting the data from property file
			 
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
				driver.get(URL);
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
				// navigate to organization module,Click on create "organization" button
				driver.findElement(By.linkText("Organizations")).click();
				
				//Click on (+)create "organization" button
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
				//step 4: enter  all the details and create new organization
				driver.findElement(By.name("accountname")).sendKeys(orgname);
				
				//step 5: phone number webelement 
				driver.findElement(By.id("phone")).sendKeys(phonenumber);
				//step 6: save webelement
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				// TEST CASE -01
				
				
				
				//verify header phone number info expected result
				 String actphoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
				
				 if(actphoneNumber.equals(phonenumber)) {
						System.out.println(phonenumber +"is created  --- pass");
					}
	
					else {
						System.out.println(phonenumber + "is not created--- fail");
					}
	
				 
				 //steps- 5:logout
				Actions act = new Actions(driver);
				act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				 
				 
				driver.close();
			}
		



	}


