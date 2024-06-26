package assignments;

import adrian.com.managers.Driver;
import adrian.com.utils.UrlsUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Assignment1 {

    @Test
    void firstAssignment() throws InterruptedException {
        WebDriver driver = Driver.getDriver();
        driver.get(UrlsUtils.getAutomationUrl());
        driver.findElement(By.id("checkBoxOption1")).click();
        Assertions.assertTrue(driver.findElement(By.id("checkBoxOption1")).isSelected());
        driver.findElement(By.id("checkBoxOption1")).click();
        Thread.sleep(200);
        Assertions.assertFalse(driver.findElement(By.id("checkBoxOption1")).isSelected());
    }

    @Test
    void secondAssignment() {
        WebDriver driver = Driver.getDriver();
        driver.get(UrlsUtils.getAutomationUrl());
        System.out.println(driver.findElements(By.cssSelector("input[type='checkbox']")).size());
    }
}
