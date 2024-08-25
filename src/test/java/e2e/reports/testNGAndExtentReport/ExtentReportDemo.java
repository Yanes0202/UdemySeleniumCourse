package e2e.reports.testNGAndExtentReport;

import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ExtentReportDemo {

    ExtentReports extent;

    @Test
    void initialDemo() {
        extent.createTest("Initial Demo");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com");
        System.out.println(driver.getTitle());
        extent.flush();
    }
}
