package e2e.unit;

import adrian.com.pages.LoginPage;
import e2e.AbstractTest;
import lectures.reports.testNGAndExtentReport.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorTest extends AbstractTest {

    @Test(groups = "ErrorHandling", retryAnalyzer = Retry.class)
    void incorrectCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.incorrectCredentials();
        Assert.assertEquals("Incorrect email password.", loginPage.getErrorMessage());
    }

    @Test
    void incorrectEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.incorrectEmail();
        Assert.assertEquals("*Enter Valid Email", loginPage.getIncorrectEmailMessage());
    }
}
