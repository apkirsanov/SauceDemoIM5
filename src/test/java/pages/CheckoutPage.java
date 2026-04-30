package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private final By CONTINUE_BUTTON = By.id("continue");
    private final By CANCEL_CHECKOUT_BUTTON = By.id("cancel");
    private final By TITLE_OF_CHECKOUT = By.cssSelector("[data-test=title]");
    private final By FIRSTNAME_FIELD = By.cssSelector("[data-test=firstName]");
    private final By LASTNAME_FIELD = By.cssSelector("[data-test=lastName]");
    private final By ZIPPOSTALCODE_FIELD = By.cssSelector("[data-test=postalCode]");
    private final By ERROR_MESSAGE_IN_CHEKOUT = By.xpath("//*[@data-test='error']");


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public String getTitleOfCheckout() {
        return driver.findElement(TITLE_OF_CHECKOUT).getText();
    }

    public void continueCheckout(String firstname, String lastname, String zippostalcode) {
        driver.findElement(FIRSTNAME_FIELD).sendKeys(firstname);
        driver.findElement(LASTNAME_FIELD).sendKeys(lastname);
        driver.findElement(ZIPPOSTALCODE_FIELD).sendKeys(zippostalcode);
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public String getErrorMessageCheckout() {
        return driver.findElement(ERROR_MESSAGE_IN_CHEKOUT).getText();
    }

    public void cancelCheckoutBtn() {
        driver.findElement(CANCEL_CHECKOUT_BUTTON).click();
    }
}
