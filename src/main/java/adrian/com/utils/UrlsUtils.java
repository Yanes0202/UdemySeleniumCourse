package adrian.com.utils;

public class UrlsUtils {
    private static final String urlPath = "src/main/resources/urls/urls.properties";

    public static String getLocatorsPageUrl() {
        return PropertiesUtils.getProperty(urlPath, "locatorsPage");
    }

    public static String getAutomationPageUrl() {
        return PropertiesUtils.getProperty(urlPath, "automationPage");
    }

    public static String getAngularPageUrl() {
        return PropertiesUtils.getProperty(urlPath, "angularPage");
    }

    public static String getAngularAppDemoPageUrl() {
        return PropertiesUtils.getProperty(urlPath, "angularAppDemoPage");
    }

    public static String getLoginPageUrl() {
        return PropertiesUtils.getProperty(urlPath, "loginPage");
    }

    public static String getE2EPageUrl() {
        return PropertiesUtils.getProperty(urlPath, "e2ePage");
    }

    public static String getUploadPageUrl() {
        return PropertiesUtils.getProperty(urlPath, "uploadPage");
    }
}