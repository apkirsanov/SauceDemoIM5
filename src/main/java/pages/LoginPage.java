package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class LoginPage extends BasePage {

    private final By USERNAME_FIELD = By.id("user-name");
    private final By PASSWORD_FIELD = By.id("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR_MESSAGE = By.xpath("//*[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы Login")
    public LoginPage open() {
        log.info("Открытие страницы логина");
        driver.get(BASE_URL);
        return this;
    }

    public LoginPage isPageOpened() {
        try {
            log.info("Страница логина открыта");
            wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        } catch (TimeoutException e) {
            log.warn("Страница не загрузилась", e.getMessage());
        }
        return this;
    }

    @Step("Вход в магазин с именем пользователя {user} и паролем {password}")
    public ProductsPage login(String user, String password) {
        log.info("Вход в систему с логином - '{}', и паролем -'{}'", user, password);
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver);
    }

    @Step("Вход в магазин с возвратом этой же страницы (для негативных тестов)")
    public LoginPage loginExpectingError(String user, String password) {
        log.info("Попытка входа с логином - '{}', и паролем -'{}'", user, password);
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return this;
    }

    @Step("Получение ошибки")
    public String getErrorMessage() {
        log.info("Получение сообщения об ошибке");
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
