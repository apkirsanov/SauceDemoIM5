package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

public class CheckoutPageTest extends BaseTest {

    @Test(description = "Позитивная проверка полного чекаута")
    @Owner("Anton Kirsanov")
    public void testPositiveFullCheckout() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addToCart(0)
                .cartBtn()
                .isPageOpened()
                .checkoutBtn()
                .isPageOpened()
                .continueCheckout("Anton", "Kirsanov", "123")
                .isPageOpened()
                .verifyTitle("Checkout: Overview")
                .verifyItemsCount(1)
                .verifyItemName("Sauce Labs Backpack")
                .verifyItemPrice("$29.99")
                .finishCheckout()
                .isPageOpened()
                .verifyTitle("Checkout: Complete!")
                .verifyCompleteHeader("Thank you for your order!")
                .verifyCompleteText("Your order has been dispatched, and will arrive just as fast as the pony can get there!")
                .verifyPonyExpressImageDisplayed();
    }

    @Test(description = "Проверка чекаута с несколькими товарами")
    @Owner("Anton Kirsanov")
    public void testCheckoutWithMultipleItems() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addToCart(0)
                .addToCart(1)
                .cartBtn()
                .isPageOpened()
                .checkoutBtn()
                .isPageOpened()
                .continueCheckout("Anton", "Kirsanov", "123")
                .isPageOpened()
                .verifyItemsCount(2)
                .finishCheckout()
                .isPageOpened()
                .verifyCompleteHeader("Thank you for your order!");
    }

    @Test(description = "Проверка отмены чекаута на странице Overview")
    @Owner("Anton Kirsanov")
    public void testCancelCheckoutOnOverviewPage() {
        String expectedUrl = "https://www.saucedemo.com/cart.html";
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addToCart(0)
                .cartBtn()
                .isPageOpened()
                .checkoutBtn()
                .isPageOpened()
                .continueCheckout("Anton", "Kirsanov", "123")
                .isPageOpened()
                .cancelCheckout();
        softAssert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        softAssert.assertAll();
    }

    @DataProvider(name = "Тестовые данные для негативного чекаута")
    public Object[][] checkoutData() {
        return new Object[][]{
                {"", "Kirsanov", "123", "Error: First Name is required"},
                {"Anton", "", "123", "Error: Last Name is required"},
                {"Anton", "Kirsanov", "", "Error: Postal Code is required"},
                {"", "", "", "Error: First Name is required"}
        };
    }

    @Test(description = "Негативная проверка чекаута", dataProvider = "Тестовые данные для негативного чекаута")
    @Owner("Anton Kirsanov")
    public void testNegativeCheckout(String firstname, String lastname, String postal, String errorMessage) {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addToCart(0)
                .cartBtn()
                .isPageOpened()
                .checkoutBtn()
                .isPageOpened()
                .verifyTitle("Checkout: Your Information")
                .continueCheckoutExpectingError(firstname, lastname, postal)
                .verifyErrorMessage(errorMessage);
    }

    @Test(description = "Проверка логики работы кнопки Cancel на странице чекаута")
    @Owner("Anton Kirsanov")
    public void testCancelCheckout() {
        String expectedUrl = "https://www.saucedemo.com/cart.html";
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addToCart(0)
                .cartBtn()
                .isPageOpened()
                .checkoutBtn()
                .isPageOpened()
                .cancelCheckoutBtn()
                .isPageOpened();
        softAssert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        softAssert.assertAll();
    }

    @Test(description = "Проверка возврата на главную страницу после завершения заказа")
    @Owner("Anton Kirsanov")
    public void testBackHomeAfterComplete() {
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addToCart(0)
                .cartBtn()
                .isPageOpened()
                .checkoutBtn()
                .isPageOpened()
                .continueCheckout("Anton", "Kirsanov", "123")
                .isPageOpened()
                .finishCheckout()
                .isPageOpened()
                .backHome()
                .isPageOpened()
                .verifyTitle("Products");
    }
}