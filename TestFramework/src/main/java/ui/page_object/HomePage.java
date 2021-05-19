package ui.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage extends MainPage {

    @FindBy(xpath = "//a[@class = 'hrefch']")
    private List<WebElement> allPageProductsList;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static HomePage Login(WebDriver driver) {
        HomePage homePage = new HomePage(driver);
        homePage.clickLogInBtn()
                .setUsername()
                .setPassword()
                .clickLogInConfirmBtn();

        return homePage;
    }

    public ProductPage clickProductByName(String name) {
        for (WebElement element : allPageProductsList) {
            if (element.getText().equals(name)) {
                new WebDriverWait(driver, 3)
                        .until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                break;
            }
        }

        return new ProductPage(driver);
    }
}
