package assignments;

import adrian.com.managers.Driver;
import adrian.com.utils.UrlsUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class Assignment6 {

    @Test
    void assignment1() {
        WebDriver driver = Driver.getDriver();
        driver.get(UrlsUtils.getAutomationUrl());

        Random random = new Random();
        int randomInt = random.nextInt(3) + 1;
        driver.findElement(By.xpath("(//div[@id='checkbox-example']//input)[%d]".formatted(randomInt))).click();
        String selectedOption = driver.findElement(By.xpath("(//div[@id='checkbox-example']//label)[%d]".formatted(randomInt))).getText();
        System.out.println(selectedOption);

        WebElement dropdown = driver.findElement(By.id("dropdown-class-example"));
        Select dropdownSelect = new Select(dropdown);
        dropdownSelect.selectByVisibleText(selectedOption);

        driver.findElement(By.id("name")).sendKeys(selectedOption);
        driver.findElement(By.id("alertbtn")).click();

        String alertText = driver.switchTo().alert().getText();
        Assertions.assertTrue(alertText.contains(selectedOption));
    }
}
