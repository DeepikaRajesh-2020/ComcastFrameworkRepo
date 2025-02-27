package com.comcast.crm.contacttest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class ReadingDataFromDropDown {
	public static void main(String[] args) throws Exception {
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
	    WebDriverUtility wlib=new WebDriverUtility();
		JavaUtility jlib=new JavaUtility();
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		String orgName=elib.getDataFromExcel("org", 1, 2);
		
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

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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
				WebElement wbsele = driver.findElement(By.name("industry"));
				wlib.select(wbsele, "Energy");
				
				WebElement wbsele1 = driver.findElement(By.name("accounttype"));
				wlib.select(wbsele1, "Press");
				driver.findElement(By.name("button")).click();

	}

}
