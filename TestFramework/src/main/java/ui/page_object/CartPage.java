package ui.page_object;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//tbody//td[2]")
    private List<WebElement> productNameList;

    @FindBy(css = "[onclick^='delete']")
    private List<WebElement> deleteLinksList;

    @FindBy(id = "name")
    private WebElement orderNameField;

    @FindBy(id = "card")
    private WebElement orderCardField;

    @FindBy(css = ".btn-success")
    private WebElement placeOrderBtn;

    @FindBy(css = "[onclick^='purchase']")
    private WebElement purchaseBtn;

    @FindBy(css = ".sweet-alert > h2")
    private WebElement purchaseConfirmTitle;

    @FindBy(css = ".confirm")
    private WebElement purchaseConfirmBtn;

    public CartPage() {
        super();
    }

    public int getNumberOfElementsInCart() {
        new Actions(driver).pause(WAIT).build().perform();
        return productNameList.size();
    }

    public int getNumberOfElementInCartByName(String name) {
        return (int) productNameList.stream()
                .filter(element -> element.getText().equals(name))
                .count();
    }

    public void deleteProductByName(String name) {
        for (int i = 0; i < productNameList.size(); i++) {
            if (productNameList.get(i).getText().equals(name)) {
                deleteLinksList.get(i).click();
                break;
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

        return new HomePage();
    }
}
