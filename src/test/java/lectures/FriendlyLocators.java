package lectures;

import adrian.com.managers.Driver;
import adrian.com.utils.UrlsUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

// THIS IS IMPORTANT!!! ide does not suggest this import automatically
import static org.openqa.selenium.support.locators.RelativeLocator.*;

public class FriendlyLocators {

    // WORKS ON SELENIUM 4
    // Important note: Friendly Locators will not recognize flex elements from html! It will take anything after/before that instead!

    static WebDriver driver;

    @BeforeAll
    static void driverInit() {
        driver = Driver.getDriver();
        driver.get(UrlsUtils.getAngularPageUrl());
    }

    @Test
    void usageOfAbove() {
        WebElement input = driver.findElement(By.name("name"));
        String labelText = driver.findElement(with(By.tagName("label")).above(input)).getText();

        Assert.assertEquals(labelText, "Name");
    }

    @Test
    void usageOfBelow() {
        WebElement label = driver.findElement(By.cssSelector("[for='dateofBirth']"));
        driver.findElement(with(By.tagName("input")).below(label)).click(); //It takes submit button instead of date input

        String submitText = driver.findElement(By.cssSelector("div[class*='alert-success']")).getText();
        Assert.assertFalse(submitText.isEmpty());
    }

    @Test
    void usageOfToLeftOf() {
        WebElement iceCreamLabel = driver.findElement(By.xpath("//label[@for='exampleCheck1']"));
        driver.findElement(with(By.tagName("input")).toLeftOf(iceCreamLabel)).click();

        WebElement iceCreamInput = driver.findElement(By.xpath("//input[@id='exampleCheck1']"));
        Assert.assertTrue(iceCreamInput.isSelected());
    }

    @Test
    void usageOfToRightOf() {
        WebElement radio = driver.findElement(By.id("inlineRadio1"));
        String radioText = driver.findElement(with(By.tagName("label")).toRightOf(radio)).getText();

        Assert.assertEquals(radioText, "Student");
    }

}
