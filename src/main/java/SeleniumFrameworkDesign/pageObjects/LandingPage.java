package SeleniumFrameworkDesign.pageObjects;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver); // sending driver to parent class using super
		this.driver = driver;
//			PageFactory
		PageFactory.initElements(driver, this);
	}

//		WebElement email = driver.findElement(By.id("userEmail"));
//		driver.findElement(By.id("userPassword")).sendKeys("Abcd@123");
//		driver.findElement(By.id("login")).click();

//		PageFactory
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement loginBtn;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public ProductCatalog loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginBtn.click();

		return new ProductCatalog(driver);
	}
	
	public String getErroMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
