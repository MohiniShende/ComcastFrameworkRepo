package practice.test;



import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Test;

public class SampleTestForScreenshot {
	@Test
	
	public void amazonTest() {
		WebDriver driver = new ChromeDriver();
		driver.get("http://amzon.com");
		
		
		//step 1 create an object to EventFiring webdriver
		
		EventFiringWebDriver edriver= new 	EventFiringWebDriver(driver) ;
		
		//step 2  use getscreenshotAs method to get file type of screnshot
		File srcfile =edriver.getScreenshotAs(OutputType.FILE);
		
		
		//step3 store screen on local driver
		
		FileUtils.copyFile(srcfile, new File("./Screenshot/test.png"));
		
	}

}
