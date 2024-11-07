package PageObjects;

import AbstractClassWorkFlow.AbstractWebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractWebPage {
    WebDriver driver;

    public OrdersPage(WebDriver driver) {
        // initialization
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> ordersName;

    public Boolean verifyOrderDisplay(String productName){
        Boolean match = ordersName.stream().anyMatch(carProduct->carProduct.getText().equalsIgnoreCase(productName));
        return match;
    }
}
