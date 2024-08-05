package adrian.com.pages;

import adrian.com.utils.WaitUtils;
import org.openqa.selenium.By;

public class AbstractPage {

    By cartBy = By.cssSelector("[routerlink*='cart']");

    public CartPage goToCart() {
        WaitUtils.untilElementIsClickable(cartBy).click();
        return new CartPage();
    }

}
