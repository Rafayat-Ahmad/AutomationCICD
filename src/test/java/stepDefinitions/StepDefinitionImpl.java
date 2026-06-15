package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFrameworkDesign.pageObjects.CartPage;
import SeleniumFrameworkDesign.pageObjects.CheckoutPage;
import SeleniumFrameworkDesign.pageObjects.ConfirmationPage;
import SeleniumFrameworkDesign.pageObjects.LandingPage;
import SeleniumFrameworkDesign.pageObjects.ProductCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumFrameworkDesign.testComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest{

	public LandingPage landingPage;
	public ProductCatalog pc;
	public ConfirmationPage cmp;
	
	@Given("I landed on Ecoomerce Page")
	public void I_landed_on_Ecoomerce_Page() throws IOException {
		//code
		landingPage = launchApplication();
	}
	
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		pc = landingPage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(productName);
	}
	
//	or And
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) {
		CartPage cp = pc.goToCartPage();

//		CartPage cp = new CartPage(driver);
		Boolean match = cp.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage cop = cp.goToCheckOut();
		cop.selectCountry("india");
		cmp = cop.placeOrder();
	}
	
//	"Thankyou for the order." message is displayed on confirmation page
	@Then("{string} message is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page( String string) {
		String confirmMessage = cmp.getSuccessMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	
	@Then("{string} message is displayed")
	public void error_message_is_diplayed(String string) {
		Assert.assertEquals(string, landingPage.getErroMessage());
		driver.close();
	}
}
