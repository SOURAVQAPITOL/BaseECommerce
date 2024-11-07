package TestFiles;

import AbstractClassWorkFlow.AbstractWebPage;
import PageObjects.*;
import TestComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class StandAlone2Test extends BaseTest {

    public String productName = "ZARA COAT 3";

    @DataProvider
    public Object[][] getData() throws IOException {

//        HashMap<String,String> map = new HashMap<String,String>();
//        map.put("email","sssourav123@gmail.com");
//        map.put("pass","Sourav123@");
//        map.put("productNamee","ZARA COAT 3");
//        map.put("Country","india");
//
//        HashMap<String,String> map1 = new HashMap<String,String>();
//        map1.put("email","qwerfdsa@gmail.com");
//        map1.put("pass","Sourav123@");
//        map1.put("productNamee","ADIDAS ORIGINAL");
//        map1.put("Country","india");

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//DataFiles//PurchaseOrder.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }

    @Test(dataProvider = "getData", groups = "purchase")
    public void submitOrder(HashMap<String, String> input) throws InterruptedException {
        ProductPage productPage = landingPage.navigateToTheHomePage(input.get("email"), input.get("pass"));
        productPage.addProductToCart(input.get("productNamee"));
        CartPage cartPage = productPage.goToCartPage();
        boolean match = cartPage.verifyProductInCartPage(input.get("productNamee"));
        Thread.sleep(2000);
        Assert.assertTrue(match);
        cartPage.isCheckOutButtonDisplayed();
        CheckOutPage checkoutPage = cartPage.goToCheckOut();
        AbstractWebPage abstractWebPage = new AbstractWebPage(driver);
        abstractWebPage.scrollDownToTheEndOfThePage();
        checkoutPage.selectCountry(input.get("Country"));
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmationMsg = confirmationPage.getConfirmationPage();
        Assert.assertTrue(confirmationMsg.equalsIgnoreCase("Thankyou for the order."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistory() {
        ProductPage productPage = landingPage.navigateToTheHomePage("sssourav123@gmail.com", "Sourav123@");
        OrdersPage ordersPage = productPage.goToOrdersPage();
        Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
    }

}
