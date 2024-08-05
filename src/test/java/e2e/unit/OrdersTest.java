package e2e.unit;

import adrian.com.pages.*;
import e2e.AbstractTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.Assert;

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
}