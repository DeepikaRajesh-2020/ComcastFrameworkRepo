package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Products {
	@FindBy(xpath="//img[@alt=\"Create Product...\"]")
	private WebElement createProductbtn;
	

	@FindBy(name="search_text")
	private WebElement ele2;
	
	@FindBy(xpath="//input[@name='save&merge']")
	private WebElement ele3;


}
