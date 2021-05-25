package ui.page_object;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

    @FindBy(css = "[onclick^='add']")
    private WebElement addToCartBtn;

    public ProductPage() {
        super();
    }

    public ProductPage clickAddToCartBtn() {
        addToCartBtn.click();

        return this;
    }

    public ProductPage clickOkAlert() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.alertIsPresent()).accept();

        return this;
    }
}
