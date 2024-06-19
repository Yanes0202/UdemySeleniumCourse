package adrian.com.managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class Driver {
    private static final ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
    private static final ChromeOptions chromeOptions = new ChromeOptions();
    private static final LoggingPreferences loggingPreferences = new LoggingPreferences();


    public static synchronized WebDriver getDriver() {
        if(drivers.get() == null) {
            createDriver();
        }
        return drivers.get();
    }

    public static synchronized void quitDriver() {
        drivers.get().quit();
        drivers.remove();
    }

    public static synchronized void createDriver() {
        String remote = System.getProperty("remote");

        if(remote == null || remote.equals("false")) {
            //WebDriverManager.chromedriver().setup();
            chromeOptions.setCapability("goog:loggingPrefs", loggingPreferences);
            chromeOptions.addArguments("--window-size=1920,1080");
            drivers.set(new ChromeDriver(chromeOptions));
        } else if(remote.equals("true")) {
            ChromeOptions opt = new ChromeOptions();
            try {
                drivers.set(new RemoteWebDriver(new URL("url do zdalnego webdrivera"), opt));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
}