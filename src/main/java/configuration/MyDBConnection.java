package configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Database Connection manager
 */
public class MyDBConnection {

    private String sslMode = "prefer";
    public String getSslMode() {
        return sslMode;
    }

    public void setSslMode(String sslMode) {
        this.sslMode = sslMode;
    }

    /**
     * Connects with database specify in Config files
     * @return connection
     */
    public Connection connect() {
        Connection conn = null;

        Properties connProperties = new Properties();
        connProperties.setProperty("user", Config.getUSER());
        connProperties.setProperty("password", Config.getPASSWORD());
        connProperties.setProperty("sslmode", sslMode);

        try {
            conn = DriverManager.getConnection(Config.getURL(), Config.getUSER(), Config.getPASSWORD());
            System.out.println("Conected successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
