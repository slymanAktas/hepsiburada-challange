package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class Config {

    public static final String DEFAULT_BROWSER_NAME;
    public static final String API_URL;
    public static final String API_BASE_PATH;
    private static final Properties properties;

    static {
        properties = loadProperties();
        API_URL = properties.getProperty("petstore.baseUrl");
        API_BASE_PATH = properties.getProperty("petstore.user.basePath");
        DEFAULT_BROWSER_NAME = getBrowser();
    }

    private static String getBrowser() {
        String browser = System.getProperties().getProperty("browser");
        browser = browser == null ? "chrome" : browser; // Default browser is chrome

        if (isBlank(browser)) {
            throw new IllegalArgumentException("No browser option is set, please set -Dbrowser in java");
        }

        return browser;
    }

    private static Properties loadProperties() {
        String configFileName = "properties/config.properties";
        InputStream in = ClassLoader.getSystemResourceAsStream(configFileName);
        Properties properties = new Properties();

        try {
            properties.load(in);
        } catch (IOException ioe) {
            throw new IllegalStateException("Exception on loading {" + configFileName + "} conf file from classpath", ioe);
        }
        return properties;
    }

}
