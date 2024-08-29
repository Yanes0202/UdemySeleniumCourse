package e2e;

import adrian.com.managers.Driver;
import adrian.com.pages.LoginPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public abstract class AbstractTest {

    public WebDriver driver;
    protected static LoginPage loginPage;
    private static final String screenshotPath = System.getProperty("user.dir") + "/src/test/java/resources/reports/screenshots/%s.png";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Driver.quitDriver();
    }

    public String getScreenShot(WebDriver driver, String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(screenshotPath.formatted(testCaseName));
        FileUtils.copyFile(source, file);
        return screenshotPath.formatted(testCaseName);
    }
}