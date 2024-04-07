package adrian.com.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertiesUtils {

    public static String getProperty(String propertiesPath, String property) {
        try {
            InputStream propFile = new FileInputStream(propertiesPath);
            Properties properties = new Properties();
            properties.load(propFile);

            return properties.getProperty(property);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}