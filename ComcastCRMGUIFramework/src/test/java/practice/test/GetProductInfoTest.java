package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest {
	
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName,String productName)
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.flipkart.com/");
		
		//search for product
		driver.findElement(By.name("q")).sendKeys(brandName,Keys.ENTER);
		
		//capture product info
		//String x="//span[text()='"+productName+"']/../../../../div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span/span[2]/span[2]";
		String y="//div[text()='"+productName+"']/../../../../a/div[2]/div[2]/div[1]/div[1]/div[1]";
		String price = driver.findElement(By.xpath(y)).getText();
		System.out.println(price);
		
	}
	
	@DataProvider
	public Object[][] getData() throws Exception
	{
		ExcelUtility elib=new ExcelUtility();
		int rowCount = elib.getRowCount("product");
		Object[][] objArr=new Object[rowCount][2];
		for(int i=0;i<rowCount;i++) {
		objArr[i][0]=elib.getDataFromExcel("product", i+1, 0);
		objArr[i][1]=elib.getDataFromExcel("product", i+1, 1);
		}
//		objArr[0][0]="iphone";
//		objArr[0][1]="Apple iPhone 15 (256 GB) - Pink";
//		
//	    objArr[1][0]="iphone";
//		objArr[1][1]="Apple iPhone 14 (128 GB) - Blue";
//		
//		objArr[2][0]="iphone";
//		objArr[2][1]="Apple iPhone 15 (128 GB) - Green";
		return objArr; 
		
	}

}
