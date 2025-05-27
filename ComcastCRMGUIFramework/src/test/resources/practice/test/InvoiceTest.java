package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
//@Listeners(com.comcast.crm.listnerutility.ListnerimplementationClass.class)
public class InvoiceTest  extends BaseClass{
	@Test(retryAnalyzer = com.comcast.crm.listnerutility.RetryListenerImplementation.class)
	public void activateSim() {
		System.out.println("execute createInvoiceTest");
		//String var= driver.getTitle();
		//System.out.println(var);
		
		String actTile= driver.getTitle();
	   //Assert.assertEquals(actTile, "Login");
	   //Assert.assertEquals("", "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
		
	}
	/*
	@Test
	public void createInvoicewithContactTest() {
		System.out.println("execute createInvoicewithContactTest");
		System.out.println("execute ");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
*/
}
