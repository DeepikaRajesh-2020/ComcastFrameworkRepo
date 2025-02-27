package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContact {

	WebDriver driver;

	public CreateNewContact(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement createcontactbtn;

	@FindBy(name = "lastname")
	private WebElement lastnameedt;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;

	@FindBy(name = "support_start_date")
	private WebElement supportstartdateedt;

	@FindBy(name = "support_end_date")
	private WebElement supportenddateedt;

	public WebElement getCreatecontactbtn() {
		return createcontactbtn;
	}

	public WebElement getLastnameedt() {
		return lastnameedt;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}

	public WebElement getSupportstartdateedt() {
		return supportstartdateedt;
	}

	public WebElement getSupportenddateedt() {
		return supportenddateedt;
	}

}
