package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Opportunities {
	
	@FindBy(xpath="//img[@title='Create Opportunity...']")
	private WebElement opportunitieslnk;
	
	@FindBy(name="search_text")
	private WebElement ele2;

}
