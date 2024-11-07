package AbstractClassWorkFlow;

import PageObjects.CartPage;
import PageObjects.OrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractWebPage {

    WebDriver driver;

    public AbstractWebPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartButton;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement ordersButton;

    public void waitAndCheckElementIsVisible(By findby)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
    }

    public void waitForElementToDisapper(WebElement ele) throws InterruptedException
    {
        Thread.sleep(2000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//		wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public CartPage goToCartPage()
    {
        cartButton.click();
        return new CartPage(driver);
    }

    public OrdersPage goToOrdersPage()
    {
        ordersButton.click();
        return new OrdersPage(driver);
    }

    public void scrollDownToTheEndOfThePage() throws InterruptedException
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 5000);");
        Thread.sleep(2000);
    }
}
