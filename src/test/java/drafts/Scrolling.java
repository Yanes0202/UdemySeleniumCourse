package drafts;

import adrian.com.managers.Driver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Scrolling {

    @Test
    void scrolling() {
        WebDriver driver = Driver.getDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //  scroll page down
        js.executeScript("window.scrollBy(0,500)");

        // scroll div table down
        js.executeScript("document.querySelector('.tableFixHead').scrollTop=500");

        List<WebElement> elements = driver.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"));

        int actual = elements.stream().mapToInt(e -> Integer.parseInt(e.getText())).sum();
        System.out.println(actual);
        int expected = Integer.parseInt(driver.findElement(By.cssSelector(".totalAmount")).getText().split(":")[1].trim());

        Assertions.assertEquals(actual, expected);

    }
}
