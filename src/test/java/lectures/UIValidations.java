package lectures;

import adrian.com.managers.Driver;
import adrian.com.utils.UrlsUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UIValidations {

    static WebDriver driver;

    @BeforeAll
    static void driverInit() {
        driver = Driver.getDriver();
        driver.manage().window().maximize(); // Not mandatory but may be important for correct validations of ui elements
        driver.get(UrlsUtils.getAngularUrl());
    }

    @Test
    void height() {
        WebElement nameInput = driver.findElement(By.name("name"));
        int elementHeight = nameInput.getRect().getDimension().getHeight(); // storing element height

        Assertions.assertEquals(38, elementHeight); // assert that element have expected height
    }

    @Test
    void width() {
        WebElement nameInput = driver.findElement(By.name("name"));
        int elementWidth = nameInput.getRect().getDimension().getWidth(); // storing element width

        Assertions.assertEquals(1110, elementWidth); // assert that element have expected width
    }
}
