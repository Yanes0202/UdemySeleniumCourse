package lectures;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;

import java.io.File;
import java.util.HashMap;

public class FileDownloading {

    @SneakyThrows
    @Test
    void fileDownload() {
        String downloadPath = System.getProperty("user.dir");

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile,default_content_setting.popups", 0);
        chromePrefs.put("download.default_directory", downloadPath);
        EdgeOptions options = new EdgeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        WebDriver driver = new EdgeDriver(options);
        driver.get("https://examplefile.com/document/pdf/1-mb-pdf");
        driver.findElement(By.cssSelector(".purchase-button a")).click();

        Thread.sleep(300);
        File f = new File(downloadPath + "/1.pdf");
        Assert.assertTrue(f.exists());
        Assert.assertTrue(f.delete());
        Assert.assertFalse(f.exists());
    }
}
