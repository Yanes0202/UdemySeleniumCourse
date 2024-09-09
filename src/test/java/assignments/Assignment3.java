package assignments;

import adrian.com.managers.Driver;
import adrian.com.utils.UrlsUtils;
import adrian.com.utils.WaitUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class Assignment3 {


    @Test
    void assignment1() {
        WebDriver driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(UrlsUtils.getLoginPageUrl());
        driver.findElement(By.name("username")).sendKeys("rahulshettyacademy");
        driver.findElement(By.name("password")).sendKeys("learning");
        driver.findElement(By.xpath("//span[contains(text(),'User')]/following-sibling::input")).click();
        WaitUtils.untilElementIsClickable(By.id("okayBtn")).click();
        WebElement staticDropDown = driver.findElement(By.cssSelector("select.form-control"));
        Select dropDown = new Select(staticDropDown);
        dropDown.selectByIndex(2);
        driver.findElement(By.id("terms")).click();
        driver.findElement(By.id("signInBtn")).click();
        WaitUtils.untilUrlIs(UrlsUtils.getAngularPageUrl() + "/shop");
        List<WebElement> elements = driver.findElements(By.xpath("//button[contains(text(),'Add')]"));
        elements.forEach(WebElement::click);
        driver.findElement(By.xpath("//a[contains(text(),'Checkout')]")).click();
    }
}
