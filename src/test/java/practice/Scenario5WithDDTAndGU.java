package practice;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario5WithDDTAndGU {

	public static void main(String[] args) throws IOException, InterruptedException {

		//create object of all utilities
		PropertyFileUtility putil=new PropertyFileUtility();
		ExcelFileUtility eutil=new ExcelFileUtility();
		WebDriverUtility wutil=new WebDriverUtility();
		JavaUtility jutil=new JavaUtility();
		WebDriver driver=null;
		
		//Step 1: Read all the required data
		 /*Common data*/		
		String URL = putil.readDataFromPropertyFile("url");
		String BROWSER = putil.readDataFromPropertyFile("browser");
		String USERNAME = putil.readDataFromPropertyFile("username");
		String PASSWORD = putil.readDataFromPropertyFile("password");
		
		/*Test data*/
        String LASTNAME = eutil.readDataFromExcel("Contacts", 1, 2);
        String ORGNAME = eutil.readDataFromExcel("Contacts", 4, 3)+jutil.getRandomNumber();
        
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
      	driver.findElement(By.name("user_name")).sendKeys(USERNAME);
      	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
      	driver.findElement(By.id("submitButton")).click();
      	
        //step 4 navigate to organisation link
        driver.findElement(By.linkText("Organizations")).click();
        
		//step 5 click on create organization look up icon
        driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
        
		//Step 6: fill the mandatory field
         driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
         
		//step 7: click on save button
         driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
         
       //step 8: validate
         String organizationHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
        if(organizationHeader.contains(ORGNAME))
        {
       	 System.out.println("PASS");
        }
        else
        {
       	 System.out.println("FAIL");
        }

       //Step 9: Navigate to contacts link
 		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
 		
 		//Step 10: Click on create contact lookUp image
 		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
 		
 		//Step 11: Create contact with mandatory field
 		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
 		
 		//Step 12: click on create organization look up image
 		driver.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();
 		
 		//Step 13: Switch to child window
 		wutil.switchToWindow(driver, "Accounts");
 		System.out.println("switched to child window");
		
        //Step 14: Search for organization name
 		driver.findElement(By.name("search_text")).sendKeys(ORGNAME);
 		driver.findElement(By.name("search")).click();
 		driver.findElement(By.xpath("//a[text()='"+ORGNAME+"']")).click();
 		
 		//Step 15: Switch the control back to parent
 		wutil.switchToWindow(driver, "Contacts");
 		System.out.println("Switched back to parent window");
 		
 		//Step 16: Save
 		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
 		
 		//Step 17: Validate
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
 		
 		//Step 18: Logout
 		WebElement contactLookupImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
 		wutil.mouseHoverAction(driver, contactLookupImage);
 		Thread.sleep(3000);
 		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
 	    System.out.println("SignedOut successfully...");
 		
	}

}
