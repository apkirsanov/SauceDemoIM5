package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

public class CheckoutPageTest extends BaseTest {

    @Test(description = "Позитивная проверка чекаута",
    testName = "Позитивная проверка чекаута")
    @Description("Позитивная проверка чекаута")
    @Epic("E2E")
    @Feature("Positive Checkout")
    @Story("Checkout")
    @TmsLink("PFLB-26")
    @Issue("PFLB-26")
    @Owner("Anton Kirsanov")
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

    @Test(description = "Негативная проверка чекаута",
            testName = "Негативная проверка чекаута")
    @Description("Негативная проверка чекаута")
    @Epic("E2E")
    @Feature("Negative Checkout")
    @Story("Checkout")
    @TmsLink("PFLB-26")
    @Issue("PFLB-26")
    @Owner("Anton Kirsanov")
    public void testNegativeCheckout(String firstname, String lastname, String postal, String errorMessage) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.cartBtn();
        cartPage.checkoutBtn();
        softAssert.assertEquals(checkoutPage.getTitleOfCheckout(), "Checkout: Your Information");
        checkoutPage.continueCheckout(firstname, lastname, postal);
        softAssert.assertEquals(checkoutPage.getErrorMessageCheckout(), errorMessage);
        softAssert.assertAll();
    }

    @Test(description = "Проверка логики работы кнопки Cancel на странице чекаута",
            testName = "Проверка логики работы кнопки Cancel на странице чекаута")
    @Description("Проверка логики работы кнопки Cancel на странице чекаута")
    @Epic("E2E")
    @Feature("Cancel Checkout")
    @Story("Checkout")
    @TmsLink("PFLB-26")
    @Issue("PFLB-26")
    @Owner("Anton Kirsanov")
    public void testCancelCheckout() {
        loginInSauceDemo();
        productsPage.cartBtn();
        cartPage.checkoutBtn();
        checkoutPage.cancelCheckoutBtn();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
    }
}
