package e2e.unit;

import adrian.com.pages.*;
import e2e.AbstractTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class StandAloneTest extends AbstractTest {

    @Test
    void selectOneProduct() {
        String desiredProduct = "ZARA COAT 3";
        LoginPage loginPage = new LoginPage();

        ProductsPage productsPage = loginPage.logIn();
        WebElement product = productsPage.getProductByName(desiredProduct);
        Assertions.assertNotNull(product);
        productsPage.addProductToCart(desiredProduct);

        CartPage cartPage = productsPage.goToCart();
        Assertions.assertTrue(cartPage.verifyIfProductDisplayed(desiredProduct));
        CheckOutPage checkOutPage = cartPage.goToCheckOut();
        checkOutPage.selectCountry("pol");

        ConfirmationPage confirmationPage = checkOutPage.goToConfirmationPage();

        String expectedOrderHeading = "Thankyou for the order.";
        Assertions.assertTrue(confirmationPage.verifyConfirmationHeading(expectedOrderHeading));
    }
}
