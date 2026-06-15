package SeleniumFrameworkDesign.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
//		PageFactory
		PageFactory.initElements(driver, this);
	}
	
//	driver.findElement(By.cssSelector(".form-group .input")
	@FindBy(css=".form-group .input")
	private WebElement enterCountry;
	
//	driver.findElement(By.xpath("//button[contains(@class, 'ta-item')][2]")).click();
	@FindBy(xpath="//button[contains(@class, 'ta-item')][2]")
	private WebElement clickCountry;
	
//	driver.findElement(By.cssSelector(".action__submit"));
	@FindBy(css=".action__submit")
	private WebElement placeOrderBtn;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String country) {
		Actions a = new Actions(driver);
		a.sendKeys(enterCountry, country).build().perform();
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
		waitForElementToAppear(results);
		clickCountry.click();
	}
	
	public ConfirmationPage placeOrder() {
		Actions a = new Actions(driver);
		a.scrollToElement(placeOrderBtn).build().perform();
		placeOrderBtn.click();
		return new ConfirmationPage(driver);
	}
}
