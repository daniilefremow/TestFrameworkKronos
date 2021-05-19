package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import test_data.OrderManager;
import utils.PropertyReader;
import utils.driver_interactions.BrowserType;
import utils.driver_interactions.Driver;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    private static final String URL = PropertyReader.getProperty("url");
    protected static final Logger LOGGER = Logger.getLogger(BaseTest.class);

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        BrowserType browser = BrowserType.CHROME;
        driver = Driver.getWebDriver(browser);
        LOGGER.info(browser.name() + " browser was launched");
        driver.get(URL);
        driver.manage().window().maximize();
        LOGGER.info("Url " + URL + " was opened");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        LOGGER.info("Connection was established");
    }

    @DataProvider(name = "orderDataProvider")
    public static Object[][] orderDataProvider() {
        return OrderManager
                .getOrderObjectArray(OrderManager.getCSVOrderList());
    }

    @AfterClass
    public void cleanUp() {
        Driver.close();
    }
}

