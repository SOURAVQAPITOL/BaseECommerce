package PageObjects;

import AbstractClassWorkFlow.AbstractWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractWebPage {

    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "//a[(contains(text(),'Place Order'))]")
    WebElement submit;

    @FindBy(xpath = "(//button[@class='ta-item list-group-item ng-star-inserted'])[2]")
    WebElement selectCountry;

    By results = By.cssSelector(".ta-results.list-group.ng-star-inserted");

    public void selectCountry(String countryName) throws InterruptedException {
        Actions action = new Actions(driver);
        country.click();
        action.sendKeys(country, countryName).build().perform();
        waitAndCheckElementIsVisible(results);
        Thread.sleep(3000);
        selectCountry.click();
    }

    public void scrollUpToSelectCountry() throws InterruptedException
    {
        scrollDownToTheEndOfThePage();
        Thread.sleep(2000);
    }

    public ConfirmationPage submitOrder() {
        submit.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }
}
