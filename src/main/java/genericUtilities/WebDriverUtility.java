package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * This class consists of all generic methods related to
 * WebDriver actions
 * @author LIPIKA
 *
 */
public class WebDriverUtility {

	/*
	  here we are declaring WebDriver in method parameter because
	  we are not launching the browser , we are just using the reference
	  variable  i.e.driver
	*/
	
	/**
	 * This method will maximize the window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * This method will minimize the window
	 * @param driver
	 */
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();		
	}
	
	/**
	 * This method will wait for 10 secs for the
	 * web page to get loaded
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	/**
	 * This method will wait for 10 seconds for the element to be visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeVisible(WebDriver driver,WebElement element)
	{
	    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method will wait for 10 seconds for the element to be clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver,WebElement element)
	{
	    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method will handel dropdown by index
	 * @param element
	 * @param index
	 */
	public void handelDropDown(WebElement element,int index)
	{
		Select s =new Select(element);
		s.selectByIndex(index);		
	}
	
	/**
	 * This method will handel dropdown by value
	 * @param element
	 * @param value
	 */
	public void handelDropDown(WebElement element,String value)
	{
		Select s =new Select(element);
		s.selectByValue(value);		
	}
	
	/**
	 * This method will handel dropdown by text
	 * @param element
	 * @param text
	 */
	public void handelDropDown(String text,WebElement element)
	{
		Select s =new Select(element);
		s.selectByVisibleText(text);		
	}
	
	/**
	 * This method will perform double click action
	 * @param driver
	 */
	public void doubleClickAction(WebDriver driver,WebElement ele) 
	{
	    Actions a=new Actions(driver);
	    a.doubleClick(ele).perform();
	}
	
	/**
	 * This method will perform mouseHovering action
	 * @param driver
	 * @param ele
	 */
	public void mouseHoverAction(WebDriver driver,WebElement ele)
	{
		Actions a=new Actions(driver);
		a.moveToElement(ele).perform();
	}
	
	/**
	 * This method will  drag and drop the src element to target element
	 * @param driver
	 * @param ele1
	 * @param ele2
	 */
	public void dragAndDropAction(WebDriver driver,WebElement ele1,WebElement ele2)
	{
		Actions a=new Actions(driver);
		a.dragAndDrop(ele1, ele2).perform();
	}
	
	/**
	 * This method will perform right click action
	 * @param driver
	 */
	public void rightClickAction(WebDriver driver,WebElement ele)
	{
		Actions a=new Actions(driver);
		a.contextClick(ele).perform();
	}
	
	/**
	 * This method will click and hold on to 
	 * particular web element
	 * @param driver
	 */
	public void clickAndHoldAction(WebDriver driver,WebElement ele)
	{
		Actions a=new Actions(driver);
		a.clickAndHold(ele).perform();
	}
	
	/**
	 * This method will scroll down for 500 units
	 * @param driver
	 */
	public void scrollDownAction(WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("Window.scrollBy(0,500);", "");
	}
	
	/**
	 * This method will scroll down for 500 units
	 * @param driver
	 */
	public void scrollUpAction(WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("Window.scrollBy(0,-500);", "");
	}
	
	/**
	 * This method will scroll right for 500 units
	 * @param driver
	 */
	public void scrollRightAction(WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("Window.scrollBy(500,0);", "");
	}
	
	/**
	 * This method will scroll left for 500 units
	 * @param driver
	 */
	public void scrollLeftAction(WebDriver driver)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("Window.scrollBy(-500,0);", "");
	}
	
	/**
	 * This method will accept the alert pop up
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method will cancel the alert pop up
	 * @param driver
	 */
	public void cancelAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * this method will capture the alert and 
	 * return to caller
	 * @param driver
	 * @return
	 */
	public String getAlertText(WebDriver driver)
	{
		String text = driver.switchTo().alert().getText();
		return text;
	}
	
	/**
	 * This method will switch to frame based on index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method will switch to frame based on nameOrId
	 * @param driver
	 * @param nameOrId
	 */
	public void switchToFrame(WebDriver driver,String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}
	
	/**
	 * This method will switch to frame based on element
	 * @param driver
	 * @param element
	 */
	public void switchToFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method will switch the windows based on partial with title
	 * @param driver
	 * @param partialWindowTitle
	 */
	public void switchToWindow(WebDriver driver,String partialWindowTitle)
	{
		//Step 1: capture all the window ids
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		//Step 2: navigate through each window id
		for(String windowID:allWindowIDs)
		{
			//Step 3: switch to each window and capture the title
			String actualTitle = driver.switchTo().window(windowID).getTitle();
			
			//step 4: compare the actual title with expected partial window title
			if(actualTitle.contains(partialWindowTitle))
			{
				break;
			}
		}		
	}
	
	/**
	 * This method will take screen shot and store it in required folder
	 * @param driver
	 * @param screenShotName
	 * @return
	 * @throws IOException
	 */
	public String captureScreenShot(WebDriver driver,String screenShotName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(".\\ScreenShots\\"+screenShotName+".png");
		
		//for this step we have imported apache commons io in pom
		Files.copy(src, destination);
		
		return destination.getAbsolutePath(); //it will return complete path of screenShot-extend reports
	}
		
}