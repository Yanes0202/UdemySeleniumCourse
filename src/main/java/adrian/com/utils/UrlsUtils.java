package adrian.com.utils;

public class UrlsUtils {
    private static final String urlPath = "src/main/resources/urls/urls.properties";

    public static String getLocatorsUrl() {
        return PropertiesUtils.getProperty(urlPath, "locatorsUrl");
    }

    public static String getAutomationUrl() {
        return PropertiesUtils.getProperty(urlPath, "automationUrl");
    }

    public static String getAngularUrl() {
        return PropertiesUtils.getProperty(urlPath, "angularUrl");
    }

    public static String getLoginPageUrl() {
        return PropertiesUtils.getProperty(urlPath, "loginPageUrl");
    }
}