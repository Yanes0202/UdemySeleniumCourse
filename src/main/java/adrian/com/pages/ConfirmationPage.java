package adrian.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractPage {

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "h1")
    WebElement heading;

    public Boolean verifyConfirmationHeading(String expectedHeading) {
        return expectedHeading.equalsIgnoreCase(heading.getText());
    }
}