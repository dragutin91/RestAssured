package utility;



import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Dragutin Misiraca
 *
 */

public class ConfigProperties {

    private static final Properties PROPERTIES=readPropertiesFile();
    public static final String USER=PROPERTIES.getProperty("user");
    public static final String ENV=PROPERTIES.getProperty("env");





    private static Properties readPropertiesFile() {
         String result = "";
         InputStream inputStream;

        Properties properties = new Properties();
        String propFileName = "config.properties";
        try {
            inputStream = ConfigProperties.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return properties;
    }
}