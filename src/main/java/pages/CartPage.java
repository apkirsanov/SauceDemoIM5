package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    private final By CHECKOUT_BUTTON = By.id("checkout");
    private final By CART_ITEM = By.cssSelector(".cart_item");
    private final By ITEM_NAME = By.cssSelector(".inventory_item_name");
    private final By ITEM_PRICE = By.cssSelector(".inventory_item_price");
    private final By REMOVE_BUTTON = By.cssSelector(".cart_button");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы корзины")
    public void open() {
        driver.get(BASE_URL + "/cart.html");
    }

    @Step("Нажатие кнопки Continue Shopping")
    public void continueShoppingBtn() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    @Step("Нажатие кнопки Checkout")
    public void checkoutBtn() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    @Step("Получить название товара в корзине")
    public String getItemName() {
        return driver.findElement(ITEM_NAME).getText();
    }

    @Step("Получить цену товара в корзине")
    public String getItemPrice() {
        return driver.findElement(ITEM_PRICE).getText();
    }

    public void removeItemFromCart() {
        driver.findElement(REMOVE_BUTTON).click();
    }
}
