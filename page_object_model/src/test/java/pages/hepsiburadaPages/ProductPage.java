package pages.hepsiburadaPages;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ProductPage extends BaseTest {

    private WebDriver driver;
    private final Logger log = LogManager.getLogger(ProductPage.class);

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        var ajax = new AjaxElementLocatorFactory(driver, getWAIT_TIME());
        PageFactory.initElements(ajax, this);
        log.info("HB home page is loaded");
    }

    @FindBy(id = "addToCart")
    private WebElement addToCart;

    public ProductPage clickAddToCard() {
        clickWithJS(addToCart);
        log.info("Clicked on addToCart");
        return this;
    }
}
