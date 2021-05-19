package ui.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends MainPage {

    @FindBy(xpath = "//a[contains(@onclick, 'addToCart')]")
    private WebElement addToCartBtn;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProductPage clickAddToCartBtn() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        addToCartBtn.click();

        return this;
    }

    public ProductPage clickOkAlert() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        return this;
    }
}
