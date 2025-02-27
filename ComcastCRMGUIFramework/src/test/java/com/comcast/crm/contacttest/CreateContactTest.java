package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.HomePage;

public class CreateContactTest extends BaseClass {

	@Test
	public void createContactTest() throws Exception {

		// read testscript data from Excel file
		String lastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();

		// step 2:navigate to contact
		HomePage hp = new HomePage(driver);
		hp.getContactlnk().click();

		// step 3:Create new contact
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// step 4:enter all details & create new Contact
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// verify Header phonenumber info Expected Result
		String actlastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actlastName.equals(lastName)) {
			System.out.println(lastName + " is Created===PASS");
		} else {
			System.out.println(lastName + " is not Created===FAIL");
		}

	}

	@Test
	public void createContactWithSupportDateTest() throws Throwable {
		// read testscript data from Excel file
		String lastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();
		// step 2:navigate to Contacts
		driver.findElement(By.linkText("Contacts")).click();

		// step 3:Create new Contacts
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// step 4:enter all details & create new Contact
		String startDate = jlib.getSystemDateYYYYMMDD();
		String endDate = jlib.getRequiredDateYYYYMMDD(30);

		driver.findElement(By.name("lastname")).sendKeys(lastName);
		// locator for start Date
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);

		// locator for End Date
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify Entered Start Date

		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if (actStartDate.equals(startDate)) {
			System.out.println(startDate + " Information is Verified===PASS");
		} else {
			System.out.println(startDate + " Information is Verified===FAIL");
		}
		// Verify Entered End Date

		String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if (actEndDate.equals(endDate)) {
			System.out.println(endDate + " Information is Verified===PASS");
		} else {
			System.out.println(endDate + " Information is Verified===FAIL");
		}

	}

	@Test
	public void createContactWithOrgTest() throws Throwable {

		String orgName = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String contactLastName = elib.getDataFromExcel("contact", 7, 3) + jlib.getRandomNumber();
		// step 2:navigate to organization
		driver.findElement(By.linkText("Organizations")).click();

		// step 3:Create new organization
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// step 4:enter all details & create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.name("button")).click();

		// verify header msg Expected Result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + " is Created===PASS");
		} else {
			System.out.println(orgName + " is not Created===FAIL");
		}

		// step 5:navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();

		// step 6:Create new contact
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// step 7:enter all details & create new Contact
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// switch to child window
		wlib.switchToTabOnURL(driver, "module=Accounts");

		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		// switch to parent window
		wlib.switchToTabOnURL(driver, "module=Contacts");

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// verify header msg Expected Result
		headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(contactLastName)) {
			System.out.println(contactLastName + " is Created===PASS");
		} else {
			System.out.println(contactLastName + " is not Created===FAIL");
		}
		// verify Header orgName info Expected Result
		String actOrgNameInfo = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actOrgNameInfo);
		if (actOrgNameInfo.trim().equals(orgName)) {
			System.out.println(orgName + " is Created===PASS");
		} else {
			System.out.println(orgName + " is not Created===FAIL");
		}
	}
}
