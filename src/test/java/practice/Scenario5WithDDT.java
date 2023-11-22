package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario5WithDDT {

	public static void main(String[] args) throws IOException, InterruptedException {

		//Step 1: Read all the required data
			
		 /*Common data*/		
		FileInputStream fisp=new FileInputStream(".\\src\\test\\resources\\CommomData.properties");
		Properties p=new Properties();
		p.load(fisp);
		String URL = p.getProperty("url");
		String BROWSER = p.getProperty("browser");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		
		/*Test data*/
		FileInputStream fise=new FileInputStream(".\\src\\test\\resources\\Test.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
        String LASTNAME = wb.getSheet("Contacts").getRow(1).getCell(2).getStringCellValue();
        String ORGNAME = wb.getSheet("Contacts").getRow(4).getCell(3).getStringCellValue();

       
        WebDriver driver=null;
       
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
        
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(URL);
        
        
        
      //Step 2: Login to application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3: Navigate to contacts link
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		
		//Step 4: Click on create contact lookUp image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		//Step 5: Create contact with mandatory field
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		
		//Step 6: Click on create Organisation look up image
		driver.findElement(By.xpath("(//img[@title='Select'])[1]")).click();
		
		//Step 7: capture the main/parent wondow ID
		String mainWindowID = driver.getWindowHandle();
		System.out.println(mainWindowID +"-- Main window ID");
		//Step 8: capture all the window IDs(bcoz we do not have a separate method to handle child window)
		Set<String> allWindowIDS = driver.getWindowHandles();
		
		//Step 9: compare and switch to child window
		for(String winID: allWindowIDS)
		{
			if(!winID.equals(mainWindowID))
			{
				System.out.println(winID+"-- child window ID");
				driver.switchTo().window(winID);
				System.out.println("switched to child");
				break;
			}
		}
		
		//Step 10: search for any organisation
		driver.findElement(By.name("search_text")).sendKeys(ORGNAME);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText("QSpiders")).click();
		
		//Step 11: switch to normal window
		driver.switchTo().window(mainWindowID);
		System.out.println("switched back to parent");

		//Step 12: Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 13: Validate
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
		
		//Step 14: Logout
		WebElement contactLookupImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a=new Actions(driver);
		a.moveToElement(contactLookupImage).perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	    System.out.println("SignedOut successfully...");
	}

}
