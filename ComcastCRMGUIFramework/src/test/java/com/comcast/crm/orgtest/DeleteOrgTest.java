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
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest {

	public static void main(String[] args) throws Exception {
		// read common data from prop
		FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
        
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		
		String BROWSER = pobj.getProperty("browser");
		String URL = pobj.getProperty("url");
//		String USERNAME = pobj.getProperty("username");
//		String PASSWORD = pobj.getProperty("password");


		// read testscript data from Excel file
		FileInputStream fis1 = new FileInputStream("./testdata/testScriptdata.xlsx");

		Workbook wb = WorkbookFactory.create(fis1);

		Sheet sh = wb.getSheet("org");
		

		Row row = sh.getRow(10);		

		String orgName = row.getCell(2).toString() + jlib.getRandomNumber();

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

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// step 1:Login to application

		driver.get(URL);

//		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
//		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
//		driver.findElement(By.id("submitButton")).click();
		
		LoginPage lp =new LoginPage(driver);
		Thread.sleep(2000);
		//lp.loginToApp("admin", "admin");
		
		
		// step 2:navigate to organization
	     HomePage hp=new HomePage(driver);
	     hp.getOrglnk().click();

		// step 3:Create new organization
		OrganizationsPage op=new OrganizationsPage(driver);
		Thread.sleep(2000);
		op.getCreateOrgPage().click();
		Thread.sleep(2000);

		// step 4:enter all details & create new organization
		CreatingNewOrganizationPage cnp=new CreatingNewOrganizationPage(driver);
		cnp.createOrg(orgName);
	

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
		
		//go back to organisation page
	     hp.getOrglnk().click();
	     
	     //search for organization
	     op.getSearchEdt().sendKeys(orgName);
	     wlib.select(op.getSearchDD(), "Organization Name");
	     op.getSearchBtn().click();
	    
	     
	     //In dynamic webtable select and delete org
	     driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
	     
	     wlib.acceptAlert(driver);
	     System.out.println("Organization deleted successfully.");
	     
	     // Handle alert popup
//	        if (isAlertPresent(driver)) {
//	            Alert alert = driver.switchTo().alert();
//	            System.out.println("Alert Text: " + alert.getText());
//	            alert.accept(); // Click "OK"
//	            System.out.println("Organization deleted successfully.");
//	        } else {
//	            System.out.println("No alert appeared. Deletion may not have happened.");
//	        }
	        
	        

		// step 5: logout of application
		//hp.logout();
		//driver.quit();
	}
	
//	public static boolean isAlertPresent(WebDriver driver) {
//        try {
//            driver.switchTo().alert();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

}
