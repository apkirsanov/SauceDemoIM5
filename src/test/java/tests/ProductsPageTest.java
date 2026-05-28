package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class ProductsPageTest extends BaseTest {

    @Test(description = "Проверка открытия главной страницы")
    @Description("Проверка открытия главной страницы")
    @Owner("Anton Kirsanov")
    public void testProductPageOpen() {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .verifyTitle("Products");
    }

    @Test(description = "Проверка количества отображаемых товаров на главной странице")
    @Owner("Anton Kirsanov")
    public void testItemsList() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .verifyItemsCount(6);
    }

    @Test(description = "Проверка логики работы кнопки Add to cart в карточке товара")
    @Owner("Anton Kirsanov")
    public void testAddToCartBtn() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .addToCart(0);
        softAssert.assertTrue(productsPage.isRemoveButtonDisplayed(),
                "Товар добавился в корзину");
        softAssert.assertAll();
    }

    @Test(description = "Проверка логики работы кнопки Remove item в карточке товара")
    @Owner("Anton Kirsanov")
    public void testRemoveItemBtn() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .addToCart(0);
        softAssert.assertTrue(productsPage.isRemoveButtonDisplayed(),
                "Товар добавился в корзину");
        productsPage.removeItem(0);
        softAssert.assertTrue(productsPage.isAddToCartButtonDisplayed(),
                "Товар удалился из корзины, кнопка Add to cart снова видна");
        softAssert.assertAll();
    }

    @Test(description = "Проверка перехода в корзину по кнопке в верхнем правом углу")
    @Owner("Anton Kirsanov")
    public void testGoToCart() {
        String expectedUrl = "https://www.saucedemo.com/cart.html";
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .cartBtn()
                .isPageOpened();
        softAssert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        softAssert.assertAll();
    }

    @Test(description = "Проверка счетчика товаров на иконке корзины")
    @Owner("Anton Kirsanov")
    public void testCartBadge() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addToCart(0);
        softAssert.assertEquals(productsPage.getCartBadgeValue(), "1");
        softAssert.assertAll();
    }
}