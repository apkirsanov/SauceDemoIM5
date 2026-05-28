package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(description = "Позитивная проверка авторизации с валидными кредами")
    @Description("Позитивная проверка авторизации с валидными кредами")
    @Epic("E2E")
    @Feature("Login in Sauce Demo")
    @Story("Login")
    @Owner("Anton Kirsanov")
    public void checkLoginWithPositiveCreds() {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .verifyTitle("Products");
    }

    @DataProvider(name = "Тестовые данные для негативного логина")
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(description = "Проверка входа в систему с невалидными кредами",
            dataProvider = "Тестовые данные для негативного логина")
    @Description("Проверка входа в систему с невалидными кредами")
    @Epic("E2E")
    @Feature("Negative login in Sauce Demo")
    @Story("Login")
    @Owner("Anton Kirsanov")
    public void negativeLogin(String user, String password, String errorMessage) {
        loginPage.open()
                .isPageOpened()
                .loginExpectingError(user, password);
        assertEquals(loginPage.getErrorMessage(), errorMessage);
    }
}