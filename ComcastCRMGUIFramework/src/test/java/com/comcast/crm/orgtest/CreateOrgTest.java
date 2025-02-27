package com.comcast.crm.orgtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrgTest extends BaseClass {

	
	@Test
	public void createOrgTest() throws Exception
	{
		
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		String orgName = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();

		// step 2:navigate to organization
	     HomePage hp=new HomePage(driver);
	     hp.getOrglnk().click();
	     UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");

		// step 3:Create new organization
	    
		OrganizationsPage op=new OrganizationsPage(driver);
		//Thread.sleep(2000);
		op.getCreateOrgPage().click();
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
		
		//Thread.sleep(2000);

		// step 4:enter all details & create new organization
		UtilityClassObject.getTest().log(Status.INFO,orgName+ " create a new org");
		CreatingNewOrganizationPage cnp=new CreatingNewOrganizationPage(driver);
		cnp.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO,orgName+ " ===create a new org");
	

		// verify header msg Expected Result
		
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		
		if(actOrgName.contains(orgName))
		{
			System.out.println(orgName+" name is verified==PASS");
		}
		else
		{
			System.out.println(orgName+" name is not verified==Fail");

		}

		
	}
	
	@Test
	
	public void  createOrgWithIndustriesTest() throws Throwable
	{
		
				String orgName=elib.getDataFromExcel("org", 4, 2)+jlib.getRandomNumber();
				String industry=elib.getDataFromExcel("org", 4, 3);
				String type=elib.getDataFromExcel("org", 4, 4);


				

				// step 2:navigate to organization
				driver.findElement(By.linkText("Organizations")).click();

				// step 3:Create new organization
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

				// step 4:enter all details & create new organization
				driver.findElement(By.name("accountname")).sendKeys(orgName);
				WebElement wbsele = driver.findElement(By.name("industry"));
				Select sel1 = new Select(wbsele);
				sel1.selectByVisibleText(industry);

				WebElement wbsele1 = driver.findElement(By.name("accounttype"));
				Select sel2 = new Select(wbsele1);
				sel2.selectByVisibleText(type);
				driver.findElement(By.name("button")).click();

				// verify the industries and type info
				String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
				if (actIndustries.equals(industry)) {
					System.out.println(industry + " Information is Verified===PASS");
				} else {
					System.out.println(industry + " Information is not Verified===FAIL");
				}
				String actType = driver.findElement(By.id("dtlview_Type")).getText();
				if (actType.equals(type)) {
					System.out.println(type + " Information is Verified===PASS");
				} else {
					System.out.println(type + " Information is not Verified===FAIL");
				}

				
			
	}
	
	@Test
	
	public void createOrgWithPhoneNumberTest() throws Exception
	{
						
				String orgName=elib.getDataFromExcel("org", 7, 2)+jlib.getRandomNumber();
				String phoneNumber=elib.getDataFromExcel("org", 7, 3);

				

				// step 2:navigate to organization
				driver.findElement(By.linkText("Organizations")).click();

				// step 3:Create new organization
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

				// step 4:enter all details & create new organization
				driver.findElement(By.name("accountname")).sendKeys(orgName);
				driver.findElement(By.id("phone")).sendKeys(phoneNumber);
				driver.findElement(By.name("button")).click();

				// verify Header phonenumber info Expected Result
				String actphoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
				if (actphoneNumber.equals(phoneNumber)) {
					System.out.println(phoneNumber + " is Created===PASS");
				} else {
					System.out.println(phoneNumber + " is not Created===FAIL");
				}

				
	}

}
