package e2e.unit;

import adrian.com.pages.*;
import adrian.com.utils.ConvertUtils;
import e2e.AbstractTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;

public class OrdersTest extends AbstractTest {

    /** IMPORTANT!!! I USE TESTNG HERE */

    String desiredProduct = "ZARA COAT 3";

    @Test
    void submitOrder() {
        ProductsPage productsPage = loginPage.logIn();
        WebElement product = productsPage.getProductByName(desiredProduct);
        Assert.assertNotNull(product);
        productsPage.addProductToCart(desiredProduct);

        CartPage cartPage = productsPage.goToCart();
        Assert.assertTrue(cartPage.verifyIfProductDisplayed(desiredProduct));
        CheckOutPage checkOutPage = cartPage.goToCheckOut();
        checkOutPage.selectCountry("pol");

        ConfirmationPage confirmationPage = checkOutPage.goToConfirmationPage();

        String expectedOrderHeading = "Thankyou for the order.";
        Assert.assertTrue(confirmationPage.verifyConfirmationHeading(expectedOrderHeading));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    void orderHistory() {
        // Implement the test logic for order history
        ProductsPage productsPage = loginPage.logIn();
        OrdersPage ordersPage = productsPage.goToOrders();
        Assert.assertTrue(ordersPage.verifyIfProductDisplayed(desiredProduct));
    }

    @Test(dataProvider = "jsonProvider")
    void parametrizedOrderTest(HashMap<String, String> input) {
        ProductsPage productsPage = loginPage.logIn(input.get("email"), input.get("password"));
        WebElement product = productsPage.getProductByName(input.get("product"));
        Assert.assertNotNull(product);
        productsPage.addProductToCart(input.get("product"));

        CartPage cartPage = productsPage.goToCart();
        Assert.assertTrue(cartPage.verifyIfProductDisplayed(input.get("product")));
        CheckOutPage checkOutPage = cartPage.goToCheckOut();
        checkOutPage.selectCountry("pol");

        ConfirmationPage confirmationPage = checkOutPage.goToConfirmationPage();

        String expectedOrderHeading = "Thankyou for the order.";
        Assert.assertTrue(confirmationPage.verifyConfirmationHeading(expectedOrderHeading));
    }

    @DataProvider
    public Object[][] provider() {
        return new Object[][] {{"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}, {"test0@tester.com", "AdrianTest0", "ZARA COAT 3"}};
    }

    @DataProvider
    public Object[][] jsonProvider() {
        List<HashMap<String, String>> hashMaps = ConvertUtils.convertToHasMapList("src/test/java/resources/dataProviders/orders.json");
        return new Object[][] {{hashMaps.get(0)}, {hashMaps.get(1)}};
    }

}