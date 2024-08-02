package lectures;

import adrian.com.managers.Driver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Frames {

    @Test
    void test() {
        WebDriver driver = Driver.getDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.xpath("//a[text()='Nested Frames']")).click();

        driver.switchTo().frame(driver.findElement(By.name("frame-top"))); // switching to frame
        driver.switchTo().frame(driver.findElement(By.name("frame-middle"))); // switching to frame
        System.out.println(driver.findElement(By.id("content")).getText());
    }
}
