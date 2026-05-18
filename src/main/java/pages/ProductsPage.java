package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

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

    public void open() {
        driver.get(BASE_URL + "/inventory.html");
    }

    @Step("Получить заголовок страницы")
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Получить количество товаров на странице")
    public int getNumberOfItems() {
        return driver.findElements(ITEM).size();
    }

    @Step("Добавить товар в корзину по индексу")
    public void addToCart(int itemN) {
        List<WebElement> items = driver.findElements(ITEM);
        items.get(itemN).findElement(ADD_TO_CART).click();
    }

    @Step("Удалить товар из корзины по индексу")
    public void removeItem(int itemN) {
        List<WebElement> items = driver.findElements(ITEM);
        items.get(itemN).findElement(REMOVE_ITEM).click();
    }

    @Step("Переход в корзину по иконке")
    public void cartBtn() {
        driver.findElement(CART_BUTTON).click();
    }

    @Step("Получить значение бейджа корзины")
    public String getCartBadgeValue() {
        return driver.findElement(CART_BADGE).getText();
    }

    @Step("Проверить отображение кнопки Remove")
    public boolean isRemoveButtonDisplayed() {
        try {
            return driver.findElement(REMOVE_ITEM).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Проверить отображение кнопки Add to cart")
    public boolean isAddToCartButtonDisplayed() {
        try {
            return driver.findElement(ADD_TO_CART).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
