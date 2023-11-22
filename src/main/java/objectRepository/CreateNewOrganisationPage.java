package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewOrganisationPage extends WebDriverUtility{

	
	//Declaration
	@FindBy(name = "accountname")
	private WebElement OrgNameEdt;
	
	@FindBy(name = "industry")
	private WebElement IndustryDropDown;
	
	@FindBy(name = "accounttype")
	private WebElement typeDropDown;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;
	
	//initialisation
	public CreateNewOrganisationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilisation
	public WebElement getOrgNameEdt() {
		return OrgNameEdt;
	}

	public WebElement getIndustryDropDown() {
		return IndustryDropDown;
	}

	public WebElement getTypeDropDown() {
		return typeDropDown;
	}

	public WebElement getSaveBtn() {
		return SaveBtn;
	}
	
	//Business libraries
	/**
	 * This method will create new organisation with mandatory fields
	 * @param ORGNAME
	 */
	public void createNewOrganisation(String ORGNAME) {
		OrgNameEdt.sendKeys(ORGNAME);
		SaveBtn.click();
	}
	
	/**
	 * this method will create new organisation with industry dropdown
	 * @param ORGNAME
	 * @param INDUSTRYNAME
	 */
	public void createNewOrganisation(String ORGNAME,String INDUSTRYNAME) 
	{
		OrgNameEdt.sendKeys(ORGNAME);
		handelDropDown(INDUSTRYNAME, IndustryDropDown);
		SaveBtn.click();
	}

}
