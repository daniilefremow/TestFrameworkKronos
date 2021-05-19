package utils.driver_interactions.driver_managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.driver_interactions.Initializable;

public class ChromeManager implements Initializable {
    @Override
    public WebDriver initialize() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        return new ChromeDriver();
    }
}
