package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void checkLoginWithPositiveCreds() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }

    @Test
    public void checkLoginWithEmptyUser() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test
    public void checkLoginWithNegativeCred() {
        loginPage.open();
        loginPage.login("test", "test");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user" +
                " in this service");
    }
}