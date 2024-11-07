package PageObjects;

import AbstractClassWorkFlow.AbstractWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractWebPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "*//div[@class='cart']//h3")
    List<WebElement> cartProducts;

    @FindBy(xpath = "//div[@class='cartSection removeWrap']/button[@class='btn btn-primary']")
    public static WebElement checkOutButton;

    public boolean verifyProductInCartPage(String productName)
    {
        Boolean match = cartProducts.stream().anyMatch(carProduct->carProduct.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckOutPage goToCheckOut()
    {
        checkOutButton.click();
        CheckOutPage checkoutPage =  new CheckOutPage(driver);
        return checkoutPage;
    }
    public void isCheckOutButtonDisplayed() {
        waitAndCheckElementIsVisible(By.cssSelector(".totalRow button"));
    }
}
