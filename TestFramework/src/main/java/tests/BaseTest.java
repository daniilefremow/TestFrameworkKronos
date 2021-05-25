package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import test_data.OrderManager;
import ui.page_object.CartPage;
import ui.page_object.HomePage;
import ui.page_object.ProductPage;
import utils.PropertyReader;
import utils.driver_interactions.BrowserType;
import utils.driver_interactions.Driver;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    private static final String URL = PropertyReader.getProperty("url");
    protected static final Logger LOGGER = Logger.getLogger(BaseTest.class);

    protected HomePage homePage;
    protected CartPage cartPage;
    protected ProductPage productPage;

    @DataProvider(name = "orderDataProvider")
    public static Object[][] orderDataProvider() {
        return OrderManager
                .getOrderObjectArray(OrderManager.getCSVOrderList());
    }

    @BeforeClass
    public void setUp() {
        WebDriver driver = Driver.getWebDriver(BrowserType.CHROME);
        homePage = new HomePage();
        cartPage = new CartPage();
        productPage = new ProductPage();
        driver.get(URL);
        driver.manage().window().maximize();
        LOGGER.info("Url " + URL + " was opened");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        LOGGER.info("Connection was established");
    }

    @AfterClass(alwaysRun = true)
    public void cleanUp() {
        Driver.close();
    }
}

