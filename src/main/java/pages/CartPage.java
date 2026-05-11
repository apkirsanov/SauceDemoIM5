package pages;

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

    public void open() {
        driver.get(BASE_URL + "/cart.html");
    }


    public void continueShoppingBtn() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    public void checkoutBtn() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public String getItemName() {
        return driver.findElement(ITEM_NAME).getText();
    }

    public String getItemPrice() {
        return driver.findElement(ITEM_PRICE).getText();
    }

    public void removeItemFromCart() {
        driver.findElement(REMOVE_BUTTON).click();
    }
}
