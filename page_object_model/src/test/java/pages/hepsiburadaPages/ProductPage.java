package pages.hepsiburadaPages;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductPage extends BaseTest {

    private WebDriver driver;
    private final Logger log = LogManager.getLogger(ProductPage.class);

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        var ajax = new AjaxElementLocatorFactory(driver, getWAIT_TIME());
        PageFactory.initElements(ajax, this);
        log.info("ProductPage is loaded");
    }

    @FindBy(id = "addToCart")
    private WebElement addToCart;
    @FindBy(css = "div[data-test-class='modal_overflow']")
    private WebElement modalOverflow;
    @FindBy(xpath = "//button[text()='Sepete git']")
    private WebElement goToCartBtn;
    @FindBy(xpath = "//button[text()='Alışverişe devam et']")
    private WebElement returnShopping;
    @FindBy(css = ".slick-slide")
    private List<WebElement> slickSlide;

    public ProductPage clickAddToCard() {
        clickWithJS(addToCart);
        log.info("Clicked on addToCart");
        return this;
    }

    public ProductPage waitInvisibleOfModal() {
        visibleWait(goToCartBtn);
        log.info("modalOverflow display");
        return this;
    }

    public ProductPage assertGotToCardBtn() {
        assertTrue(goToCartBtn.isDisplayed());
        log.info("goToCartBtn display");
        return this;
    }

    public ProductPage assertReturnShopping() {
        assertTrue(returnShopping.isDisplayed());
        log.info("returnShopping display");
        return this;
    }

    public ProductPage assertSlickSlideIsPresent() {
        assertTrue(slickSlide.size() > 0);
        log.info("slickSlide is present");
        return this;
    }

    public SearchResultPage clickReturnShopping() {
        clickElement(returnShopping);
        log.info("Clicked on returnShopping");
        invisibleWait(goToCartBtn);
        return new SearchResultPage(driver);
    }
}
