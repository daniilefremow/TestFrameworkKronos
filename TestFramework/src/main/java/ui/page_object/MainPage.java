package ui.page_object;

import business_object.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class MainPage {

    protected static final Duration WAIT = Duration.ofSeconds(1);
    private static final User USER = User.getUser();

    protected WebDriver driver;

    @FindBy(xpath = "//a[@id = 'login2']")
    private WebElement logInBtn;

    @FindBy(xpath = "//input[@id = 'loginusername']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id = 'loginpassword']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@onclick = 'logIn()']")
    private WebElement logInConfirmBtn;

    @FindBy(xpath = "//a[@id = 'nameofuser']")
    private WebElement welcomeLine;

    @CacheLookup
    @FindBy(xpath = "//li/a[@href = 'index.html']")
    private WebElement homePageBtn;

    @CacheLookup
    @FindBy(xpath = "//a[@id = 'cartur']")
    private WebElement cartPageBtn;

    @FindBy(xpath = "//a[@id = 'logout2']")
    private WebElement logOutBtn;

    public MainPage clickLogInBtn() {
        new Actions(driver).click(logInBtn).build().perform();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(usernameField));

        return this;
    }

    public MainPage setUsername() {
        usernameField.clear();
        usernameField.sendKeys(USER.getUsername());

        return this;
    }

    public MainPage setPassword() {
        passwordField.clear();
        passwordField.sendKeys(USER.getPassword());

        return this;
    }

    public void clickLogInConfirmBtn() {
        new Actions(driver).pause(WAIT)
                .click(logInConfirmBtn).build().perform();
    }

    public String getWelcomeLine() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(welcomeLine));

        return welcomeLine.getText();
    }

    public CartPage clickCartPageBtn() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(cartPageBtn));
        cartPageBtn.click();

        return new CartPage(driver);
    }

    public HomePage clickHomePage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(homePageBtn));
        homePageBtn.click();

        return new HomePage(driver);
    }

    public void clickLogOutBtn() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(logOutBtn));
        logOutBtn.click();
    }

    public boolean isWelcomeLineDisplayed() {
        return welcomeLine.isDisplayed();
    }

}
