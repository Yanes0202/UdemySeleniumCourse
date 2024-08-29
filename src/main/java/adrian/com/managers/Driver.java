package adrian.com.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LoggingPreferences;

public class Driver {
    private static final ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
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
        /*String remote = System.getProperty("remote");

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
        }*/

        String browser = System.getProperty("browser") == null ? "chrome" : System.getProperty("browser");

        if (browser.contains("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            if (browser.contains("headless")) {
                chromeOptions.addArguments("headless");
            }
            chromeOptions.setCapability("goog:loggingPrefs", loggingPreferences);
            chromeOptions.addArguments("--window-size=1920,1080");
            drivers.set(new ChromeDriver(chromeOptions));
        } else if (browser.contains("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            if (browser.contains("headless")) {
                firefoxOptions.addArguments("headless");
            }
            firefoxOptions.setCapability("goog:loggingPrefs", loggingPreferences);
            firefoxOptions.addArguments("--window-size=1920,1080");
            drivers.set(new FirefoxDriver(firefoxOptions));
        } else if (browser.contains("edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            if (browser.contains("headless")) {
                edgeOptions.addArguments("headless");
            }
            edgeOptions.setCapability("goog:loggingPrefs", loggingPreferences);
            edgeOptions.addArguments("--window-size=1920,1080");
            drivers.set(new EdgeDriver(edgeOptions));
        }
    }
}