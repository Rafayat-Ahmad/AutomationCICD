package SeleniumFrameworkDesign.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
//		PageFactory
		PageFactory.initElements(driver, this);
	}
	
//	driver.findElement(By.cssSelector(".hero-primary")).getText();
	@FindBy(css=".hero-primary")
	WebElement successMessage;
	
	public String getSuccessMessage() {
		return successMessage.getText();
	}

}
