package lectures;

import adrian.com.managers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class CheckingLinks {

    public static void main(String[] args) {
        WebDriver driver = Driver.getDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        System.out.println(driver.findElements(By.tagName("a")).size());

        WebElement footer = driver.findElement(By.id("gf-BIG"));
        List<WebElement> footerLinks = footer.findElements(By.xpath("(//ul)[1]//a"));
        String clickTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
        footerLinks.forEach(a -> a.sendKeys(clickTab));
        Set<String> windows = driver.getWindowHandles();
        windows.forEach(w -> {
            driver.switchTo().window(w);
            System.out.println(driver.getTitle());
        });
    }
}
