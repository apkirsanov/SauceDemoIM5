package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CheckoutPage extends BasePage {

    private final By CONTINUE_BUTTON = By.id("continue");
    private final By CANCEL_CHECKOUT_BUTTON = By.id("cancel");
    private final By TITLE_OF_CHECKOUT = By.cssSelector("[data-test=title]");
    private final By FIRSTNAME_FIELD = By.cssSelector("[data-test=firstName]");
    private final By LASTNAME_FIELD = By.cssSelector("[data-test=lastName]");
    private final By ZIPPOSTALCODE_FIELD = By.cssSelector("[data-test=postalCode]");
    private final By ERROR_MESSAGE_IN_CHECKOUT = By.xpath("//*[@data-test='error']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage open() {
        log.info("Открытие страницы чекаута");
        driver.get(BASE_URL + "/checkout-step-one.html");
        return this;
    }

    public CheckoutPage isPageOpened() {
        try {
            log.info("Страница чекаута открыта");
            wait.until(ExpectedConditions.visibilityOfElementLocated(CONTINUE_BUTTON));
        } catch (TimeoutException e) {
            log.warn("Страница не загрузилась", e.getMessage());
        }
        return this;
    }

    @Step("Получить заголовок страницы")
    public String getTitleOfCheckout() {
        log.info("Получение заголовка страницы");
        return driver.findElement(TITLE_OF_CHECKOUT).getText();
    }

    @Step("Заполнение формы чекаута и переход в Overview")
    public CheckoutOverviewPage continueCheckout(String firstname, String lastname, String zippostalcode) {
        log.info("Переход в овервью с именем - '{}', фамилией - '{}', почтовым индексом - '{}'",
                firstname, lastname, zippostalcode);
        driver.findElement(FIRSTNAME_FIELD).sendKeys(firstname);
        driver.findElement(LASTNAME_FIELD).sendKeys(lastname);
        driver.findElement(ZIPPOSTALCODE_FIELD).sendKeys(zippostalcode);
        driver.findElement(CONTINUE_BUTTON).click();
        return new CheckoutOverviewPage(driver);
    }

    @Step("Заполнение формы чекаута с ожиданием ошибки")
    public CheckoutPage continueCheckoutExpectingError(String firstname, String lastname, String zippostalcode) {
        log.info("Заполнение формы чекаута с ожиданием ошибки");
        driver.findElement(FIRSTNAME_FIELD).sendKeys(firstname);
        driver.findElement(LASTNAME_FIELD).sendKeys(lastname);
        driver.findElement(ZIPPOSTALCODE_FIELD).sendKeys(zippostalcode);
        driver.findElement(CONTINUE_BUTTON).click();
        return this;
    }

    @Step("Получение ошибки на странице Checkout")
    public String getErrorMessageCheckout() {
        log.info("Сообщение об ошибке");
        return driver.findElement(ERROR_MESSAGE_IN_CHECKOUT).getText();
    }

    @Step("Отмена чекаута")
    public CartPage cancelCheckoutBtn() {
        log.info("Отмена чекаута");
        driver.findElement(CANCEL_CHECKOUT_BUTTON).click();
        return new CartPage(driver);
    }

    public CheckoutPage verifyTitle(String expectedTitle) {
        log.info("Проверка заголовка страницы чекаута");
        String actualTitle = getTitleOfCheckout();
        assert actualTitle.equals(expectedTitle) :
                String.format("Заголовок не соответствует. Ожидалось: '%s', Получено: '%s'", expectedTitle, actualTitle);
        return this;
    }

    public CheckoutPage verifyErrorMessage(String expectedError) {
        log.info("Проверка сообщения об ошибке");
        String actualError = getErrorMessageCheckout();
        assert actualError.equals(expectedError) :
                String.format("Сообщение об ошибке не соответствует. Ожидалось: '%s', Получено: '%s'", expectedError, actualError);
        return this;
    }
}