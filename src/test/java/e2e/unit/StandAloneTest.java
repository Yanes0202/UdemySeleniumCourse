package e2e.unit;

import adrian.com.managers.Driver;
import adrian.com.pages.LoginPage;
import adrian.com.utils.WaitUtils;
import e2e.AbstractTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class StandAloneTest extends AbstractTest {

    @Test
    void selectOneProduct() {
        String desiredProduct = "ZARA COAT 3";
        WebDriver driver = Driver.getDriver();
        LoginPage.logIn();
        WaitUtils.untilElementIsVisible(By.cssSelector(".mb-3"));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement product = products.stream().filter(p -> p.findElement(By.tagName("b")).getText().equals(desiredProduct)).findFirst().orElse(null);
        Assertions.assertNotNull(product);
        product.findElement(By.cssSelector("button:last-of-type")).click();

        WaitUtils.untilElementIsPresent(By.cssSelector("#toast-container"));
        WaitUtils.untilElementIsInvisible(driver, By.cssSelector(".ng-animating"));
        WaitUtils.untilElementIsClickable(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> cartProducts = driver.findElements(By.xpath("//li[contains(@class,'items')]"));
        List<String> cartProductsName = cartProducts.stream().map(p -> p.findElement(By.tagName("h3")).getText()).toList();
        Assertions.assertTrue(cartProductsName.stream().anyMatch(p -> p.equals(desiredProduct)));

        driver.findElement(By.cssSelector(".totalRow button")).click();
        WaitUtils.untilElementIsClickable(By.className("payment"));
        //driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Pol");

        Actions actions = new Actions(driver);
        actions.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "pol").build().perform();

        WaitUtils.untilElementIsClickable(By.xpath("(//button[contains(@class,'ta-item')])[3]")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();

        WaitUtils.ifElementIsVisible(By.tagName("h1"));
        String expectedOrderHeading = "Thankyou for the order.";
        Assertions.assertTrue(expectedOrderHeading.equalsIgnoreCase(driver.findElement(By.tagName("h1")).getText()));
    }
}
