package seleniumFrameworkDesign.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.pageObjects.CartPage;
import SeleniumFrameworkDesign.pageObjects.ProductCatalog;
import seleniumFrameworkDesign.testComponents.BaseTest;
import seleniumFrameworkDesign.testComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {
//		String productName = "ZARA COAT 3";
		ldp.loginApplication("abcd@gmail.com", "Abcd23@123");
//		ldp.getErroMessage();
		Assert.assertEquals("Incorrect email or password.", ldp.getErroMessage());
	}
	
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		String country = "India";
		// Login
		ProductCatalog pc = ldp.loginApplication("abcd2026@gmail.com", "Abcd@123");

//		Products on screen
//		ProductCatalog pc = new ProductCatalog(driver);
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(productName);
		CartPage cp = pc.goToCartPage();

//		CartPage cp = new CartPage(driver);
		Boolean match = cp.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
