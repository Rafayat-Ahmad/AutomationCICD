package SeleniumFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
//		PageFactory
		PageFactory.initElements(driver, this);
	}
	
	
//	List<WebElement> cartProducts = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
	@FindBy(xpath="//*[@class='cartSection']/h3")
	List<WebElement> productTitles;
	
//	driver.findElement(By.cssSelector(".totalRow button")).click();
	@FindBy(css=".totalRow button")
	WebElement checkOutBtn;
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean matchCartprod = productTitles.stream().anyMatch(cartProduct ->
		cartProduct.getText().equalsIgnoreCase(productName));
		return matchCartprod;
	}
	
	public CheckoutPage goToCheckOut() {
		checkOutBtn.click();
		return new CheckoutPage(driver);
	}

}
