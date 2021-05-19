package tests;

import business_object.User;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ui.page_object.CartPage;
import ui.page_object.HomePage;
import ui.page_object.ProductPage;
import utils.AssertionManager;

public class MainTest extends BaseTest {

    private static final User USER = User.getUser();

    private HomePage homePage;
    private CartPage cartPage;
    private ProductPage productPage;

    private final String orderName;
    private final String orderCard;

    @Factory(dataProvider = "orderDataProvider")
    public MainTest(String orderName, String orderCard) {
        this.orderName = orderName;
        this.orderCard = orderCard;
    }

    /**
     * This test tries to log in to the demoblaze.com
     * The test will pass if Username will be presented in the
     * welcome line
     */
    @Test(description = "Log In to the demoblaze.com")
    public void logIn() {
        LOGGER.debug("Test 1: Log In to the demoblaze.com");
        homePage = HomePage.Login(driver);
        String expectedString = "Welcome " + USER.getUsername();
        AssertionManager.assertEqualsAndLog(homePage.getWelcomeLine(), expectedString);
    }

    /**
     * This test tries to add the product to the cart
     * The test will pass if number of products in cart after adding
     * will be greater then number of products before adding by 1
     * @param product product to add
     */
    @Parameters(value = "phone")
    @Test(description = "Add product to the cart", dependsOnMethods = "logIn")
    public void addProductToCart(String product) {
        LOGGER.debug("Test 2: Add product to the cart");
        cartPage = homePage.clickCartPageBtn();
        int numberOfElementBeforeAdd = cartPage.getNumberOfElementInCartByName(product);
        homePage = cartPage.clickHomePage();
        productPage = homePage.clickProductByName(product)
                .clickAddToCartBtn()
                .clickOkAlert();
        cartPage = productPage.clickCartPageBtn();
        int numberOfElementAfterAdd = cartPage.getNumberOfElementInCartByName(product);
        AssertionManager.assertEqualsAndLog(numberOfElementAfterAdd - numberOfElementBeforeAdd, 1);
    }

    /**
     * This test tries to delete the product from the cart
     * The test will pass if number of products in cart after deleting
     * will be less then number of products before deleting by 1
     * @param product product to delete
     */
    @Parameters(value = "phone")
    @Test(description = "Delete product from the cart", dependsOnMethods = "addProductToCart")
    public void deleteProductFromCart(String product) {
        LOGGER.debug("Test 3: Delete product from the cart");
        int numberOfElementsBeforeDelete = cartPage.getNumberOfElementsInCart();
        cartPage.deleteProductByName(product);
        int numberOfElementsAfterDelete = cartPage.getNumberOfElementsInCart();
        homePage = cartPage.clickHomePage();
        AssertionManager.assertEqualsAndLog(numberOfElementsBeforeDelete - numberOfElementsAfterDelete, 1);
    }

    /**
     * This test tries to place order and purchase it
     * The test will pass if purchase confirm title will be equal to
     * "Thank you for your purchase!"
     * @param product product to purchase
     */
    @Parameters(value = "phone")
    @Test(description = "Place order and purchase", dependsOnMethods = "deleteProductFromCart")
    public void placeOrder(String product) {
        LOGGER.debug("Test 4: Delete product from the cart");
        productPage = homePage.clickProductByName(product)
                .clickAddToCartBtn()
                .clickOkAlert();
        cartPage = homePage.clickCartPageBtn()
                .clickPlaceOrderBtn()
                .setOrderNameField(orderName)
                .setOrderCardField(orderCard)
                .clickPurchaseBtn();
        AssertionManager.assertEqualsAndLog(cartPage.getPurchaseConfirmText(), "Thank you for your purchase!");
        homePage = cartPage.clickConfirmPurchaseBtn();
    }

    /**
     * This test tries to log out from the demoblaze.com
     * The test will pass if welcome line will not be presented
     * on the page
     */
    @Test(description = "Log Out from the demoblaze.com", dependsOnMethods = "placeOrder")
    public void logOut() {
        LOGGER.debug("Test 5: Delete product from the cart");
        homePage.clickLogOutBtn();
        AssertionManager.assertFalseAndLog(homePage.isWelcomeLineDisplayed());
    }
}
