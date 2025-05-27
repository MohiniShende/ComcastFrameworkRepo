package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webDriverUtility.WebDriverUtility;

public class LoginPage extends WebDriverUtility {
	WebDriver driver;// global variable
	public LoginPage(WebDriver driver) { // this constructor will take care of loading the object
		this.driver=driver;// local variable
		PageFactory.initElements(driver, this);// this will refered currnt object
		
	}
	//rule -1  create a seperate java class
	//rule-2 object creation 
	@FindBy(name="user_name")
	 private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordedt ;
	
	@FindBy(id="submitButton")
	private WebElement loginbtn;

	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordedt() {
		return passwordedt;
	}

	public WebElement getLoginbtn() {
		return loginbtn;
	}
	
	// rule -3 Object Initialization done in test script not in pom class
	
	//rule -4 object encapsulation
	
	// rule -5 object utilization
	
	
	// provide action
	// business method , this method u can not use for another application , used for specific method
	public void loginToapp(String url,String username,String password) {
		waitForPageToLoad(driver);// when u extends no need to create object for WebDriver utility just directly call method
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdt.sendKeys("admin");
		passwordedt.sendKeys("1999");
		loginbtn.click();
		
	}
	 
	
	
	

}
