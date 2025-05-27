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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWithIndustries2 {
//TEST CASE-2 crate organization with industery and type
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
			  	Row row = sheet.getRow(4);
			    // insted of get string we will use tostring all the data will be converted in string, even though we have a numeric data
			 		String orgname = row.getCell(2).toString() +randomint ;// concatinating random number, after reading that Twiter00_at the same time i am going to concat this random number integer
			 		String industry = row.getCell(3).toString()  ;// concatinating random number, after reading that Twiter00_at the same time i am going to concat this random number integer
			 		String type = row.getCell(4).toString() ;// concatinating random number, after reading that Twiter00_at the same time i am going to concat this random number integer
			        
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
				//step 5: enter all the details and create new organization
				
				// industry web element
				   WebElement wbele = driver.findElement(By.name("industry"));
				   Select s= new Select(wbele);
					s.selectByVisibleText(industry);
				   // type webelement
				  WebElement typeweb = driver.findElement(By.name("accounttype"));
				Select s1= new Select(typeweb);
				s1.selectByVisibleText(type);
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				 
				 //Tset case===2
				//vrify  the dropdown industries and type
				//verify industry info expected result 
				String actindustries = driver.findElement(By.id("dtlview_Industry")).getText();
				if(actindustries.equals(industry)) {
					System.out.println(industry+" information is created===pass");
				}
				else {
					System.out.println(industry+"information is not created===fail");
				}
				
				
				String acttype = driver.findElement(By.id("dtlview_Type")).getText();
				if(acttype.equals(type)) {
					System.out.println(type+" information is created===pass");
				}
				else {
					System.out.println(type+"information is not created===fail");
				}
				
				
				 
				 //steps- 5:logout
					Actions act = new Actions(driver);
					act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
					driver.findElement(By.linkText("Sign Out")).click();
				 
				driver.close();
			}
		



	}


