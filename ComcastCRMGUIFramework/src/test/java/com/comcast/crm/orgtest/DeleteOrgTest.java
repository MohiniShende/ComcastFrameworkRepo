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
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.fileUtility;
import com.comcast.crm.generic.webDriverUtility.JavaUtility;
import com.comcast.crm.generic.webDriverUtility.WebDriverUtility;
import com.comcast.crm.objectrepository.CreateNewOrganizationPage;
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.LoginPage;
import com.comcast.crm.objectrepository.OrganizationInfoPage;
import com.comcast.crm.objectrepository.OrganizationsPage;

public class DeleteOrgTest {
//TESTCASE -1 create organization 
	public static void main(String[] args) throws Throwable {
		// CREATE OBJECT
		
		fileUtility flib= new fileUtility();
		ExcelUtility elib= new ExcelUtility();
		JavaUtility  jlib = new JavaUtility();
		WebDriverUtility wlib= new 	WebDriverUtility();
		// read common data from properties file
				//FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
				 //Properties pobj= new Properties();
			     //pobj.load(fis);
			      //String URL = pobj .getProperty("url");
			     // String BROWSER = pobj .getProperty("browser");
			      //String USERNAME = pobj .getProperty("username");
			      //String PASSWORD = pobj .getProperty("password");
		String URL = flib.getDataFromPropertiesFile("url");
	      String BROWSER = flib.getDataFromPropertiesFile("browser");
	      String USERNAME = flib.getDataFromPropertiesFile("username");
	      String PASSWORD =flib.getDataFromPropertiesFile("password");
			      
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
			      
			     // FileInputStream fistream =new FileInputStream("./testscriptData/testscript.xlsx");
			  	//Workbook wb = WorkbookFactory.create(fistream);
			  	//Sheet sheet = wb.getSheet("org");
			  	//Row row = sheet.getRow(1);
			    // insted of get string we will use tostring all the data will be converted in string, even though we have a numeric data
			 		//String orgname = row.getCell(2).toString() +randomint ;// concatinating random number, after reading that Twiter00_at the same time i am going to concat this random number integer
	    String orgname = elib.getDataFromExcel("org", 10, 2)+jlib.getRandomNumber() ;
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
			 //LOGIN TO APP
				//driver.manage().window().maximize();
				//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
				//driver.get(URL);
				//LoginPage lp = PageFactory.initElements(driver, LoginPage.class);// this is object creation , no need worry about initializaton
				LoginPage lp= new LoginPage(driver);// calling constructor

				// rule -5 object utilization
				//lp.getUsernameEdt().sendKeys("admin");
				//lp.getPasswordedt().sendKeys("1999");// u can utilize element via getter method or action
				//lp.getLoginbtn().click();
				lp.loginToapp(USERNAME, PASSWORD);
				
				//STEP 2 - NAVIGATE TO ORGANIZATION MODULE
				HomePage hp= new HomePage(driver);
				hp.getOrglink().click();
				//create organization
				OrganizationsPage cmp= new OrganizationsPage(driver);
				cmp.getCreateNewOrgBtn().click();
				
				// STEP 4 ENTER ALL THE DETAILS AND CREATE NEW ORGANIZATION
				CreateNewOrganizationPage cnop= new CreateNewOrganizationPage(driver);
				cnop.createOrg(orgname);
				
				
				//STEP 5 VERIFY HEADER MAG EXPECTED RESULT
				OrganizationInfoPage oip= new OrganizationInfoPage(driver);
				String actOrgName = oip.getHeadermsg().getText();
				if(actOrgName.contains(orgname)) {
					System.out.print(orgname+ " name is verified ===pass");
				}
				else {
					System.out.print(orgname+ " name is not  verified ===fail");
				}
	
				
	
				// go back to organiation page
				
				hp.getOrglink().click();
				
				//search for organization
				cmp.getSearchEdt().sendKeys(orgname);
				wlib.select(cmp.getSearchDD(),"Organization Name");// in organization name
				cmp.getSubmitbtn().click();
				//IN DYNAMIC WEBTABLE SELECT , this data constructing inthe runtime
				//driver.findElement(By.xpath("//a[text()='Deepak_']/../../td[8]/a[text()='del']")).click();
				driver.findElement(By.xpath("//a[text()='"+orgname+"']/../../td[8]/a[text()='del']")).click();
				
				
				
				// go back to Organization page 
				// search for Organization
				//in dynamic webtable select and delete all
				
				//STEP 6 - LOGOUT
				hp.logout();
				
				//driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				//driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				//driver.findElement(By.id("submitButton")).click();
				
				// navigate to organization module,Click on create "organization" button
				//driver.findElement(By.linkText("Organizations")).click();
				
				//Click on (+)create "organization" button
				//driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
				//step 4: enter  all the details and create new organization
				//driver.findElement(By.name("accountname")).sendKeys(orgname);
				//driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				// TEST CASE -01
			/*	//verify header msg expected result
				String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();				//verify orgname info expected result
				if(headerinfo.contains(orgname)) {
					System.out.println(orgname+"is created");
				}
				else {
					System.out.println(orgname+"is not created");
				}
				//verify header orgname info expected result
				 String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
				
				 if(actOrgName.contains("orgname")) {
						System.out.println(orgname+"is created");
					}
	
					else {
						System.out.println(orgname+"is not created");
					}
	
				 
				 //steps- 5:logout
				Actions act = new Actions(driver);
				act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
				//driver.findElement(By.linkText("Sign Out")).click();
				 
				 */
				driver.close();
			}
		



	}


