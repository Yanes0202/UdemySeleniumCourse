package adrian.com.utils;

public class UrlsUtils {
    private static final String urlPath = "src/main/resources/urls/urls.properties";

    public static String getLocatorsUrl() {
        return PropertiesUtils.getProperty(urlPath, "locatorsUrl");
    }
}