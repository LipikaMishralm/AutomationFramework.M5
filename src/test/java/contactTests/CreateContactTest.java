package contactTests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import genericUtilities.BaseClass;
import objectRepository.ContactInfopage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.HomePage;

@Listeners(genericUtilities.ListenersImplimentation.class)
public class CreateContactTest extends BaseClass{

@Test(groups = {"SmokeSuite","RegressionSuite"})
public void createNewContactTest() throws InterruptedException, IOException
{				
				/*Test data*/	
		       String LASTNAME = eUtil.readDataFromExcel("Contacts",1,2);
				
				//step 4: navigate to contacts link
				HomePage hp=new HomePage(driver);
				hp.clickOnContactsLnk();
				
				//step 5: click on create contact lookupImg
				ContactsPage cp=new ContactsPage(driver);
				cp.clickOnCreatecontactLookUpImg();
				
			//	Assert.fail();
			
				//step 6: create new contact
				CreateNewContactPage cncp=new CreateNewContactPage(driver);
				cncp.createNewContact(LASTNAME);
				
				//step 7: validate 
				ContactInfopage cip=new ContactInfopage(driver);
				String contactHeader = cip.getContactHeader();
				
				Assert.assertTrue(contactHeader.contains(LASTNAME));
				System.out.println(contactHeader);
      }

}
