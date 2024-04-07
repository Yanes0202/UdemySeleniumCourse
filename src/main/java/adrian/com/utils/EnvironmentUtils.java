package adrian.com.utils;

import adrian.com.commons.enums.Environment;

public class EnvironmentUtils {

    private static final String propertiesFilePath = "src/main/resources/common/env.properties";

    public static Environment getEnvironment() {
        String propertyValue = System.getProperty("test.env");
        if (propertyValue == null) {
            propertyValue = PropertiesUtils.getProperty(propertiesFilePath, "test.env");
        }
        return Environment.valueOf((propertyValue != null) ? propertyValue : "DEV");
    }
}
