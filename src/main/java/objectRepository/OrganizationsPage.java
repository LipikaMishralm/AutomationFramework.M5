package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class OrganizationsPage {

	//Declaration
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement createOrgLookUpImg;
	
	//Initialisation
	public OrganizationsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//utilisation
	public WebElement getCreateOrgLookUpImg() {
		return createOrgLookUpImg;
	}
	
	//Business libraries
	/**
	 * This method will click on create org look up image
	 */
	public void createOrgLookUpImg() {
		createOrgLookUpImg.click();
	}
	
}
