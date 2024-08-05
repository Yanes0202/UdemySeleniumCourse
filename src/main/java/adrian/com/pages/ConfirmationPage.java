package adrian.com.pages;

import adrian.com.managers.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {

    WebDriver driver;

    public ConfirmationPage() {
        this.driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "h1")
    WebElement heading;

    public Boolean verifyConfirmationHeading(String expectedHeading) {
        return expectedHeading.equalsIgnoreCase(heading.getText());
    }

}
