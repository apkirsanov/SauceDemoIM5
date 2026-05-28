package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CheckoutCompletePage extends BasePage {

    private final By COMPLETE_HEADER = By.cssSelector("[data-test=complete-header]");
    private final By COMPLETE_TEXT = By.cssSelector("[data-test=complete-text]");
    private final By BACK_HOME_BUTTON = By.id("back-to-products");
    private final By PONY_EXPRESS_IMAGE = By.cssSelector(".pony_express");
    private final By TITLE = By.cssSelector("[data-test=title]");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы завершения заказа")
    public CheckoutCompletePage open() {
        log.info("Открытие страницы завершения заказа");
        driver.get(BASE_URL + "/checkout-complete.html");
        return this;
    }

    @Step("Проверка загрузки страницы завершения заказа")
    public CheckoutCompletePage isPageOpened() {
        try {
            log.info("Страница завершения заказа открыта");
            wait.until(ExpectedConditions.visibilityOfElementLocated(COMPLETE_HEADER));
        } catch (TimeoutException e) {
            log.warn("Страница не загрузилась", e.getMessage());
        }
        return this;
    }

    @Step("Получение заголовка страницы завершения")
    public String getTitle() {
        log.info("Получение заголовка страницы завершения");
        return driver.findElement(TITLE).getText();
    }

    @Step("Получение основного сообщения об успешном заказе")
    public String getCompleteHeader() {
        log.info("Получение основного сообщения об успешном заказе");
        return driver.findElement(COMPLETE_HEADER).getText();
    }

    @Step("Получение дополнительного текста об успешном заказе")
    public String getCompleteText() {
        log.info("Получение дополнительного текста об успешном заказе");
        return driver.findElement(COMPLETE_TEXT).getText();
    }

    @Step("Нажатие кнопки Back Home")
    public ProductsPage backHome() {
        log.info("Возврат на главную страницу с товарами");
        driver.findElement(BACK_HOME_BUTTON).click();
        return new ProductsPage(driver);
    }

    @Step("Проверка отображения изображения Pony Express")
    public boolean isPonyExpressImageDisplayed() {
        log.info("Проверка отображения изображения Pony Express");
        try {
            return driver.findElement(PONY_EXPRESS_IMAGE).isDisplayed();
        } catch (Exception e) {
            log.warn("Изображение Pony Express не отображается");
            return false;
        }
    }

    @Step("Проверка заголовка страницы завершения")
    public CheckoutCompletePage verifyTitle(String expectedTitle) {
        log.info("Проверка заголовка страницы завершения");
        String actualTitle = getTitle();
        assert actualTitle.equals(expectedTitle) :
                String.format("Заголовок не соответствует. Ожидалось: '%s', Получено: '%s'", expectedTitle, actualTitle);
        return this;
    }

    @Step("Проверка основного сообщения об успешном заказе")
    public CheckoutCompletePage verifyCompleteHeader(String expectedHeader) {
        log.info("Проверка основного сообщения об успешном заказе");
        String actualHeader = getCompleteHeader();
        assert actualHeader.equals(expectedHeader) :
                String.format("Сообщение не соответствует. Ожидалось: '%s', Получено: '%s'", expectedHeader, actualHeader);
        return this;
    }

    @Step("Проверка дополнительного текста об успешном заказе")
    public CheckoutCompletePage verifyCompleteText(String expectedText) {
        log.info("Проверка дополнительного текста об успешном заказе");
        String actualText = getCompleteText();
        assert actualText.equals(expectedText) :
                String.format("Текст не соответствует. Ожидалось: '%s', Получено: '%s'", expectedText, actualText);
        return this;
    }

    @Step("Проверка отображения изображения Pony Express")
    public CheckoutCompletePage verifyPonyExpressImageDisplayed() {
        log.info("Проверка отображения изображения Pony Express");
        assert isPonyExpressImageDisplayed() : "Изображение Pony Express не отображается на странице";
        return this;
    }

    @Step("Получение URL текущей страницы")
    public String getCurrentUrl() {
        log.info("Получение URL текущей страницы");
        return driver.getCurrentUrl();
    }

    @Step("Проверка URL страницы завершения")
    public CheckoutCompletePage verifyUrl(String expectedUrl) {
        log.info("Проверка URL страницы завершения");
        String actualUrl = getCurrentUrl();
        assert actualUrl.equals(expectedUrl) :
                String.format("URL не соответствует. Ожидалось: '%s', Получено: '%s'", expectedUrl, actualUrl);
        return this;
    }
}