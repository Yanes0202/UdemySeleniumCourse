package lectures;

import adrian.com.managers.Driver;
import adrian.com.utils.UrlsUtils;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class ScreenShots {

    @Test
    void pageSS() throws IOException {
        WebDriver driver = Driver.getDriver();

        driver.get("https://www.google.com/");

        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "//pageSS.png"));
        file = FileUtils.getFile(System.getProperty("user.dir") + "//pageSS.png");
        Assertions.assertTrue(file.exists());
        file.delete();
        Assertions.assertFalse(file.exists());
    }

    @Test
    void webElementSS() throws IOException {
        WebDriver driver = Driver.getDriver();
        driver.get(UrlsUtils.getAngularPageUrl());

        WebElement nameInput = driver.findElement(By.cssSelector("[name='name']"));
        nameInput.sendKeys("Java Test Automation");

        File file = nameInput.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "//webElementSS.png"));
        file = FileUtils.getFile(System.getProperty("user.dir") + "//webElementSS.png");
        Assertions.assertTrue(file.exists());
        file.delete();
        Assertions.assertFalse(file.exists());
    }
}
