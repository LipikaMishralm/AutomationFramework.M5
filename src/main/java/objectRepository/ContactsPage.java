package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	//Declaration
	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement createContactLookUpImg;
	
	//initialisation
	public ContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilisation
	public WebElement getCreateContactLookUpImg() {
		return createContactLookUpImg;
	}
	
	//Business libraries
	/**
	 * this method will click on create contact lookUp image
	 */
	public void clickOnCreatecontactLookUpImg() 
	{
		createContactLookUpImg.click();
	}
}
