package pages;

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

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public int getNumberOfItems() {
        return driver.findElements(ITEM).size();
    }

    public void addToCart(int itemN) {
        List<WebElement> items = driver.findElements(ITEM);
        items.get(itemN).findElement(ADD_TO_CART).click();
    }

    public void removeItem(int itemN) {
        List<WebElement> items = driver.findElements(ITEM);
        items.get(itemN).findElement(REMOVE_ITEM).click();
    }

    public void cartBtn() {
        driver.findElement(CART_BUTTON).click();
    }

    public String getCartBadgeValue() {
        return driver.findElement(CART_BADGE).getText();
    }

    public boolean isRemoveButtonDisplayed() {
        try {
            return driver.findElement(REMOVE_ITEM).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAddToCartButtonDisplayed() {
        try {
            return driver.findElement(ADD_TO_CART).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
