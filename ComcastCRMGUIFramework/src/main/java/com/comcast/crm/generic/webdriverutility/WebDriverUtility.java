package com.comcast.crm.generic.webdriverutility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	public void waitForElementPresent(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void switchToTabOnURL(WebDriver driver,String partialURL)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> itr = set.iterator();
		while(itr.hasNext())
		{
			String winID = itr.next();
			driver.switchTo().window(winID);
		
			String acturl = driver.getCurrentUrl();
			if(acturl.contains(partialURL))
			{
				break;
			}
		}
	}
	public void switchToTabOnTitle(WebDriver driver,String partialTitle)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> itr = set.iterator();
		while(itr.hasNext())
		{
			String winID = itr.next();
			driver.switchTo().window(winID);
		
			String acturl = driver.getTitle();
			if(acturl.contains(partialTitle))
			{
				break;
			}
		}
	}
	public void switchtoFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
		
	}
	public void switchtoFrame(WebDriver driver,String nameID)
	{
		driver.switchTo().frame(nameID);
		
	}
	public void switchtoFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
		
	}
	public void switchtoAlertAndAccept(WebDriver driver,WebElement element)
	{
		driver.switchTo().alert().accept();
		
	}
	public void switchtoAlertAndDismiss(WebDriver driver,WebElement element)
	{
		driver.switchTo().alert().dismiss();
		
	}
	
	public void select(WebElement element,String text)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	public void select(WebElement element,int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	
	public void mousemoveOnElement(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	public void doubleclick(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
	}
	public void rightclick(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
	}
	
	public void dragAndDrop(WebDriver driver,WebElement src,WebElement dest)
	{
		Actions act=new Actions(driver);
		act.dragAndDrop(src, dest);
	}
	
	public void clickAndHold(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.clickAndHold(element);
	}
	
	public void scrollByamount(WebDriver driver,int x,int y)
	{
		Actions act=new Actions(driver);
		act.scrollByAmount(x,y);
	}
	public void scrollToElement(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.scrollToElement(element);
	}
	public void pressShiftWithKey(WebDriver driver,WebElement element,String key)
	{
		Actions act=new Actions(driver);
		act.keyDown(element,Keys.SHIFT).sendKeys(element,key).keyUp(element,Keys.SHIFT).perform();
	}
	//taking Screenshot of webpage
	public void takesScreenShotAS(WebDriver driver,String filepath)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(filepath);	
		try {
			org.openqa.selenium.io.FileHandler.copy(src, dest);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	//taking Screenshot of webelement
	
	
	//alert
	public void acceptAlert(WebDriver driver) {
       
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
    
public void dismissAlert(WebDriver driver) {
    
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }
}

	
	

