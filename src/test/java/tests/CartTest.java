package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

public class CartTest extends BaseTest {

    @Test(description = "Проверка логики работы кнопки Continue shopping в корзине")
    @Owner("Anton Kirsanov")
    public void testContinueShoppingBtn() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .cartBtn()
                .isPageOpened()
                .verifyTitle("Your Cart")
                .continueShoppingBtn()
                .isPageOpened()
                .verifyTitle("Products");
    }

    @Test(description = "Проверка логики работы кнопки Checkout в корзине")
    @Owner("Anton Kirsanov")
    public void testCheckoutBtn() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .cartBtn()
                .isPageOpened()
                .checkoutBtn()
                .isPageOpened()
                .verifyTitle("Checkout: Your Information");
    }

    @Test(description = "Проверка корректного отображения добавленного товара в корзине по названию")
    @Owner("Anton Kirsanov")
    public void testItemInCart() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addToCart(0)
                .cartBtn()
                .isPageOpened()
                .verifyItemName("Sauce Labs Backpack");
    }

    @Test(description = "Проверка корректного отображения цены у товара в корзине")
    @Owner("Anton Kirsanov")
    public void testItemPriceInCart() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addToCart(0)
                .cartBtn()
                .isPageOpened()
                .verifyItemPrice("$29.99");
    }
}