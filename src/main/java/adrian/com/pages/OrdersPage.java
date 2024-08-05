package adrian.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractPage {

    public OrdersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//tbody/tr")
    List<WebElement> products;

    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> productsNames;

    public Boolean verifyIfProductDisplayed(String productName) {
        return productsNames.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
    }
}