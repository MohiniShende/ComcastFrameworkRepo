package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage {
	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountname")
	 private WebElement orgnameedt;
	public WebElement getOrgnameedt() {
		return orgnameedt;
	}
	public WebElement getSavebtn() {
		return savebtn;
	}
	@FindBy(name="button")
	private WebElement savebtn;
	@FindBy(name="industry")
	private WebElement industrydropdown;
	
	
	public void createOrg(String orgName) {
		orgnameedt.sendKeys(orgName);
		savebtn.click();
	}
	
	public void createOrg(String orgName,String  industry) {
		
		orgnameedt.sendKeys(orgName);
		Select sel= new Select(industrydropdown);
		sel.selectByVisibleText(industry);
		savebtn.click();
	}
}
