package lectures;

import adrian.com.managers.Driver;
import adrian.com.utils.UrlsUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import java.util.Iterator;
import java.util.Set;

public class NewWindows {

    @Test
    void tab() {
        WebDriver driver = Driver.getDriver();
        driver.get(UrlsUtils.getAngularUrl());

        driver.switchTo().newWindow(WindowType.TAB); //opening new tab
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> iterator = windows.iterator();
        String parentId = iterator.next();
        String childId = iterator.next();
        driver.switchTo().window(childId);
        driver.get("https://rahulshettyacademy.com/");
        String courseName = driver.findElement(By.cssSelector("h2 a[href*='https://courses.rahulshettyacademy.com/p/']")).getText();
        driver.switchTo().window(parentId);
        WebElement nameInput = driver.findElement(By.cssSelector("[name='name']"));
        nameInput.sendKeys(courseName);

        Assertions.assertEquals(nameInput.getAttribute("value"), courseName);
    }

    @Test
    void windows() {
        WebDriver driver = Driver.getDriver();
        driver.get(UrlsUtils.getAngularUrl());

        driver.switchTo().newWindow(WindowType.WINDOW); //opening new window
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> iterator = windows.iterator();
        String parentId = iterator.next();
        String childId = iterator.next();
        driver.switchTo().window(childId);
        driver.get("https://rahulshettyacademy.com/");
        String courseName = driver.findElement(By.cssSelector("h2 a[href*='https://courses.rahulshettyacademy.com/p/']")).getText();
        driver.switchTo().window(parentId);
        WebElement nameInput = driver.findElement(By.cssSelector("[name='name']"));
        nameInput.sendKeys(courseName);

        Assertions.assertEquals(nameInput.getAttribute("value"), courseName);
    }
}
