package contactTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericUtilities.ExcelFileUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.ContactInfopage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;

public class CreateMultipleContacts {

	ExcelFileUtility eUtil=new ExcelFileUtility();
	WebDriverUtility wUtil=new WebDriverUtility();
	PropertyFileUtility pUtil=new PropertyFileUtility();
	
	
	@Test(dataProvider = "getData")
	public void createMultipleContacts(String lastname) throws IOException, InterruptedException
	{
		
		WebDriver driver=null;
		
		/*Common data*/		
		String URL = pUtil.readDataFromPropertyFile("url");
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
       
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
       
      wUtil.maximizeWindow(driver);
      wUtil.waitForPageLoad(driver);
      driver.get(URL);
       
       
		//Step 3: login to application
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//step 4: navigate to contacts link
		HomePage hp=new HomePage(driver);
		hp.clickOnContactsLnk();
		
		//step 5: click on create contact lookupImg
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreatecontactLookUpImg();
		
		//step 6: create new contact
		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.createNewContact(lastname);
		
		//step 7: validate 
		ContactInfopage cip=new ContactInfopage(driver);
		String contactHeader = cip.getContactHeader();
		if(contactHeader.contains(lastname))
		{
		    System.out.println(contactHeader);
			System.out.println("PASS");
			}
		else 
		{
			System.out.println("FAIL");
		}
		
		//step 8: logout
		hp.logOutOfApp(driver);
		
		
	}
	
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		return eUtil.readMultipleDataFromExcel("CreateMultipleContacts");
		
	}
}
