package adrian.com.pages;

import adrian.com.managers.Driver;
import adrian.com.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage extends AbstractPage {

    WebDriver driver;

    public ProductsPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;
    By productsBy = By.cssSelector(".mb-3");

    public List<WebElement> getProducts() {
        WaitUtils.untilElementIsVisible(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        return getProducts().stream().filter(p -> p.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
    }

    public void addProductToCart(String productName) {
        getProductByName(productName).findElement(By.cssSelector("button:last-of-type")).click();
        WaitUtils.untilElementIsPresent(By.cssSelector("#toast-container"));
        WaitUtils.untilElementIsInvisible(driver, By.cssSelector(".ng-animating"));
        WaitUtils.untilElementIsInvisible(driver, By.cssSelector(".ng-animating"));
    }
}
