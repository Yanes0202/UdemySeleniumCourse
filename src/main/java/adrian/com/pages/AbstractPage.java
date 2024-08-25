package adrian.com.pages;

import adrian.com.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

    WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    By cartBy = By.cssSelector("[routerlink*='cart']");

    By orderBy = By.cssSelector("[routerlink*='myorders']");

    public CartPage goToCart() {
        WaitUtils.untilElementIsClickable(driver, cartBy).click();
        return new CartPage(driver);
    }

    public OrdersPage goToOrders() {
        WaitUtils.untilElementIsClickable(driver, orderBy).click();
        return new OrdersPage(driver);
    }
}