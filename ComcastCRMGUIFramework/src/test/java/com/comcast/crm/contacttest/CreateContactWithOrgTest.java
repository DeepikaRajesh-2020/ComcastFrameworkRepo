package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws Exception {
		// read common data from prop
		FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		// WebDriver driver = new ChromeDriver();

		String BROWSER = pobj.getProperty("browser");
		String URL = pobj.getProperty("url");
		String USERNAME = pobj.getProperty("username");
		String PASSWORD = pobj.getProperty("password");


		// read testscript data from Excel file
		FileInputStream fis1 = new FileInputStream("./testdata/testScriptdata.xlsx");

		Workbook wb = WorkbookFactory.create(fis1);

		Sheet sh = wb.getSheet("contact");

		Row row = sh.getRow(7);

		String orgName = row.getCell(2).toString() + jlib.getRandomNumber();
		String contactLastName = row.getCell(3).toString() + jlib.getRandomNumber();

		// step 6:close the workbook
		wb.close();

		WebDriver driver = null;

		if (BROWSER.equals("chrome")) {

			driver = new ChromeDriver();
			driver.manage().window().maximize();

		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();

		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
			driver.manage().window().maximize();

		}

		wlib.waitForPageToLoad(driver);
		// step 1:Login to application

		driver.get(URL);

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

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
		
		//switch to child window
		wlib.switchToTabOnURL(driver, "module=Accounts");
		
		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		//switch to parent window
		wlib.switchToTabOnURL(driver, "module=Contacts");
		
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify header msg Expected Result
		 headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerInfo.contains(contactLastName))
		{
			System.out.println(contactLastName+" is Created===PASS");
		}
		else
		{
			System.out.println(contactLastName+" is not Created===FAIL");
		}
		//verify Header orgName info Expected Result
		String actOrgNameInfo = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actOrgNameInfo);
		if(actOrgNameInfo.trim().equals(orgName))
		{
			System.out.println(orgName+" is Created===PASS");
		}
		else
		{
			System.out.println(orgName+" is not Created===FAIL");
		}
		// step 8: logout of application
		driver.quit();
	}

}
