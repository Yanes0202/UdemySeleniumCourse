package lectures;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AddProxy {

    public static void main(String[] args) {
        // How to add proxy
        ChromeOptions options = new ChromeOptions();
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("ipaddress:4444");
        options.setCapability("proxy", proxy);

        WebDriver driver = new ChromeDriver(options);

        // https://developer.chrome.com/docs/chromedriver/capabilities?hl=pl
    }
}
