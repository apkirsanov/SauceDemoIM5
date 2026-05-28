package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class ProductsPageTest extends BaseTest {

    @Test(description = "Проверка открытия главной страницы",
    testName = "Проверка открытия главной страницы")
    @Description("Проверка открытия главной страницы")
    @Epic("E2E")
    @Feature("Open products page")
    @Story("Products page")
    @TmsLink("PFLB-26")
    @Issue("PFLB-26")
    @Owner("Anton Kirsanov")
    public void testProductPageOpen() {
        loginInSauceDemo();
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(description = "Проверка количества отображаемых товаров на главной странице",
            testName = "Проверка количества отображаемых товаров на главной странице")
    @Description("Проверка количества отображаемых товаров на главной странице")
    @Epic("E2E")
    @Feature("View list of products")
    @Story("Products page")
    @TmsLink("PFLB-26")
    @Issue("PFLB-26")
    @Owner("Anton Kirsanov")
    public void testItemsList() {
        loginInSauceDemo();
        Assert.assertEquals(productsPage.getNumberOfItems(), 6);
    }

    @Test(description = "Проверка логики работы кнопки Add to cart в карточке товара",
            testName = "Проверка логики работы кнопки Add to cart в карточке товара")
    @Description("Проверка логики работы кнопки Add to cart в карточке товара")
    @Epic("E2E")
    @Feature("Add item to cart")
    @Story("Products page")
    @TmsLink("PFLB-26")
    @Issue("PFLB-26")
    @Owner("Anton Kirsanov")
    public void testAddToCartBtn() {
        loginInSauceDemo();
        productsPage.addToCart(0);
        Assert.assertTrue(productsPage.isRemoveButtonDisplayed(),
                "Товар добавился в корзину");
    }

    @Test(description = "Проверка логики работы кнопки Remove item в карточке товара",
            testName = "Проверка логики работы кнопки Remove item в карточке товара")
    @Description("Проверка логики работы кнопки Remove item в карточке товара")
    @Epic("E2E")
    @Feature("Remove item from cart")
    @Story("Products page")
    @TmsLink("PFLB-26")
    @Issue("PFLB-26")
    @Owner("Anton Kirsanov")
    public void testRemoveItemBtn() {
        loginInSauceDemo();
        productsPage.addToCart(0);
        softAssert.assertTrue(productsPage.isRemoveButtonDisplayed(),
                "Товар добавился в корзину");
        productsPage.removeItem(0);
        softAssert.assertTrue(productsPage.isAddToCartButtonDisplayed(), "Товар удалился из корзины, кнопка" +
                " Add to cart снова видна");
    }

    @Test(description = "Проверка перехода в корзину по кнопке в верхнем правом углу",
            testName = "Проверка перехода в корзину по кнопке в верхнем правом углу")
    @Description("Проверка перехода в корзину по кнопке в верхнем правом углу")
    @Epic("E2E")
    @Feature("Go to cart")
    @Story("Products page")
    @TmsLink("PFLB-26")
    @Issue("PFLB-26")
    @Owner("Anton Kirsanov")
    public void testGoToCart() {
        loginInSauceDemo();
        productsPage.cartBtn();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
    }

    @Test(description = "Проверка счетчика товаров на иконке корзины",
            testName = "Проверка счетчика товаров на иконке корзины")
    @Description("Проверка счетчика товаров на иконке корзины")
    @Epic("E2E")
    @Feature("Cart bage")
    @Story("Products page")
    @TmsLink("PFLB-26")
    @Issue("PFLB-26")
    @Owner("Anton Kirsanov")
    public void testCartBadge() {
        loginInSauceDemo();
        productsPage.addToCart(0);
        Assert.assertEquals(productsPage.getCartBadgeValue(), "1");
    }
}
