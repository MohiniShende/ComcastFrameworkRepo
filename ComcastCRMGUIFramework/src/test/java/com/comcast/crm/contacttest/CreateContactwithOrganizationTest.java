package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.fileUtility;
import com.comcast.crm.generic.webDriverUtility.JavaUtility;
import com.comcast.crm.generic.webDriverUtility.WebDriverUtility;

public class CreateContactwithOrganizationTest {
	
	public static void main(String[] args) throws Throwable {
		fileUtility flib= new fileUtility();
		ExcelUtility elib= new ExcelUtility();
		JavaUtility  jlib = new JavaUtility();
		WebDriverUtility wlib= new WebDriverUtility();
		
		
	// read common data from properties file
//	FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
//	 Properties pobj= new Properties();
//     pobj.load(fis);
//      String URL = pobj .getProperty("url");
//      String BROWSER = pobj .getProperty("browser");
//      String USERNAME = pobj .getProperty("username");
//      String PASSWORD = pobj .getProperty("password");
      
      
		String URL = flib.getDataFromPropertiesFile("url");
     String BROWSER = flib.getDataFromPropertiesFile("browser");
     String USERNAME = flib.getDataFromPropertiesFile("username");     
     String PASSWORD = flib.getDataFromPropertiesFile("password");
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
      
//      FileInputStream fistream =new FileInputStream("./testscriptData/testscript.xlsx");
//  	Workbook wb = WorkbookFactory.create(fistream);
//  	Sheet sheet = wb.getSheet("Contact");
//  	Row row = sheet.getRow(7);
    // insted of get string we will use tostring all the data will be converted in string, even though we have a numeric data
 		//String orgname = getCell(2).toString() +randomint ;// concatinating random number, after reading that Twiter00_at the same time i am going to concat this random number integer
 		//String contactLastName = row.getCell(3).toString()  ;// concatinating random number, after reading that Twiter00_at the same time i am going to concat this random number integer
 		String orgname = elib.getDataFromExcel("Contact",7,2)+jlib.getRandomNumber() ;// concatinating random number, after reading that Twiter00_at the same time i am going to concat this random number integer
 		String contactLastName = elib.getDataFromExcel("Contact",7,3) ;// concatinating random number, after reading that Twiter00_at the same time i am going to concat this random number integer
 		
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
	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	wlib.waitForPageToLoad(driver);
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
	
	//step 5: enter all the details and create new organization
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	
	
	// navigate to contact module
	driver.findElement(By.linkText("Contacts")).click();
	driver.findElement(By.xpath("//img[@title'Create Contact...']")).click();
	
	driver.findElement(By.name("lastname")).sendKeys(contactLastName);
	driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
	
	//switch to child window 
// Set<String>  set=driver.getWindowHandles();
//      Iterator<String>  iterator =set.iterator(); // capture the set data from collection
//      while(iterator.hasNext()) {
//    	String  windowID = iterator.next();
//    	  driver.switchTo().window(windowID);
//    	  
//    	String  acturl =driver.getCurrentUrl();
//    	if(acturl.contains("module=Accounts")) {
//    		break;
//    	}
//      }
      
	wlib.swithToTabOnURL(driver, "module=Accounts");
	
	
	driver.findElement(By.name("search_text")).sendKeys(orgname);
	driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
	
	
	//switch to parent window
	wlib.swithToTabOnURL(driver, "Contacts=Accounts");
	
//	Set<String>  set1=driver.getWindowHandles();
//    Iterator<String>  iterator1 =set1.iterator(); // capture the set data from collection
//    while(iterator1.hasNext()) {
//  	String  windowID = iterator1.next();
//  	  driver.switchTo().window(windowID);
//  	  
//  	String  acturl =driver.getCurrentUrl();
//  	if(acturl.contains("Contacts&action")) {
//  		break;
//  	}
//    }
	
	driver.findElement(By.xpath("//input[@title='Save [Alt+s]'")).click();
	 
	
	
	//verify header orgname info expected result in contact check organization
	
	String headerinfo = driver.findElement(By.id("//span[@class='dvHeaderText']")).getText();
	
	 if(headerinfo.equals(contactLastName)) {
			System.out.println(contactLastName +"is created  --- pass");
		}

		else {
			System.out.println(contactLastName + "is not created--- fail");
		}
	 
	 
	
	
	
	 String actorgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
	System.out.println(actorgname);
	 if(actorgname.equals(orgname)) {
			System.out.println(orgname +"is created  --- pass");
		}

		else {
			System.out.println(orgname + "is not created--- fail");
		}
	 
	 

	
	
	 
	 //steps- 5:logout
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
	
	driver.close();
	
	
	
	
	
	
}
}