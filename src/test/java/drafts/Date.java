package drafts;

import adrian.com.managers.Driver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Date {

    @Test
    void test() {
        String day = "15";
        String month = "6";
        String year = "2027";

        WebDriver driver = Driver.getDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        driver.findElement(By.className("react-date-picker__inputGroup")).click();
        driver.findElement(By.cssSelector(".react-calendar__navigation__label")).click();
        driver.findElement(By.cssSelector(".react-calendar__navigation__label")).click();
        driver.findElement(By.xpath("//button[text()='%s']".formatted(year))).click();
        driver.findElement(By.xpath("(//button[contains(@class,'react-calendar__year-view__months__month')])[%s]".formatted(month))).click();
        driver.findElement(By.xpath("//abbr[text()='%s']".formatted(day))).click();

        List<WebElement> elements = driver.findElements(By.cssSelector(".react-date-picker__inputGroup__input"));
        elements.forEach(e -> System.out.println(e.getAttribute("value")));
        Assertions.assertTrue(elements.stream().filter(e -> e.getAttribute("name").equals("day")).allMatch(e -> e.getAttribute("value").equals(day)));
        Assertions.assertTrue(elements.stream().filter(e -> e.getAttribute("name").equals("month")).allMatch(e -> e.getAttribute("value").equals(month)));
        Assertions.assertTrue(elements.stream().filter(e -> e.getAttribute("name").equals("year")).allMatch(e -> e.getAttribute("value").equals(year)));
    }
}
