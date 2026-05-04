package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsPageTest extends BaseTest {

    @Test
    public void testProductPageOpen() {
        loginInSauceDemo();
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }

    @Test
    public void testItemsList() {
        loginInSauceDemo();
        Assert.assertEquals(productsPage.getNumberOfItems(), 6);
    }

    @Test
    public void testAddToCartBtn() {
        loginInSauceDemo();
        productsPage.addToCart(0);
        Assert.assertTrue(productsPage.isRemoveButtonDisplayed(),
                "Товар добавился в корзину");
    }

    @Test
    public void testRemoveItemBtn() {
        loginInSauceDemo();
        productsPage.addToCart(0);
        softAssert.assertTrue(productsPage.isRemoveButtonDisplayed(),
                "Товар добавился в корзину");
        productsPage.removeItem(0);
        softAssert.assertTrue(productsPage.isAddToCartButtonDisplayed(), "Товар удалился из корзины, кнопка" +
                " Add to cart снова видна");
    }

    @Test
    public void testGoToCart() {
        loginInSauceDemo();
        productsPage.cartBtn();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
    }

    @Test
    public void testCartBadge() {
        loginInSauceDemo();
        productsPage.addToCart(0);
        Assert.assertEquals(productsPage.getCartBadgeValue(), "1");
    }
}
