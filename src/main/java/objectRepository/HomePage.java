package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility{

	//img[@src='themes/softed/images/user.PNG']
	
	//Declaration
	@FindBy(linkText = "Organizations")
	private WebElement organisationLnk;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactsLnk;
	
	@FindBy(linkText = "Products")
	private WebElement productLnk;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorImg;
	
	@FindBy(partialLinkText  = "Sign Out")
	private WebElement signOutLnk;
	
	//Initialisation
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilisation
	public WebElement getOrganisationLnk() {
		return organisationLnk;
	}

	public WebElement getContactsLnk() {
		return contactsLnk;
	}

	public WebElement getProductLnk() {
		return productLnk;
	}

	public WebElement getAdministratorImg() {
		return administratorImg;
	}

	public WebElement getSignOutLnk() {
		return signOutLnk;
	}
	
	//Business Libraries
	/**
	 * This method will click on organisation link
	 */
	public void clickOnOrgLnk()
	{
		organisationLnk.click();
	}
	
	/**
	 * This method will click on contacts link
	 */
	public void clickOnContactsLnk()
	{
		contactsLnk.click();
	}
	
	/**
	 * this method will logout of application
	 * @param driver
	 * @throws InterruptedException
	 */
	public void logOutOfApp(WebDriver driver) throws InterruptedException
	{
        mouseHoverAction(driver, administratorImg);
		Thread.sleep(5000);
		signOutLnk.click();
	}
}
