package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckoutPageTest extends BaseTest {

    @Test(description = "Позитивная проверка чекаута",
    testName = "Позитивная проверка чекаута")
    public void testPositiveCheckout() {
        loginInSauceDemo();
        productsPage.cartBtn();
        cartPage.checkoutBtn();
        checkoutPage.continueCheckout("Anton", "Kirsanov", "123");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
    }

    @DataProvider(name = "Тестовые данные для негативного чекаута")
    public Object[][] checkoutData() {
        return new Object[][] {
                {"", "Kirsanov", "123", "Error: First Name is required"},
                {"Anton", "", "123", "Error: Last Name is required"},
                {"Anton", "Kirsanov", "", "Error: Postal Code is required"},
                {"", "", "", "Error: First Name is required"}
        };
    }

    @Test(description = "Проверка логики работы кнопки Cancel на странице чекаута",
            testName = "Проверка логики работы кнопки Cancel на странице чекаута")
    public void testCancelCheckout() {
        loginInSauceDemo();
        productsPage.cartBtn();
        cartPage.checkoutBtn();
        checkoutPage.cancelCheckoutBtn();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
    }
}
