package lectures;

import adrian.com.managers.Driver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DragDrop {

    @Test
    void test() {
        WebDriver driver = Driver.getDriver();
        driver.get("https://jqueryui.com/droppable/");
        driver.switchTo().frame(0);
        Actions a = new Actions(driver);
        WebElement movingBox = driver.findElement(By.id("draggable"));
        WebElement targetedBox = driver.findElement(By.id("droppable"));

        a.dragAndDrop(movingBox, targetedBox).build().perform();
    }
}
