package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class LocatorTest extends BaseTest {

    @Test
    public void checkLocator() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.className("submit-button")).click();
        WebElement byTagName = driver.findElement(By.tagName("button"));
        WebElement linkText = driver.findElement(By.linkText("Sauce Labs Backpack"));
        WebElement partialLinkText = driver.findElement(By.partialLinkText("Backpack"));
        //*Xpath's
        WebElement attributeXpath = driver.findElement(By.xpath("//img[@alt='Sauce Labs Backpack']"));
        WebElement textXpath = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        WebElement containsXpath = driver.findElement(By.xpath("//button[contains(@data-test, 'add-to-" +
                "cart')]"));
        WebElement textContXpath = driver.findElement(By.xpath("//div[contains(text(), 'Bike Light')]"));
        WebElement ancestorXpath = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']" +
                "/ancestor::div[@class='inventory_item_description']"));
        WebElement descendantXpath = driver.findElement(By.xpath("//div[@class='inventory_item_" +
                "description']//descendant::button"));
        WebElement followingXpath = driver.findElement(By.xpath("//div[@data-test='inventory-item-name']" +
                "/following::div[@data-test='inventory-item-price']"));
        WebElement parentXpath = driver.findElement(By.xpath("//div[@data-test='inventory-item-name']" +
                "/parent::a"));
        WebElement precedingXpath = driver.findElement(By.xpath("//div[@data-test='inventory-item-price']" +
                "/preceding::div[@data-test='inventory-item-name']"));
        WebElement andXpath = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs" +
                "-bike-light' and text()='Add to cart']"));
        //*CSS
        WebElement cartButton = driver.findElement(By.cssSelector(".shopping_cart_container"));
        WebElement addToCart = driver.findElement(By.cssSelector(".btn.btn_primary"));
        WebElement itemName = driver.findElement(By.cssSelector(".inventory_item .inventory_item_name"));
        WebElement itemPic = driver.findElement(By.cssSelector("#item_0_img_link"));
        WebElement tagName = driver.findElement(By.cssSelector("button"));
        WebElement tagNameClass = driver.findElement(By.cssSelector("button.btn_inventory"));
        WebElement lightPic = driver.findElement(By.cssSelector("[data-test='inventory-item-sauce-labs-bike-light" +
                "-img']"));
        WebElement lightPic1 = driver.findElement(By.cssSelector("[alt~='Light']"));
        WebElement valueEqual = driver.findElement(By.cssSelector("div[data-test|='inventory']"));
        WebElement valueBegins = driver.findElement(By.cssSelector("img[data-test^='inventory-item']"));
        WebElement itemPrice = driver.findElement(By.cssSelector("div[data-test$='item-price']"));
        WebElement itemDescription = driver.findElement(By.cssSelector("div[data-test*='item-desc']"));
        driver.quit();

    }
}
