package lectures;

import adrian.com.utils.UrlsUtils;
import adrian.com.utils.WaitUtils;
import com.google.common.collect.ImmutableList;
import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v125.browser.Browser;
import org.openqa.selenium.devtools.v125.browser.model.PermissionType;
import org.openqa.selenium.devtools.v127.fetch.Fetch;
import org.openqa.selenium.devtools.v127.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v127.network.Network;
import org.openqa.selenium.devtools.v127.network.model.ConnectionType;
import org.openqa.selenium.devtools.v127.network.model.ErrorReason;
import org.openqa.selenium.devtools.v127.network.model.Request;
import org.openqa.selenium.devtools.v127.network.model.Response;
import org.openqa.selenium.devtools.v85.emulation.Emulation;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URI;
import java.util.*;
import java.util.function.Predicate;

public class ChromeDevToolsProtocols {

    /** Documentation: <a href="https://chromedevtools.github.io/devtools-protocol">Link</a> */

    @Test
    void usingSendCDPSendCommand() {
        EdgeDriver driver = new EdgeDriver();
        DevTools devTools = driver.getDevTools();

        devTools.createSession();

        devTools.send(Emulation.setDeviceMetricsOverride(600, 1000, 50, true,
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));

        driver.get(UrlsUtils.getAngularAppDemoPageUrl());
        driver.findElement(By.cssSelector(".navbar-toggler")).click();

        WaitUtils.untilElementIsClickable(driver, By.linkText("Library")).click();
    }

    @Test
    void usingExecuteCDPCommand() {
        EdgeDriver driver = new EdgeDriver();
        DevTools devTools = driver.getDevTools();

        devTools.createSession();

        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 600);
        deviceMetrics.put("height", 1000);
        deviceMetrics.put("deviceScaleFactor", 50);
        deviceMetrics.put("mobile", true);

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        driver.get(UrlsUtils.getAngularAppDemoPageUrl());
        driver.findElement(By.cssSelector(".navbar-toggler")).click();

        WaitUtils.untilElementIsClickable(driver, By.linkText("Library")).click();
    }

    @Test
    void localizationTesting() {
        /** Unfortunately not working */
        EdgeDriver driver = new EdgeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Emulation.setGeolocationOverride(
                Optional.of(40.7128),
                Optional.of(-74.0060),
                Optional.of(1.0)
        ));

        driver.get("http://google.com");

        devTools.send(Browser.grantPermissions(
                Collections.singletonList(PermissionType.GEOLOCATION),
                Optional.of(driver.getCurrentUrl()),
                Optional.empty()
        ));

        driver.findElement(By.id("W0wltc")).click();
        driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);
        driver.findElements(By.cssSelector(".LC20lb")).get(0).click();
        String title = driver.findElement(By.cssSelector(".our-story-card-title")).getText();
        System.out.println(title);
    }

    @Test
    void requestListeners() {
        EdgeDriver driver = new EdgeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        devTools.addListener(Network.requestWillBeSent(), request -> {
            Request req = request.getRequest();
            System.out.println(req.getUrl());
        });

        devTools.addListener(Network.responseReceived(), response -> {
            Response res = response.getResponse();
            if (res.getStatus().toString().startsWith("4")) {
                System.out.println(res.getUrl() + " is failing with status code " + res.getStatus());
            }
        });

        driver.get(UrlsUtils.getAngularAppDemoPageUrl());
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();

    }

    @Test
    void mockingRequests() {
        EdgeDriver driver = new EdgeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));
        devTools.addListener(Fetch.requestPaused(), requestPaused -> {
            Request request = requestPaused.getRequest();
            if (request.getUrl().contains("=shetty")) {
                String mockedUrl = request.getUrl().replace("=shetty", "=BadGuy");

                devTools.send(Fetch.continueRequest(requestPaused.getRequestId(), Optional.of(mockedUrl), Optional.of(request.getMethod()), Optional.empty(), Optional.empty(), Optional.empty()));
            } else {
                devTools.send(Fetch.continueRequest(requestPaused.getRequestId(), Optional.of(request.getUrl()), Optional.of(request.getMethod()), Optional.empty(), Optional.empty(), Optional.empty()));
            }
        });

        driver.get(UrlsUtils.getAngularAppDemoPageUrl());
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();

        String actualText = WaitUtils.untilElementIsPresent(driver, By.cssSelector("p")).getText();
        Assert.assertEquals(actualText,"Oops only 1 Book available");
    }

    @Test
    void failingRequests() {
        EdgeDriver driver = new EdgeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        Optional<List<RequestPattern>> patterns =Optional.of(List.of(new RequestPattern(Optional.of("*GetBook*"), Optional.empty(), Optional.empty())));
        devTools.send(Fetch.enable(patterns, Optional.empty()));

        devTools.addListener(Fetch.requestPaused(), requestPaused -> {
            devTools.send(Fetch.failRequest(requestPaused.getRequestId(), ErrorReason.FAILED));
        });

        driver.get(UrlsUtils.getAngularAppDemoPageUrl());
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();

        Assert.assertTrue(driver.findElements(By.cssSelector("tbody tr")).isEmpty());
    }

    @Test
    void blockingRequests() {
        EdgeDriver driver = new EdgeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg", "*.css")));

        driver.get(UrlsUtils.getAngularAppDemoPageUrl());
        driver.findElement(By.linkText("Browse Products")).click();
        driver.findElement(By.linkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        System.out.println(driver.findElement(By.cssSelector("p")).getText());
    }

    @Test
    void slowingDownNetwork() {
        EdgeDriver driver = new EdgeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(false, 3000, 20000, 10000, Optional.of(ConnectionType.ETHERNET),
                Optional.empty(), Optional.empty(), Optional.empty()));

        driver.get(UrlsUtils.getAngularAppDemoPageUrl());
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
    }

    @Test
    void basicAuthentication() {
        EdgeDriver driver = new EdgeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        Predicate<URI> uriPredicate = uri -> uri.getHost().contains("httpbin.org");

        ((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of("foo", "bar"));
        driver.get("http://httpbin.org/basic-auth/foo/bar");
    }

    @Test
    void logJSErrors() {
        EdgeDriver driver = new EdgeDriver();

        driver.manage().window().maximize();

        driver.get(UrlsUtils.getAngularAppDemoPageUrl());
        driver.findElement(By.linkText("Browse Products")).click();
        driver.findElement(By.partialLinkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        driver.findElement(By.linkText("Cart")).click();
        driver.findElement(By.id("exampleInputEmail1")).clear();
        driver.findElement(By.id("exampleInputEmail1")).sendKeys("2");

        LogEntries entries = driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> logs = entries.getAll();

        logs.forEach(log -> {
            System.out.println(log.getMessage());
        });

    }
}
