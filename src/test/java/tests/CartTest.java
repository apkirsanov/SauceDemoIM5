package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CartTest extends BaseTest {

    @Test(description = "Проверка логики работы кнопки Continue shopping в корзине",
    testName = "Проверка логики работы кнопки Continue shopping в корзине")
    @Description("Проверка логики работы кнопки Continue shopping в корзине")
    @Epic("E2E")
    @Feature("Cart check")
    @Story("Cart")
    @TmsLink("PFLB-26")
    @Issue("PFLB-26")
    @Owner("Anton Kirsanov")
    public void testContinueShoppingBtn() {
        loginInSauceDemo();
        productsPage.cartBtn();
        softAssert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        cartPage.continueShoppingBtn();
        softAssert.assertEquals(productsPage.getTitle(), "Products");
        softAssert.assertAll();
    }

    @Test(description = "Проверка логики работы кнопки Checkout в корзине",
    testName = "Проверка логики работы кнопки Checkout в корзине")
    @Description("Проверка логики работы кнопки Checkout в корзине")
    @Epic("E2E")
    @Feature("Cart check")
    @Story("Cart")
    @TmsLink("PFLB-26")
    @Issue("PFLB-26")
    @Owner("Anton Kirsanov")
    public void testCheckoutBtn() {
        loginInSauceDemo();
        productsPage.cartBtn();
        cartPage.checkoutBtn();
        Assert.assertEquals(checkoutPage.getTitleOfCheckout(), "Checkout: Your Information");
    }

    @Test(description = "Проверка корректного отображения добавленного товара в корзине по названию",
    testName = "Проверка корректного отображения добавленного товара в корзине по названию")
    @Description("Проверка корректного отображения добавленного товара в корзине по названию")
    @Epic("E2E")
    @Feature("Cart check")
    @Story("Cart")
    @TmsLink("PFLB-26")
    @Issue("PFLB-26")
    @Owner("Anton Kirsanov")
    public void testItemInCart() {
        loginInSauceDemo();
        productsPage.addToCart(0);
        productsPage.cartBtn();
        Assert.assertEquals(cartPage.getItemName(), "Sauce Labs Backpack");
    }

    @Test(description = "Проверка корректного отображения цены у товара в корзине",
    testName = "Проверка корректного отображения цены у товара в корзине")
    @Description("Проверка корректного отображения добавленного товара в корзине по названию")
    @Epic("E2E")
    @Feature("Cart check")
    @Story("Cart")
    @TmsLink("PFLB-26")
    @Issue("PFLB-26")
    @Owner("Anton Kirsanov")
    public void testItemPriceInCart() {
        loginInSauceDemo();
        productsPage.addToCart(0);
        productsPage.cartBtn();
        Assert.assertEquals(cartPage.getItemPrice(), "$29.99");
    }
}
