package drafts;

import adrian.com.managers.Driver;
import adrian.com.utils.UrlsUtils;
import adrian.com.utils.WaitUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class LocatorsTest extends AbstractTest {

    @Test
    void waitUntil() {
        WebDriver driver = Driver.getDriver();
        String url = UrlsUtils.getLocatorsUrl();
        driver.get(url);
        driver.findElement(By.id("inputUsername")).sendKeys("rahul");
        driver.findElement(By.name("inputPassword")).sendKeys("hello123");
        driver.findElement(By.className("signInBtn")).click();
        WaitUtils.untilElementIsPresent(By.cssSelector("p.error"));
        String text = driver.findElement(By.cssSelector("p.error")).getText();
        System.out.println(text);
        driver.findElement(By.linkText("Forgot your password?")).click();
        WaitUtils.untilElementIsVisible(By.xpath("//input[@placeholder='Name']"));
        driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("John");
        driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("john@rsa.com");
        driver.findElement(By.xpath("//input[@type='text'][2]")).clear();
        driver.findElement(By.cssSelector("input[type='text']:nth-child(3)")).sendKeys("john@rsa.com");
        driver.findElement(By.xpath("//form/input[3]")).sendKeys("9864353253");
        driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
        WaitUtils.untilElementIsPresent(By.cssSelector("form p"));
        String text1 = driver.findElement(By.cssSelector("form p")).getText();
        System.out.println(text1);

    }

    @Test
    void implicitWait() throws InterruptedException {
        WebDriver driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String url = UrlsUtils.getLocatorsUrl();
        driver.get(url);
        driver.findElement(By.id("inputUsername")).sendKeys("rahul");
        driver.findElement(By.name("inputPassword")).sendKeys("hello123");
        driver.findElement(By.className("signInBtn")).click();
        String text = driver.findElement(By.cssSelector("p.error")).getText();
        System.out.println(text);
        driver.findElement(By.linkText("Forgot your password?")).click();
        driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("John");
        driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("john@rsa.com");
        driver.findElement(By.xpath("//input[@type='text'][2]")).clear();
        driver.findElement(By.cssSelector("input[type='text']:nth-child(3)")).sendKeys("john@rsa.com");
        driver.findElement(By.xpath("//form/input[3]")).sendKeys("9864353253");
        WaitUtils.untilElementIsClickable(By.cssSelector("button.reset-pwd-btn"));
        driver.findElement(By.cssSelector("button.reset-pwd-btn")).click();
        String text1 = driver.findElement(By.cssSelector("form p")).getText();
        System.out.println(text1);
        driver.findElement(By.xpath("//div[@class='forgot-pwd-btn-conainer']/button[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#inputUsername")).sendKeys("rahul");
        driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys("rahulshettyacademy");
        driver.findElement(By.id("chkboxOne")).click();
        driver.findElement(By.xpath("//button[contains(@class,'submit')]")).click();
    }
}
