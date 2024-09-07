package lectures;

import adrian.com.commons.excel.Fruits;
import adrian.com.managers.Driver;
import adrian.com.utils.ExcelUtils;
import adrian.com.utils.UrlsUtils;
import adrian.com.utils.WaitUtils;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DownloadUploadExcel {

    final String filePath = System.getProperty("user.home") + "/downloads/download.xlsx";

    @SneakyThrows
    void deleteFileIfExist() {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    @Test
    void test () {
        deleteFileIfExist();
        int fruitId = 2;
        int desiredPrice = 100;

        WebDriver driver = Driver.getDriver();
        driver.get(UrlsUtils.getUploadPageUrl());
        WaitUtils.untilElementIsClickable(By.id("downloadButton")).click();
        List<Fruits> fruits = ExcelUtils.convertExcelToFruits(filePath);

        fruits.get(fruitId - 1).setPrice(desiredPrice);

        ExcelUtils.fruitsToExcel(filePath, fruits);

        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(filePath);

        WaitUtils.untilElementIsVisible(driver, By.cssSelector(".Toastify__toast-body div:nth-child(2)"));
        WaitUtils.untilElementIsInvisible(driver, By.cssSelector(".Toastify__toast-body div:nth-child(2)"));

        String fruitPrice = driver.findElement(By.xpath("//div[contains(@class,'rdt_TableRow')][" + fruitId + "]/div[4]/div")).getText();
        Assert.assertEquals(Integer.parseInt(fruitPrice), desiredPrice);
    }
}
