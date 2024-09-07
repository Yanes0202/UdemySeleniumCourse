package adrian.com.utils;

public class UrlsUtils {
    private static final String urlPath = "src/main/resources/urls/urls.properties";

    public static String getLocatorsUrl() {
        return PropertiesUtils.getProperty(urlPath, "locators");
    }

    public static String getAutomationUrl() {
        return PropertiesUtils.getProperty(urlPath, "automation");
    }

    public static String getAngularUrl() {
        return PropertiesUtils.getProperty(urlPath, "angular");
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