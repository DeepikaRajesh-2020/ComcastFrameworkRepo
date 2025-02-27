package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * @author Deepika
 * 
 */
public class HomePage {
	
WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getOrglnk() {
		return orglnk;
	}

	public WebElement getContactlnk() {
		return contactlnk;
	}

	@FindBy(linkText = "Organizations")
	private WebElement orglnk;

	@FindBy(linkText = "Product")
	private WebElement productlnk;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactlnk;
	
	@FindBy(linkText = "Campaigns")
	private WebElement compaignlnk;

	@FindBy(linkText = "More")
	private WebElement morelnk;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutlnk;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	public void navigateToCampaignPage()
	{
		Actions act=new Actions(driver);
		act.moveToElement(morelnk).perform();
		compaignlnk.click();
	}

	public WebElement getMorelnk() {
		return morelnk;
	}
	
	public void logout()
	{
		Actions act=new Actions(driver);
		act.moveToElement(adminImg).perform();
		signOutlnk.click();
	}

}
