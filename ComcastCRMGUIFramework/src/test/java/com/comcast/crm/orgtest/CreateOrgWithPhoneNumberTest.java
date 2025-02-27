package com.comcast.crm.orgtest;

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

public class CreateOrgWithPhoneNumberTest {

	public static void main(String[] args) throws Exception {
		// read common data from prop
		FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		JavaUtility jlib=new JavaUtility();
		// WebDriver driver = new ChromeDriver();

		String BROWSER = pobj.getProperty("browser");
		String URL = pobj.getProperty("url");
		String USERNAME = pobj.getProperty("username");
		String PASSWORD = pobj.getProperty("password");

		

		// read testscript data from Excel file
		FileInputStream fis1 = new FileInputStream("./testdata/testScriptdata.xlsx");

		Workbook wb = WorkbookFactory.create(fis1);

		Sheet sh = wb.getSheet("org");

		Row row = sh.getRow(7);

		String orgName = row.getCell(2).toString() + jlib.getRandomNumber();
		String phoneNumber = row.getCell(3).toString();

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
		driver.findElement(By.id("phone")).sendKeys(phoneNumber);
		driver.findElement(By.name("button")).click();

		// verify Header phonenumber info Expected Result
		String actphoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
		if (actphoneNumber.equals(phoneNumber)) {
			System.out.println(phoneNumber + " is Created===PASS");
		} else {
			System.out.println(phoneNumber + " is not Created===FAIL");
		}

		// step 5: logout of application
		driver.quit();
	}

}
