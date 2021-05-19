package ui.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage extends MainPage {

    @FindBy(xpath = "//tbody//td[2]")
    private List<WebElement> productNameList;

    @FindBy(xpath = "//a[contains(@onclick, 'delete')]")
    private List<WebElement> deleteLinksList;

    @FindBy(xpath = "//input[@id = 'name']")
    private WebElement orderNameField;

    @FindBy(xpath = "//input[@id = 'card']")
    private WebElement orderCardField;

    @FindBy(xpath = "//button[contains(@class,'btn-success')]")
    private WebElement placeOrderBtn;

    @FindBy(xpath = "//button[contains(@onclick, 'purchase')]")
    private WebElement purchaseBtn;

    @FindBy(xpath = "//div[contains(@class, 'sweet-alert')]//h2")
    private WebElement purchaseConfirmTitle;

    @FindBy(xpath = "//button[contains(@class, 'confirm')]")
    private WebElement purchaseConfirmBtn;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getNumberOfElementsInCart() {
        new Actions(driver).pause(WAIT).build().perform();

        return productNameList.size();
    }

    public int getNumberOfElementInCartByName(String name) {
        new Actions(driver).pause(WAIT).build().perform();
        int counter = 0;
        for (WebElement webElement : productNameList) {
            if (webElement.getText().equals(name)) {
                counter++;
            }
        }

        return counter;
    }

    public void deleteProductByName(String name) {
        for (int i = 0; i < productNameList.size(); i++) {
            WebElement deleteBtn = deleteLinksList.get(i);
            if (productNameList.get(i).getText().equals(name)) {
                new Actions(driver).click(deleteBtn).build().perform();
                new WebDriverWait(driver, 3)
                        .until(ExpectedConditions.invisibilityOf(deleteBtn));
            }
        }
    }

    public CartPage clickPlaceOrderBtn() {
        new Actions(driver).pause(WAIT)
                .click(placeOrderBtn).build().perform();

        return this;
    }

    public CartPage setOrderNameField(String name) {
        orderNameField.clear();
        orderNameField.sendKeys(name);

        return this;
    }

    public CartPage setOrderCardField(String card) {
        orderCardField.clear();
        orderCardField.sendKeys(card);

        return this;
    }

    public CartPage clickPurchaseBtn() {
        new Actions(driver).pause(WAIT)
                .click(purchaseBtn).build().perform();

        return this;
    }

    public String getPurchaseConfirmText() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(purchaseConfirmTitle));

        return purchaseConfirmTitle.getText();
    }

    public HomePage clickConfirmPurchaseBtn() {
        new Actions(driver).pause(WAIT)
                .click(purchaseConfirmBtn).build().perform();

        return new HomePage(driver);
    }
}
