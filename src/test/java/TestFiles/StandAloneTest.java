package TestFiles;

import AbstractClassWorkFlow.AbstractWebPage;
import PageObjects.LandingPage;
import PageObjects.ProductPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    public static void main(String[] args, By headerText) throws InterruptedException {

        String productName = "ZARA COAT 3";
        // TODO Auto-generated method stub
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client/");

        LandingPage landingPage = new LandingPage(driver);
        ProductPage productPage = new ProductPage(driver);
        AbstractWebPage abstractPage = new AbstractWebPage(driver);


//		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        List<WebElement> product = productPage.getProductList();
        productPage.getProductByName(productName);

//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

//		wait.until(ExpectedConditions
//				.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> cartProducts = driver.findElements(By.xpath("*//div[@class='cart']//h3"));

        Boolean match = cartProducts.stream().anyMatch(carProduct -> carProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);

        WebElement checkOut = driver.findElement(By.cssSelector(".totalRow button"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", checkOut);

        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions action = new Actions(driver);
        action.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results.list-group.ng-star-inserted")));

        driver.findElement(By.xpath("(//button[@class='ta-item list-group-item ng-star-inserted'])[2]")).click();

        driver.findElement(By.xpath("//a[(contains(text(),'Place Order'))]")).click();

        String confirmationMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmationMsg.equalsIgnoreCase("Thankyou for the order."));

        driver.quit();

        driver.findElement(By.xpath("")).click();
    }
}
