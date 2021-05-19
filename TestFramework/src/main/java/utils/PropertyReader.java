package utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static final Logger LOGGER = Logger.getLogger(PropertyReader.class);

    public static String getProperty(String property) {
        String propertyValue = null;
        try {
            InputStream input = new FileInputStream("configuration/configuration.properties");
            Properties prop = new Properties();
            prop.load(input);
            propertyValue = prop.getProperty(property);
        } catch (IOException e) {
            LOGGER.fatal("Missing '" + property + "' property");
        }

        return propertyValue;
    }
}