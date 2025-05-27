package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.fileUtility;
import com.comcast.crm.generic.webDriverUtility.JavaUtility;

public class CreatecontactWithSupportDateTest {

	public static void main(String[] args) throws Throwable {
		fileUtility flib= new fileUtility();
		ExcelUtility elib=  new ExcelUtility();
		JavaUtility jlib= new JavaUtility();
	      String URL = flib.getDataFromPropertiesFile("url");
	      String BROWSER = flib.getDataFromPropertiesFile("browser");
	      String USERNAME = flib.getDataFromPropertiesFile("username");
	      String PASSWORD =flib.getDataFromPropertiesFile("password");
	      
//		// read common data from properties file
//		FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
//		 Properties pobj= new Properties();
//	     pobj.load(fis);
//	      String URL = pobj .getProperty("url");
//	      String BROWSER = pobj .getProperty("browser");
//	      String USERNAME = pobj .getProperty("username");
//	      String PASSWORD = pobj .getProperty("password");
	      
	      
	      //generate random number
	   // Random is a class which is present in java.util.package
			//Random random = new Random();
			//int randomint = random.nextInt(1000);
			// it will display the random number with in this range
			// how many time ur executing that many times it will generate the random number
		
	     // Scanner sc= new Scanner(System.in);
	      //System.out.println("enter the browser");
	      // String browser= sc.next();
	      
	      // read testscript data from excel file
	      
//	      FileInputStream fistream =new FileInputStream("./testscriptData/testscript.xlsx");
//	  	Workbook wb = WorkbookFactory.create(fistream);
//	  	Sheet sheet = wb.getSheet("Contact");
//	  	Row row = sheet.getRow(1);
	    // insted of get string we will use tostring all the data will be converted in string, even though we have a numeric data
	        
	  	//String lastname = row.getCell(2).toString() +randomint ;
	      String lastname  =  elib.getDataFromExcel("Contact", 1, 2)+jlib.getRandomNumber();// concatinating random number, after reading that Twiter00_at the same time i am going to concat this random number integer
	 // concatinating random number, after reading that Twiter00_at the same time i am going to concat this random number integer
	 		//wb.close();
	      
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
		driver.findElement(By.linkText("Contacts")).click();
		
		//Click on (+)create "organization" button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//step 4: enter  all the details and create new organization
		
		
		// how to capture current date in specific  format
//   Date dateobj = new Date();
//  // System.out.println(dateobj.toString());// it will capture today date and time
//    SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
//    String satrtactDate = sim.format(dateobj);
//   
//    //SimpleDateFormat sim1 = new SimpleDateFormat("yyyy-mm-dd");
//   Calendar cal = sim.getCalendar();
//   //cal.add(Calendar.DAY_OF_MONTH,-30);//before 30 date 2025-04-13
//   cal.add(Calendar.DAY_OF_MONTH,30);// after adding 30 days
//   String endafterdatereuries = sim.format(cal.getTime());//before 30 date 2025-06-12
//  
		
		String satrtactDate = jlib.getSystemDateyyyyMMdd();
		String endafterdatereuries = jlib.getRequiredDateyyyMMdd(30);
	
   // last name webelement
       driver.findElement(By.name("lastname")).sendKeys(lastname);
       // support date webelement
    // start date webelement
    		driver.findElement(By.name("support_start_date")).clear();
    		driver.findElement(By.name("support_start_date")).sendKeys(satrtactDate);
    		
    		// end date webelement
    		driver.findElement(By.name("support_end_date")).clear();
    		driver.findElement(By.name("support_end_date")).sendKeys(endafterdatereuries);
       
		
		
		//step 6: save webelement
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		
	 //verify start date info expected result
		 String actstartdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
			
		 if(satrtactDate.equals(actstartdate)) {
				System.out.println(actstartdate +"is created  --- pass");
			}

			else {
				System.out.println(actstartdate + "is not created--- fail");
			} 
		 
		 
		 //verify end date info expected result
		 String actenddate = driver.findElement(By.id("dtlview_Support End Date")).getText();
			
		 if(endafterdatereuries.equals(actenddate)) {
				System.out.println(actenddate +"is created  --- pass");
			}

			else {
				System.out.println(actenddate + "is not created--- fail");
			} 
		 
		 //steps- 5:logout
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		 
		 
		driver.close();
	}






	}


