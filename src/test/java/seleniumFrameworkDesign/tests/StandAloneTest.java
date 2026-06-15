package seleniumFrameworkDesign.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SeleniumFrameworkDesign.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		
		LandingPage ld = new LandingPage(driver);
		
		//Login
		driver.findElement(By.id("userEmail")).sendKeys("abcd2026@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Abcd@123");
		driver.findElement(By.id("login")).click();
		
//		Products on screen
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
//		for(WebElement product:products) {
//			System.out.println(product.findElement(By.tagName("b")).getText());
////			System.out.println(text);
//		}
		
//		Get the product with name - "ZARA COAT 3" and then add to cart
//		Here we have used stream to fetch the product,
		WebElement prod = products.stream().filter(product -> 
		product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		
//		Add to cart button locator
		WebElement addToCartBtn = prod.findElement(By.cssSelector(".card-body button:last-of-type"));
		wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
		addToCartBtn.click();
		
		//ng-animating location for loading
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//cart page
		List<WebElement> cartProducts = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
		
		Boolean matchCartprod = cartProducts.stream().anyMatch(cartProduct ->
		cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(matchCartprod);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
//		driver.findElement(By.cssSelector(".form-group .input")).sendKeys("India");
//		
//		List<WebElement> text = driver.findElements(By.cssSelector(".ta-item span"));
//		WebElement t = text.stream().filter(txt ->
//		txt.getText().equalsIgnoreCase("India")).;
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector(".form-group .input")), "India").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
		
//		.ta-item:nth-of-type(2)
		driver.findElement(By.xpath("//button[contains(@class, 'ta-item')][2]")).click();
		
		WebElement placeOrderBtn = driver.findElement(By.cssSelector(".action__submit"));
		a.scrollToElement(placeOrderBtn).build().perform();
		placeOrderBtn.click(); 
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		driver.close();

	}

}
