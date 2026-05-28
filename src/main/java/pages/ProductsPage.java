package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;

@Log4j2
public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By ITEM = By.cssSelector("[data-test='inventory-item']");
    private final By ADD_TO_CART = By.cssSelector("[data-test^='add-to-cart']");
    private final By REMOVE_ITEM = By.cssSelector("[data-test^='remove-sauce']");
    private final By CART_BUTTON = By.id("shopping_cart_container");
    private final By CART_BADGE = By.cssSelector("[data-test='shopping-cart-badge']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public ProductsPage open() {
        log.info("Открытие страницы c товарами");
        driver.get(BASE_URL + "/inventory.html");
        return this;
    }

    public ProductsPage isPageOpened() {
        try {
            log.info("Страница с товарами открыта");
            wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        } catch (TimeoutException e) {
            log.warn("Страница не загрузилась", e.getMessage());
        }
        return this;
    }

    @Step("Получить заголовок страницы")
    public String getTitle() {
        log.info("Получение заголовка страницы");
        return driver.findElement(TITLE).getText();
    }

    @Step("Получить количество товаров на странице")
    public int getNumberOfItems() {
        log.info("Получение количества товаров на странице");
        return driver.findElements(ITEM).size();
    }

    @Step("Добавить товар в корзину по индексу")
    public ProductsPage addToCart(int itemN) {
        log.info("Добавление товара в корзину");
        List<WebElement> items = driver.findElements(ITEM);
        items.get(itemN).findElement(ADD_TO_CART).click();
        return this;
    }

    @Step("Удалить товар из корзины по индексу")
    public ProductsPage removeItem(int itemN) {
        log.info("Удаление товара из корзины");
        List<WebElement> items = driver.findElements(ITEM);
        items.get(itemN).findElement(REMOVE_ITEM).click();
        return this;
    }

    @Step("Переход в корзину по иконке")
    public CartPage cartBtn() {
        log.info("Переход в корзину");
        driver.findElement(CART_BUTTON).click();
        return new CartPage(driver);
    }

    @Step("Получить значение бейджа корзины")
    public String getCartBadgeValue() {
        log.info("Получение значения бейджа корзины");
        return driver.findElement(CART_BADGE).getText();
    }

    @Step("Проверить отображение кнопки Remove")
    public boolean isRemoveButtonDisplayed() {
        log.info("Проверка отображения кнопки удаления товара");
        try {
            return driver.findElement(REMOVE_ITEM).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Проверить отображение кнопки Add to cart")
    public boolean isAddToCartButtonDisplayed() {
        log.info("Проверка отображения кнопки добавления товара");
        try {
            return driver.findElement(ADD_TO_CART).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public ProductsPage verifyTitle(String expectedTitle) {
        log.info("Проверка заголовка страницы");
        assert getTitle().equals(expectedTitle) : "Expected title: " + expectedTitle + ", but got: " + getTitle();
        return this;
    }

    public ProductsPage verifyItemsCount(int expectedCount) {
        log.info("Проверка количества товаров");
        assert getNumberOfItems() == expectedCount : "Expected count: " + expectedCount + ", but got: " + getNumberOfItems();
        return this;
    }
}
