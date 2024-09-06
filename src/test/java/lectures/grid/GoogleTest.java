package lectures.grid;

import lombok.SneakyThrows;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.URI;

public class GoogleTest {

    @Test
    @SneakyThrows
    void titleCheck() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("firefox");
//        caps.setPlatform(Platform.);
//        caps.setCapability(CapabilityType.BROWSER_NAME, "firefox");
//        caps.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        WebDriver driver = new RemoteWebDriver(new URI("http://[HUB_IP]:[HUB_PORT]").toURL(), caps);
        driver.get("https://www.google.com/");
        System.out.println(driver.getTitle());
    }
}
