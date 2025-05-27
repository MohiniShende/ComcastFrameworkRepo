package practice.test;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//extent report insrallation and implementation
public class SampleReportTest {
	ExtentReports report;
	@BeforeSuite
	public void configBS() {
		//spark report configuration
				ExtentSparkReporter spark= new ExtentSparkReporter("./AdvanceReport/report.html");
				spark.config().setDocumentTitle("CRM Test Suite Results");
				spark.config().setReportName("CRM Report");
				spark.config().setTheme(Theme.DARK);
				
				//add envirnment  information and create test
				 report = new ExtentReports();
				report.attachReporter(spark);
				report.setSystemInfo("OS", "Windows-11");
				report.setSystemInfo("BROWSER", "CHROME-100");
		
	}
	@AfterSuite
	public void configAS() {
		report.flush();
	}

	// attaching our screenshot into extent report
		@Test
		public void createContactTest() {
		
		WebDriver driver =new ChromeDriver();
		driver.get("http://localhost:8888");
		
		TakesScreenshot edriver= (TakesScreenshot)driver;
		String filepath = edriver.getScreenshotAs(OutputType.BASE64); // extent report will support base64 file
		
		ExtentTest test = report.createTest("Create contact test");
		
		
		
		test.log(Status.INFO,"login to app ");
		test.log(Status.INFO,"navigate to contact page"); 
		test.log(Status.INFO,"create contact ");
			if("HkFC".equals("HDFC")) {
				test.log(Status.PASS,"Contact is created");
			}
			else {
				//test.log(Status.FAIL,"Contact is not  created");
				test.addScreenCaptureFromBase64String(filepath, "errorFile");
			}
		driver.close();
			
		
		

	}
		
		@Test
		public void createContactWithOrg() {
		
		
		ExtentTest test = report.createTest("createContactWithOrg");
		
		
		
		test.log(Status.INFO,"login to app ");
		test.log(Status.INFO,"navigate to contact page"); 
		test.log(Status.INFO,"create contact ");
			if("HDFC".equals("HDFC")) {
				test.log(Status.PASS,"Contact is created");
			}
			else {
				test.log(Status.FAIL,"Contact is not  created");
			}
		
			
			
		

	}

		@Test
		public void createContactWithPhoneNumber() {
		
		
		ExtentTest test = report.createTest("createContactWithPhoneNumber");
		
		
		
		test.log(Status.INFO,"login to app ");
		test.log(Status.INFO,"navigate to contact page"); 
		test.log(Status.INFO,"create contact ");
			if("HDFC".equals("HDFC")) {
				test.log(Status.PASS,"Contact is created");
			}
			else {
				test.log(Status.FAIL,"Contact is not  created");
			}
		
			
		
		

	}


		

		

}
