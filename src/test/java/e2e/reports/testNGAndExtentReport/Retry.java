package e2e.reports.testNGAndExtentReport;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer  {

    /** YOU NEED TO ADD in TESTNG annotation @Test(retryAnalyzer=[CLASS_METHOD]) */

    int count = 0;
    int maxTry = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (count < maxTry)
        {
            count++;
            return true;
        }
        return false;
    }
}
