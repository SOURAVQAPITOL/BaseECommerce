package PageObjects;

import AbstractClassWorkFlow.AbstractWebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractWebPage {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        // initialization
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // PageObject Factory

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement password;

    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
    WebElement emailValidation;

    public ProductPage navigateToTheHomePage(String usersEmail, String usersPassword) {
        // TODO Auto-generated method stub
        userEmail.sendKeys(usersEmail);
        password.sendKeys(usersPassword);
        loginButton.click();
        ProductPage productPage = new ProductPage(driver);
        return productPage;
    }
    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client/");
    }

    public String getErrorMessage() {
        return emailValidation.getText().trim();
    }
}
