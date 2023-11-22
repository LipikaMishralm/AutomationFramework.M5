package contactTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtilities.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.ContactInfopage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.CreateNewOrganisationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganisationInfoPage;
import objectRepository.OrganizationsPage;

public class CreateContactWithOrg extends BaseClass{

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {

		//create Object of all utilities
				ExcelFileUtility eutil=new ExcelFileUtility();
				PropertyFileUtility putil=new PropertyFileUtility();
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
		       String LASTNAME = eutil.readDataFromExcel("Contacts",1,2);
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
		     
		      
		      //login
		      LoginPage lp=new LoginPage(driver);
		      lp.loginToApp(USERNAME, PASSWORD);
		      
		      //navigate to org link
		      HomePage hp=new HomePage(driver);
		      hp.clickOnOrgLnk();
		      
		      //click on org look up image
		      OrganizationsPage op=new OrganizationsPage(driver);
		      op.createOrgLookUpImg();
		      
		      //create new organisation
		      CreateNewOrganisationPage cnop=new CreateNewOrganisationPage(driver);
		      cnop.createNewOrganisation(ORGNAME);
		      
		      //validate for organisation
		      OrganisationInfoPage oip=new OrganisationInfoPage(driver);
		      String orgHeader = oip.getOrganisationHeader();
		      if(orgHeader.contains(ORGNAME))
		      {
		    	  System.out.println(orgHeader);
		    	  System.out.println("organisation created");
		      }
		      else
		      {
		    	  System.out.println("fail");
		      }
		      
		      //navigate to contacts
		      hp.clickOnContactsLnk();
		      
		      //click on create contact
		      ContactsPage cp=new ContactsPage(driver);
		      cp.clickOnCreatecontactLookUpImg();
		      
		      
		      //create contact with orgname
		      CreateNewContactPage cncp=new CreateNewContactPage(driver);
		      cncp.createNewContact(LASTNAME, ORGNAME, driver);
		      
		      //validate
		      ContactInfopage cip=new ContactInfopage(driver);
		      String contactHeader = cip.getContactHeader();
		      if(contactHeader.contains(LASTNAME))
		      {
		    	  System.out.println(contactHeader);
		    	  System.out.println("pass");
		      }
		      else
		      {
		    	  System.out.println("FAIL");
		      }
		      //logout
		      hp.logOutOfApp(driver);
		      
		      //close the browser
		      driver.close();
	}

}
