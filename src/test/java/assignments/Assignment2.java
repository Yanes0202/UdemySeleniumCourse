package assignments;

import adrian.com.managers.Driver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Assignment2 {

    @Test
    void assignment1() {
        WebDriver driver = Driver.getDriver();
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.findElement(By.name("name")).sendKeys("Test");
        driver.findElement(By.name("email")).sendKeys("test@test.com");
        driver.findElement(By.id("exampleInputPassword1")).sendKeys("test1234");
        driver.findElement(By.id("exampleCheck1")).click();
        WebElement staticDropdown = driver.findElement(By.id("exampleFormControlSelect1"));
        Select dropdown = new Select(staticDropdown);
        dropdown.selectByIndex(1);
        driver.findElement(By.id("inlineRadio1")).click();
        driver.findElement(By.name("bday")).sendKeys("18062024");
        driver.findElement(By.cssSelector("input[class*='btn-success']")).click();
        System.out.println(driver.findElement(By.cssSelector("div[class*='alert']")).getText());

    }
}
