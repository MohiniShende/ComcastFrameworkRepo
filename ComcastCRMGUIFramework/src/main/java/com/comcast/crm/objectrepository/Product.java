package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Product {
	@FindBy(xpath="//img[@alt='Create Product...']")
	private WebElement createProducrImgBtn;	

}
