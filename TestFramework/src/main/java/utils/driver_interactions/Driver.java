package utils.driver_interactions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.driver_interactions.driver_managers.ChromeManager;
import utils.driver_interactions.driver_managers.EdgeManager;

public class Driver {

    private static final Logger LOGGER = Logger.getLogger(Driver.class);

    private static WebDriver driver = null;

    private Driver() {
    }

    public static WebDriver getWebDriver(BrowserType browserType) {
        if (driver == null) {
            switch (browserType) {
                case CHROME:
                    driver = getChromeDriver();
                    LOGGER.info("The ChromeDriver was setup");
                    break;
                case EDGE:
                    driver = getEdgeDriver();
                    LOGGER.info("The EdgeDriver was setup");
                    break;
                default:
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        LOGGER.fatal("Incorrect browser type");
                    }
            }
        }
        return driver;
    }

    private static WebDriver getChromeDriver() {
        return new ChromeManager().initialize();
    }

    private static WebDriver getEdgeDriver() {
        return new EdgeManager().initialize();
    }

    public static void close() {
        driver.quit();
        driver = null;
        LOGGER.info("The driver was closed");
    }
}
