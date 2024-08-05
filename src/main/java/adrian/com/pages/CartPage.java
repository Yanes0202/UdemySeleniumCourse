package adrian.com.pages;

import adrian.com.utils.WaitUtils;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractPage {

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Getter
    @FindBy(xpath = "//li[contains(@class,'items')]")
    List<WebElement> cartProducts;

    @FindBy(css = ".totalRow button")
    WebElement checkOutButton;


    public List<String> getCartProductsName() {
        return cartProducts.stream().map(p -> p.findElement(By.tagName("h3")).getText()).toList();
    }

    public Boolean verifyIfProductDisplayed(String productName) {
        return getCartProductsName().stream().anyMatch(p -> p.equals(productName));
    }

    public CheckOutPage goToCheckOut() {
        checkOutButton.click();
        WaitUtils.untilElementIsClickable(driver, By.className("payment"));
        return new CheckOutPage(driver);
    }
}