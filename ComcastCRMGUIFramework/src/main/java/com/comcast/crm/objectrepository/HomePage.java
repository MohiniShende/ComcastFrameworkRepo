package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
		WebDriver driver;// global variable
		public HomePage(WebDriver driver) { // this constructor will take care of loading the object
			this.driver=driver;// local variable
			PageFactory.initElements(driver, this);// this will refered currnt object
			
		}
	
	@FindBy(linkText = "Organizations")
	private WebElement orglink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactlink;
	
	@FindBy(linkText = "Campaigns")
	private WebElement Campaignlink;
	
	@FindBy(linkText = "More")
	private WebElement  morelink;
	
	@FindBy(xpath= "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg ;
	
	@FindBy(linkText= "Sign Out")
	private WebElement signoutlink ;
	
	
	
	
	
	
	public WebElement getOrglink() {
		return orglink;
	}

	public WebElement getContactlink() {
		return contactlink;
	}
	
	
	public WebElement getCampaignlink() {
		return Campaignlink;
	}
	public void navigateToCampaginpage() {
	     Actions act = new Actions(driver);
	     act.moveToElement(morelink).perform();
	     Campaignlink.click();
	}
	public void logout() {
		Actions act = new Actions(driver);
		act.moveToElement(adminImg).perform();
		signoutlink.click();
	}
}
