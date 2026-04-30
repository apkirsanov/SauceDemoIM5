package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutPageTest extends BaseTest {

    @Test
    public void testPositiveCheckout() {
        loginInSauceDemo();
        productsPage.cartBtn();
        cartPage.checkoutBtn();
        checkoutPage.continueCheckout("Anton", "Kirsanov", "123");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
    }

    @Test
    public void testCheckoutWithEmptyFirstname() {
        loginInSauceDemo();
        productsPage.cartBtn();
        cartPage.checkoutBtn();
        checkoutPage.continueCheckout("", "Kirsanov", "123");
        Assert.assertEquals(checkoutPage.getErrorMessageCheckout(), "Error: First Name is required");
    }

    @Test
    public void testCheckoutWithEmptyLastname() {
        loginInSauceDemo();
        productsPage.cartBtn();
        cartPage.checkoutBtn();
        checkoutPage.continueCheckout("Anton", "", "123");
        Assert.assertEquals(checkoutPage.getErrorMessageCheckout(), "Error: Last Name is required");
    }

    @Test
    public void testCheckoutWithEmptyZippostalcode() {
        loginInSauceDemo();
        productsPage.cartBtn();
        cartPage.checkoutBtn();
        checkoutPage.continueCheckout("Anton", "Kirsanov", "");
        Assert.assertEquals(checkoutPage.getErrorMessageCheckout(), "Error: Postal Code is required");
    }

    @Test
    public void testCheckoutWithEmptyFields() {
        loginInSauceDemo();
        productsPage.cartBtn();
        cartPage.checkoutBtn();
        checkoutPage.continueCheckout("", "", "");
        Assert.assertEquals(checkoutPage.getErrorMessageCheckout(), "Error: First Name is required");
    }

    @Test
    public void testCancelCheckout() {
        loginInSauceDemo();
        productsPage.cartBtn();
        cartPage.checkoutBtn();
        checkoutPage.cancelCheckoutBtn();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
    }
}
