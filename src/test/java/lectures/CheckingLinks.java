package lectures;

import adrian.com.managers.Driver;
import adrian.com.utils.UrlsUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Set;

public class CheckingLinks {

    @Test
    void test() {
        WebDriver driver = Driver.getDriver();
        driver.get(UrlsUtils.getAutomationPageUrl());
        int linksOnSite = driver.findElements(By.tagName("a")).size();

        Assert.assertEquals(linksOnSite, 27);

        WebElement footer = driver.findElement(By.id("gf-BIG")); // reducing numbers of links ('a' elements) to number presents in footer
        List<WebElement> footerLinks = footer.findElements(By.xpath("(//ul)[1]//a")); // we are using footer not driver here
        int linksInFooter = footerLinks.size();
        Assert.assertTrue(linksOnSite > linksInFooter);

        String clickTab = Keys.chord(Keys.CONTROL, Keys.ENTER); // with control + enter we are opening links in another tab
        footerLinks.forEach(a -> a.sendKeys(clickTab)); // opening every link in another tab
        Set<String> windows = driver.getWindowHandles();
        Assert.assertEquals(linksInFooter, windows.size() - 1); // reduce by main tab
        windows.forEach(w -> {
            driver.switchTo().window(w);
            System.out.println(driver.getTitle());
        });
    }
}
