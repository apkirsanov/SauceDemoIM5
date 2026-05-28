package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CartPage extends BasePage {

    private final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    private final By CHECKOUT_BUTTON = By.id("checkout");
    private final By CART_ITEM = By.cssSelector(".cart_item");
    private final By ITEM_NAME = By.cssSelector(".inventory_item_name");
    private final By ITEM_PRICE = By.cssSelector(".inventory_item_price");
    private final By REMOVE_BUTTON = By.cssSelector(".cart_button");
    private final By CART_PAGE_TITLE = By.cssSelector(".title");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы корзины")
    public CartPage open() {
        log.info("Открытие страницы корзины");
        driver.get(BASE_URL + "/cart.html");
        return this;
    }

    public CartPage isPageOpened() {
        try {
            log.info("Страница корзины открыта");
            wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_BUTTON));
        } catch (TimeoutException e) {
            log.warn("Страница не загрузилась", e.getMessage());
        }
        return this;
    }

    @Step("Нажатие кнопки Continue Shopping")
    public ProductsPage continueShoppingBtn() {
        log.info("Возврат на главную страницу с товарами");
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
        return new ProductsPage(driver);
    }

    @Step("Нажатие кнопки Checkout")
    public CheckoutPage checkoutBtn() {
        log.info("Переход на страницу чекаута");
        driver.findElement(CHECKOUT_BUTTON).click();
        return new CheckoutPage(driver);
    }

    @Step("Получить название товара в корзине")
    public String getItemName() {
        String name = driver.findElement(ITEM_NAME).getText();
        log.info("Название товара в корзине: {}", name);
        return name;
    }

    @Step("Получить цену товара в корзине")
    public String getItemPrice() {
        String price = driver.findElement(ITEM_PRICE).getText();
        log.info("Цена товара в корзине: {}", price);
        return price;
    }

    public String getTitleCartPage() {
        log.info("Заголовок страницы CartPage взят в обработку");
        return driver.findElement(CART_PAGE_TITLE).getText();
    }

    public CartPage removeItemFromCart() {
        log.info("Удаление товара из корзины");
        driver.findElement(REMOVE_BUTTON).click();
        return this;
    }

    public CartPage verifyItemName(String expectedName) {
        log.info("Проверка названия товара");
        assert getItemName().equals(expectedName) : "Expected: " + expectedName + ", but got: " + getItemName();
        return this;
    }

    public CartPage verifyItemPrice(String expectedPrice) {
        log.info("Проверка цены товара");
        assert getItemPrice().equals(expectedPrice) : "Expected: " + expectedPrice + ", but got: " + getItemPrice();
        return this;
    }

    public CartPage verifyTitle(String expectedTitle) {
        log.info("Проверка заголовка страницы корзины");
        assert getTitleCartPage().equals(expectedTitle) : "Expected: " + expectedTitle + ", but got: " + getTitleCartPage();
        return this;
    }
}