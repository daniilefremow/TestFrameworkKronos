package ui.page_object;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(css = ".hrefch")
    private List<WebElement> allPageProductsList;

    public HomePage() {
        super();
    }

    public ProductPage clickProductByName(String name) {
        for (WebElement element : allPageProductsList) {
            if (element.getText().equals(name)) {
                element.click();
                break;
            }
        }

        return new ProductPage();
    }
}
