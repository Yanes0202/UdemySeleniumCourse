package adrian.com.pages;

import adrian.com.utils.PropertiesUtils;
import adrian.com.utils.UrlsUtils;
import adrian.com.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    private static final String propertiesFilePath = "src/main/resources/common/credentials.properties";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userPassword")
    WebElement userPassword;
    @FindBy(id = "login")
    WebElement loginButton;
    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;
    @FindBy(className = "invalid-feedback")
    WebElement incorrectEmailMessage;

    private void logIn(String email, String password) {
        driver.get(UrlsUtils.getE2EPageUrl());
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginButton.click();
    }

    public ProductsPage logIn() {
        String email = PropertiesUtils.getProperty(propertiesFilePath, "email");
        String password = PropertiesUtils.getProperty(propertiesFilePath, "password");
        logIn(email, password);
        return new ProductsPage(driver);
    }

    public void incorrectCredentials() {
        String email = PropertiesUtils.getProperty(propertiesFilePath, "incorrectEmail");
        String  password = PropertiesUtils.getProperty(propertiesFilePath, "incorrectPassword");
        logIn(email, password);
    }

    public void incorrectEmail() {
        String email = PropertiesUtils.getProperty(propertiesFilePath, "wrongEmail");
        String  password = PropertiesUtils.getProperty(propertiesFilePath, "incorrectPassword");
        logIn(email, password);
    }

    public String getErrorMessage() {
        return WaitUtils.untilElementIsVisible(driver, errorMessage).getText();
    }

    public String getIncorrectEmailMessage() {
        return WaitUtils.untilElementIsVisible(driver, incorrectEmailMessage).getText();
    }
}