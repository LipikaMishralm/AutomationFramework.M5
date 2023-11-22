package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfopage {

	//Declaration
	@FindBy(xpath = "")
	private WebElement  ContactHeaderText;
	
	//Initialisation
	public ContactInfopage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	//Utilisation
	public WebElement getContactHeaderText() {
		return ContactHeaderText;
	}
	
	//Business libraries
	/**
	 * this method will capture the header text and return it to caller
	 * @return
	 */
	public String getContactHeader()
	{
		return ContactHeaderText.getText();
	}
}
