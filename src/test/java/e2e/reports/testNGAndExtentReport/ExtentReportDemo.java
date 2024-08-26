package e2e.reports.testNGAndExtentReport;

import e2e.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExtentReportDemo extends AbstractTest {

    @Test
    void initialDemo() {
        driver.get("https://rahulshettyacademy.com");
        System.out.println(driver.getTitle());
        Assert.assertTrue(false);
    }
}
