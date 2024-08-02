package adrian.com.pages;

import adrian.com.managers.Driver;
import adrian.com.utils.PropertiesUtils;
import adrian.com.utils.UrlsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private static final String propertiesFilePath = "src/main/resources/common/credentials.properties";

    public static void logIn() {
        WebDriver driver = Driver.getDriver();
        driver.get(UrlsUtils.getE2EPageUrl());
        driver.findElement(By.id("userEmail")).sendKeys(PropertiesUtils.getProperty(propertiesFilePath, "email"));
        driver.findElement(By.id("userPassword")).sendKeys(PropertiesUtils.getProperty(propertiesFilePath, "password"));
        driver.findElement(By.id("login")).click();
    }
}
