package adrian.com.managers;

import adrian.com.utils.PropertiesUtils;
import lombok.SneakyThrows;

import java.sql.*;

public class DbConnector {
    private static final String dbPropertiesFilePath = "/src/main/resources/common/db.properties";

    private static String getDBProperty(String propertyName) {
        return PropertiesUtils.getProperty(System.getProperty("user.dir") + dbPropertiesFilePath, propertyName);
    }

    private static String getConnectionUrl() {
        return "%s://%s:%d/%s".formatted(getDBProperty("db_url"), getDBProperty("db_host"),
                Integer.parseInt(getDBProperty("db_port")), getDBProperty("db_name"));
    }

    @SneakyThrows
    private static Connection connect() {
        return DriverManager.getConnection(getConnectionUrl(), getDBProperty("db_user"), getDBProperty("db_password"));
    }

    @SneakyThrows
    public static ResultSet executeQuery(String query) {
        Connection connection = connect();
        Statement s = connection.createStatement();
        return s.executeQuery(query);
    }
}
