package TestFiles;

import PageObjects.CartPage;
import PageObjects.ProductPage;
import TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class Errorvalidation extends BaseTest {

    @DataProvider
    public Object[] getData() {
        return new Object[]{"ZARA COAT 3"};
    }

    @Test(groups = {"ErrorHandling"})
    public void loginErrorValidation() {

        landingPage.navigateToTheHomePage("sssourav1113@gmail.com", "Sou8xuav123@");
        Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
    }

    @Test(dataProvider = "getData")
    public void productErrorValidation(String productName) throws IOException, InterruptedException {
        ProductPage productPage = landingPage.navigateToTheHomePage("sssourav123@gmail.com", "Sourav123@");
//        List<WebElement> products = productPage.getProductList();
        productPage.addProductToCart(productName);
        CartPage cartPage = productPage.goToCartPage();
        boolean match = cartPage.verifyProductInCartPage("ZARA COAT 33");
        Assert.assertFalse(match);
    }
}
