package lectures;

import adrian.com.managers.Driver;
import adrian.com.utils.UrlsUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Requests {

    @Test
    void sendingRequests() throws IOException {
        WebDriver driver = Driver.getDriver();
        driver.get(UrlsUtils.getAutomationPageUrl());
        WebElement footer = driver.findElement(By.id("gf-BIG"));
        List<WebElement> links = footer.findElements(By.tagName("a")); // reducing number of links to the number in footer
        System.out.println(links.size());
        SoftAssert softAssert = new SoftAssert(); // using soft assertion to get all the results
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
