package practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import genericUtilities.ExcelFileUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.LoginPage;

public class Scenario1WithDDTAndGU {

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {

		//create Object of all utilities
		ExcelFileUtility eutil=new ExcelFileUtility();
		PropertyFileUtility putil=new PropertyFileUtility();
		WebDriverUtility wutil=new WebDriverUtility();
		//JavaUtility jutil=new JavaUtility();
		WebDriver driver=null;
		
		
		//Step 1: Read all the required data		
		 /*Common data*/		
		String URL = putil.readDataFromPropertyFile("url");
		String BROWSER = putil.readDataFromPropertyFile("browser");
		String USERNAME = putil.readDataFromPropertyFile("username");
		String PASSWORD = putil.readDataFromPropertyFile("password");
		
		/*Test data*/	
       String LASTNAME = eutil.readDataFromExcel("Contacts",1,2);
              
       //Step 2: Launch the browser
       if(BROWSER.equalsIgnoreCase("Edge")) 
       {
       	WebDriverManager.edgedriver().setup();
       	driver=new EdgeDriver();
       }
       else if(BROWSER.equalsIgnoreCase("Firefox"))
       {
       	WebDriverManager.firefoxdriver().setup();
       	driver=new FirefoxDriver();
       }
       else if(BROWSER.equalsIgnoreCase("Chrome"))
       {
       	WebDriverManager.chromedriver().setup();
       	driver=new ChromeDriver();
       }
       else
       {
       	System.err.println("Invalid browser name!!!");
       }
       
      wutil.maximizeWindow(driver);
      wutil.waitForPageLoad(driver);
      driver.get(URL);
       
     //Step 3: Login to application
      
     //driver.findElement(By.name("user_name")).sendKeys(USERNAME);
     //driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
     //driver.findElement(By.id("submitButton")).click();
      
     //implimenting using pom class we have created "LoginPage"	
      
      LoginPage lp=new LoginPage(driver);
     // lp.getUserNameEdt().sendKeys(USERNAME);
     // lp.getPasswordEdt().sendKeys(PASSWORD);
     // lp.getLoginBtn().click();
      
      lp.loginToApp(USERNAME, PASSWORD);
      
     		//Step 4: Navigate to contacts link
     		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
     		
     		//Step 5: Click on create contact lookUp image
     		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
     		
     		//Step 6: Create contact with mandatory field
     		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
     		
     		//Step 7: Save
     		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
     		
     		//Step 8: Validate
     		String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
     		if(contactHeader.contains(LASTNAME))
     		{
     			System.out.println(contactHeader);
     			System.out.println("PASS");
     		}
     		else
     		{
     			System.out.println("FAIL");
     		}
     		
     		//Step 9: Logout
     		WebElement contactLookupImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
     		wutil.mouseHoverAction(driver, contactLookupImage);
     		Thread.sleep(3000);
     		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
     	    System.out.println("SignedOut successfully...");
	}

}