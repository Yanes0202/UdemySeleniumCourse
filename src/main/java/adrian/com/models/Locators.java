package adrian.com.models;

import adrian.com.managers.Driver;
import adrian.com.utils.UrlsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Locators {

    WebDriver driver = Driver.getDriver();
    String url = UrlsUtils.getLocatorsUrl();

    public void logIn() {
        driver.get(url);
        driver.findElement(By.id("inputUsername")).sendKeys("rahul");
        driver.findElement(By.name("inputPassword")).sendKeys("hello123");
    }
}
