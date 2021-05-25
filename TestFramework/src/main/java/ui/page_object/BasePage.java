package ui.page_object;

import business_object.User;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.driver_interactions.Driver;

import java.time.Duration;

public class BasePage {

    private static final User USER = User.getUser();
    protected static final Duration WAIT = Duration.ofSeconds(1);

    protected WebDriver driver = Driver.getWebDriver();

    protected BasePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "login2")
    private WebElement logInBtn;

    @FindBy(id = "loginusername")
    private WebElement usernameField;

    @FindBy(id = "loginpassword")
    private WebElement passwordField;

    @FindBy(css = "[onclick^='logIn']")
    private WebElement logInConfirmBtn;

    @FindBy(id = "nameofuser")
    private WebElement welcomeLine;

    @CacheLookup
    @FindBy(xpath = "//li/a[@href = 'index.html']")
    private WebElement homePageBtn;

    @CacheLookup
    @FindBy(id = "cartur")
    private WebElement cartPageBtn;

    @FindBy(id = "logout2")
    private WebElement logOutBtn;

    public BasePage clickLogInBtn() {
        logInBtn.click();

        return this;
    }

    public BasePage setUsername() {
        usernameField.clear();
        usernameField.sendKeys(USER.getUsername());

        return this;
    }

    public BasePage setPassword() {
        passwordField.clear();
        passwordField.sendKeys(USER.getPassword());

        return this;
    }

    public void clickLogInConfirmBtn() {
        logInConfirmBtn.click();
    }

    public void login() {
        clickLogInBtn()
                .setUsername()
                .setPassword()
                .clickLogInConfirmBtn();
    }

    public String getWelcomeLine() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(welcomeLine));

        return welcomeLine.getText();
    }

    public CartPage clickCartPageBtn() {
        cartPageBtn.click();

        return new CartPage();
    }

    public HomePage clickHomePage() {
        homePageBtn.click();

        return new HomePage();
    }

    public void clickLogOutBtn() {
        logOutBtn.click();
    }

    public boolean isLogInBtnDisplayed() {
        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(logInBtn));
            return logInBtn.isDisplayed();
        } catch (TimeoutException e) {
            return logInBtn.isDisplayed();
        }
    }

}
