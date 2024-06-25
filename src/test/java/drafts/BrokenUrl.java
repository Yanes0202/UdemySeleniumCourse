package drafts;

import adrian.com.managers.Driver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BrokenUrl {

    @Test
    void test() throws IOException {
        WebDriver driver = Driver.getDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement footer = driver.findElement(By.id("gf-BIG"));
        List<WebElement> links = footer.findElements(By.tagName("a"));
        System.out.println(links.size());
        SoftAssert softAssert = new SoftAssert();
        for (WebElement l : links) {
            String url = l.getAttribute("href");
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();
            int responseCode = conn.getResponseCode();
            System.out.println(responseCode);
            softAssert.assertTrue(responseCode < 400, "Expected response code to be < 400 but was %d".formatted(responseCode));
        }
        softAssert.assertAll();
    }
}
