package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;

public class CartTest extends BaseTest {

    @Test
    public void testContinueShoppingBtn() {
        loginInSauceDemo();
        productsPage.cartBtn();
        softAssert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        cartPage.continueShoppingBtn();
        softAssert.assertEquals(productsPage.getTitle(), "Products");
    }

    @Test
    public void testCheckoutBtn() {
        loginInSauceDemo();
        productsPage.cartBtn();
        cartPage.checkoutBtn();
        Assert.assertEquals(checkoutPage.getTitleOfCheckout(), "Checkout: Your Information");
    }

    @Test
    public void testItemInCart() {
        loginInSauceDemo();
        productsPage.addToCart(0);
        productsPage.cartBtn();
        Assert.assertEquals(cartPage.getItemName(), "Sauce Labs Backpack");
    }

    @Test
    public void testItemPriceInCart() {
        loginInSauceDemo();
        productsPage.addToCart(0);
        productsPage.cartBtn();
        Assert.assertEquals(cartPage.getItemPrice(), "$29.99");
    }
}
