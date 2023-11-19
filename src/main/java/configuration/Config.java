package configuration;

public class Config {
    private static final String URL = "jdbc:postgresql://IP:Port/YourDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    public static String getURL() {
        return URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }
}
