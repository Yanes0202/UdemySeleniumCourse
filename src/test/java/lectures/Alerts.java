package lectures;

import adrian.com.managers.Driver;
import adrian.com.utils.UrlsUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Alerts {

    @Test
    void handlingAlerts() {
        WebDriver driver = Driver.getDriver();
        driver.get(UrlsUtils.getAutomationPageUrl());
        driver.findElement(By.id("name")).sendKeys("Name");
        driver.findElement(By.id("alertbtn")).click();
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();

        driver.findElement(By.id("name")).sendKeys("Name");
        driver.findElement(By.id("confirmbtn")).click();
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().dismiss();
    }
}
