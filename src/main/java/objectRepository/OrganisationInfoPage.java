package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganisationInfoPage {

	
	//declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement OrgHeaderText;
	
	//initialisation
	public OrganisationInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilisation
	public WebElement getOrgHeaderText() {
		return OrgHeaderText;
	}
	
	//Business libraries
	/**
	 * this method will capture the header text and return it to caller
	 * @return
	 */
	public String getOrganisationHeader() {
		return OrgHeaderText.getText();
	}
}
