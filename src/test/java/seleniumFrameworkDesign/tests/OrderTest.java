package seleniumFrameworkDesign.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.pageObjects.CartPage;
import SeleniumFrameworkDesign.pageObjects.CheckoutPage;
import SeleniumFrameworkDesign.pageObjects.ConfirmationPage;
import SeleniumFrameworkDesign.pageObjects.OrderPage;
import SeleniumFrameworkDesign.pageObjects.ProductCatalog;
import seleniumFrameworkDesign.testComponents.BaseTest;

public class OrderTest extends BaseTest {

	String productName = "ZARA COAT 3";
	String country = "India";

	@Test(dataProvider = "getData", groups = { "purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// Login
		ProductCatalog pc = ldp.loginApplication(input.get("email"), input.get("password"));

//		Products on screen
//		ProductCatalog pc = new ProductCatalog(driver);
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(input.get("productName"));
		CartPage cp = pc.goToCartPage();

//		CartPage cp = new CartPage(driver);
		Boolean match = cp.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage cop = cp.goToCheckOut();
		cop.selectCountry(country);
		ConfirmationPage cmp = cop.placeOrder();

//		Confirmation Page
		String confirmMessage = cmp.getSuccessMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
//		To verify ZARA COAT 3 is in order page
		// Login
		ProductCatalog pc = ldp.loginApplication("abcd2026@gmail.com", "Abcd@123");
		OrderPage op = pc.goToOrderPage();
		Assert.assertTrue(op.verifyOrderDisplay(productName));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\seleniumFrameworkDesign\\data\\purchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	@DataProvider
//	public Object[][] getData() {
//
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "abcd2026@gmail.com");
//		map.put("password", "Abcd@123");
//		map.put("productName", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "xyz2026@gmail.com");
//		map1.put("password", "Abcd@123");
//		map1.put("productName", "ADIDAS ORIGINAL");
//
//		return new Object[][] { { map }, { map1 } };
//	}

//	@DataProvider
//	public Object[][] getData() {
//		
//		return new Object[][] {{"abcd2026@gmail.com", "Abcd@123", "ZARA COAT 3"}, 
//								{"xyz2026@gmail.com", "Abcd@123", "ADIDAS ORIGINAL"}};
//		}

}
