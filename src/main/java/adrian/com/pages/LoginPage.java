package adrian.com.pages;

import adrian.com.managers.Driver;
import adrian.com.utils.PropertiesUtils;
import adrian.com.utils.UrlsUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    private static final String propertiesFilePath = "src/main/resources/common/credentials.properties";

    public LoginPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userPassword")
    WebElement userPassword;
    @FindBy(id = "login")
    WebElement loginButton;

    public ProductsPage logIn() {
        driver.get(UrlsUtils.getE2EPageUrl());
        userEmail.sendKeys(PropertiesUtils.getProperty(propertiesFilePath, "email"));
        userPassword.sendKeys(PropertiesUtils.getProperty(propertiesFilePath, "password"));
        loginButton.click();
        return new ProductsPage();
    }
}
