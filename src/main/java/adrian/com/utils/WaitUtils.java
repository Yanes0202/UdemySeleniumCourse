package adrian.com.utils;

import adrian.com.managers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class WaitUtils {

    public static int time() {
        return Integer.parseInt(Objects.requireNonNull(PropertiesUtils.getProperty("src/main/resources/common/time.properties", "waitTime")));
    }

    public static void untilElementIsInvisible(By by) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(time()));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static void untilElementIsInvisible(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time()));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(by)));
    }

    public static WebElement untilElementIsClickable(By by) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(time()));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static WebElement untilElementIsClickable(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time()));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static WebElement untilElementIsClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time()));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement untilElementIsPresent(By by) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(time()));
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static WebElement untilElementIsPresent(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time()));
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static WebElement untilElementIsVisible(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time()));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static WebElement untilElementIsVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time()));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void untilUrlIs(String url) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(time()));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public static boolean ifElementIsVisible(WebDriver driver, By by){
        try { untilElementIsVisible(driver, by);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public static void forElementTextEqualString(WebDriver driver, By by, String expectedText) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(time()));
        ExpectedCondition<Boolean> elementEqualsString =
                arg0 -> untilElementIsVisible(driver, by).getText().contains(expectedText);
        wait.until(elementEqualsString);
    }

    public static List<WebElement> untilElementsAreVisible(By by) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(time()));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }
}