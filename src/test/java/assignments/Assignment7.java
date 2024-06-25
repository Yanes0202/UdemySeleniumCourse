package assignments;

import adrian.com.managers.Driver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Assignment7 {

    @Test
    void assignment1() {
        WebDriver driver = Driver.getDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement table = driver.findElement(By.name("courses"));

        System.out.println(table.findElements(By.tagName("tr")).size());
        Assertions.assertEquals(table.findElements(By.tagName("tr")).size(), 11);
        System.out.println(table.findElements(By.tagName("th")).size());
        Assertions.assertEquals(table.findElements(By.tagName("th")).size(), 3);

        List<WebElement> tableElements = table.findElements(By.xpath("(//tr)[3]/td"));

        tableElements.forEach(e -> System.out.println(e.getText()));
    }
}
