package com.comcast.crm.generic.webDriverUtility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	public void waitForElementPresent(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	public void swithToTabOnTitle(WebDriver driver, String partialTitle) {
		//switch to parent window
		
		Set<String>  set1=driver.getWindowHandles();
	    Iterator<String>  iterator1 =set1.iterator(); // capture the set data from collection
	    while(iterator1.hasNext()) {
	  	String  windowID = iterator1.next();
	  	  driver.switchTo().window(windowID);
	  	  
	  	String  acturl =driver.getTitle();
	  	if(acturl.contains(partialTitle)) {
	  		break;
	  	}
	    }
		
	}
	public void swithToTabOnURL(WebDriver driver, String partialTitle) {
		//switch to parent window
		
		Set<String>  set1=driver.getWindowHandles();
	    Iterator<String>  iterator1 =set1.iterator(); // capture the set data from collection
	    while(iterator1.hasNext()) {
	  	String  windowID = iterator1.next();
	  	  driver.switchTo().window(windowID);
	  	  
	  	String  acturl =driver.getTitle();
	  	if(acturl.contains("module=Accounts")) {
	  		break;
	  	}
	    }
		
	}
	
	
	// swicth to frame
	public void switchtoFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
		
	}
	public void switchtoFrame(WebDriver driver, String nameID) {
		driver.switchTo().frame(nameID);
		
	}
	
	public void switchtoFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
		
	}
	
	public void switchtoAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
		
	}
	public void select(WebElement element, String text) {// select the value from dropdown
		Select se= new Select(element);
		se.selectByVisibleText(text);
		
		
	}
	public void select(WebElement element, int index) {// select the value from dropdown
		Select se= new Select(element);
		se.selectByIndex(index);
		
		
	}
	public void mousemomentOnElement(WebDriver driver , WebElement  element) {
		Actions  act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	 public void  doubleclick(WebDriver driver , WebElement  element) {
		 Actions  act = new Actions(driver);
		 act.doubleClick(element).perform();
	 }
	
		
	}
	


