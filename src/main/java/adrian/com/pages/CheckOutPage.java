package adrian.com.pages;

import adrian.com.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractPage {

    public CheckOutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;

    @FindBy(css = ".action__submit")
    WebElement submitButton;

    By selectedCountryBy = By.xpath("(//button[contains(@class,'ta-item')])[3]");

    public void selectCountry(String countryName) {
        Actions actions = new Actions(driver);
        actions.sendKeys(country, countryName).build().perform();
        WaitUtils.untilElementIsClickable(driver, selectedCountryBy).click();
    }

    public ConfirmationPage goToConfirmationPage() {
        submitButton.click();
        WaitUtils.ifElementIsVisible(driver, By.tagName("h1"));
        return new ConfirmationPage(driver);
    }
}