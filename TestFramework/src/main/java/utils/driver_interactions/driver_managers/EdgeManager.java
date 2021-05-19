package utils.driver_interactions.driver_managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import utils.driver_interactions.Initializable;

public class EdgeManager implements Initializable {
    @Override
    public WebDriver initialize() {
        System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");

        return new EdgeDriver();
    }
}
