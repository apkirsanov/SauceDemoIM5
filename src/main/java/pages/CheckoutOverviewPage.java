package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CheckoutOverviewPage extends BasePage {

    private final By FINISH_BUTTON = By.id("finish");
    private final By CANCEL_BUTTON = By.id("cancel");
    private final By TOTAL_LABEL = By.cssSelector("[data-test=total-label]");
    private final By SUBTOTAL_LABEL = By.cssSelector("[data-test=subtotal-label]");
    private final By TAX_LABEL = By.cssSelector("[data-test=tax-label]");
    private final By CART_ITEM = By.cssSelector(".cart_item");
    private final By ITEM_NAME = By.cssSelector(".inventory_item_name");
    private final By ITEM_PRICE = By.cssSelector(".inventory_item_price");
    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By ITEM_QUANTITY = By.cssSelector(".cart_quantity");
    private final By PAYMENT_INFO = By.cssSelector("[data-test=payment-info-value]");
    private final By SHIPPING_INFO = By.cssSelector("[data-test=shipping-info-value]");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы Overview чекаута")
    public CheckoutOverviewPage open() {
        log.info("Открытие страницы Overview чекаута");
        driver.get(BASE_URL + "/checkout-step-two.html");
        return this;
    }

    @Step("Проверка загрузки страницы Overview")
    public CheckoutOverviewPage isPageOpened() {
        try {
            log.info("Страница Overview чекаута открыта");
            wait.until(ExpectedConditions.visibilityOfElementLocated(FINISH_BUTTON));
        } catch (TimeoutException e) {
            log.warn("Страница не загрузилась", e.getMessage());
        }
        return this;
    }

    @Step("Получение заголовка страницы Overview")
    public String getTitle() {
        log.info("Получение заголовка страницы Overview");
        return driver.findElement(TITLE).getText();
    }

    @Step("Нажатие кнопки Finish")
    public CheckoutCompletePage finishCheckout() {
        log.info("Завершение оформления заказа");
        driver.findElement(FINISH_BUTTON).click();
        return new CheckoutCompletePage(driver);
    }

    @Step("Нажатие кнопки Cancel в Overview")
    public CartPage cancelCheckout() {
        log.info("Отмена оформления заказа в Overview");
        driver.findElement(CANCEL_BUTTON).click();
        return new CartPage(driver);
    }

    @Step("Получение общей стоимости заказа")
    public String getTotalAmount() {
        log.info("Получение общей стоимости заказа");
        return driver.findElement(TOTAL_LABEL).getText();
    }

    @Step("Получение суммы без налога")
    public String getSubtotalAmount() {
        log.info("Получение суммы без налога");
        return driver.findElement(SUBTOTAL_LABEL).getText();
    }

    @Step("Получение суммы налога")
    public String getTaxAmount() {
        log.info("Получение суммы налога");
        return driver.findElement(TAX_LABEL).getText();
    }

    @Step("Получение количества товаров в Overview")
    public int getNumberOfItems() {
        log.info("Получение количества товаров в Overview");
        return driver.findElements(CART_ITEM).size();
    }

    @Step("Получение названия товара в Overview")
    public String getItemName() {
        log.info("Получение названия товара в Overview");
        return driver.findElement(ITEM_NAME).getText();
    }

    @Step("Получение цены товара в Overview")
    public String getItemPrice() {
        log.info("Получение цены товара в Overview");
        return driver.findElement(ITEM_PRICE).getText();
    }

    @Step("Получение количества единиц товара")
    public String getItemQuantity() {
        log.info("Получение количества единиц товара");
        return driver.findElement(ITEM_QUANTITY).getText();
    }

    @Step("Получение информации о платеже")
    public String getPaymentInfo() {
        log.info("Получение информации о платеже");
        return driver.findElement(PAYMENT_INFO).getText();
    }

    @Step("Получение информации о доставке")
    public String getShippingInfo() {
        log.info("Получение информации о доставке");
        return driver.findElement(SHIPPING_INFO).getText();
    }

    @Step("Проверка заголовка страницы Overview")
    public CheckoutOverviewPage verifyTitle(String expectedTitle) {
        log.info("Проверка заголовка страницы Overview");
        String actualTitle = getTitle();
        assert actualTitle.equals(expectedTitle) :
                String.format("Заголовок не соответствует. Ожидалось: '%s', Получено: '%s'", expectedTitle, actualTitle);
        return this;
    }

    @Step("Проверка количества товаров в Overview")
    public CheckoutOverviewPage verifyItemsCount(int expectedCount) {
        log.info("Проверка количества товаров в Overview");
        int actualCount = getNumberOfItems();
        assert actualCount == expectedCount :
                String.format("Количество товаров не соответствует. Ожидалось: %d, Получено: %d", expectedCount, actualCount);
        return this;
    }

    @Step("Проверка названия товара в Overview")
    public CheckoutOverviewPage verifyItemName(String expectedName) {
        log.info("Проверка названия товара в Overview");
        String actualName = getItemName();
        assert actualName.equals(expectedName) :
                String.format("Название товара не соответствует. Ожидалось: '%s', Получено: '%s'", expectedName, actualName);
        return this;
    }

    @Step("Проверка цены товара в Overview")
    public CheckoutOverviewPage verifyItemPrice(String expectedPrice) {
        log.info("Проверка цены товара в Overview");
        String actualPrice = getItemPrice();
        assert actualPrice.equals(expectedPrice) :
                String.format("Цена товара не соответствует. Ожидалось: '%s', Получено: '%s'", expectedPrice, actualPrice);
        return this;
    }

    @Step("Проверка общей стоимости")
    public CheckoutOverviewPage verifyTotalAmount(String expectedTotal) {
        log.info("Проверка общей стоимости");
        String actualTotal = getTotalAmount();
        assert actualTotal.equals(expectedTotal) :
                String.format("Общая стоимость не соответствует. Ожидалось: '%s', Получено: '%s'", expectedTotal, actualTotal);
        return this;
    }
}