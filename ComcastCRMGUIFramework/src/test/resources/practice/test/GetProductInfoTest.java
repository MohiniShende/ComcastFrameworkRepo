package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileUtility.ExcelUtility;

public class GetProductInfoTest {
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName,String productName) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://amazon.in");
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
		
		//capture product info
		String x= "//span[text()='"+productName+"']/../../../../div[3]/div[1]/div/div[1]/div[1]/a/span/span[2]/span[2]";
		String price= driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		driver.quit();
	}
	@DataProvider
	public  Object[] [] getData() throws Throwable{
		ExcelUtility elib= new ExcelUtility ();
		int rowcount = elib.getRowCount("Product");
		Object [][] objearry= new Object [rowcount][2];
		for(int i=0;i<rowcount;i++) {
			
		
		objearry[i][0]=elib.getDataFromExcel("Product", i+1, 0);
		objearry[i][1]=elib.getDataFromExcel("Product", i+1, 1);
		}
		
		
		
		
		return objearry;
	}

}



