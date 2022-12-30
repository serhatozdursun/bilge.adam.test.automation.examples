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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchResultPage extends BaseTest {
    private static String currentWindow = null;
    private WebDriver driver;
    private final Logger log = LogManager.getLogger(SearchResultPage.class);

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        var ajax = new AjaxElementLocatorFactory(driver, getWAIT_TIME());
        PageFactory.initElements(ajax, this);
        log.info("HB home page is loaded");
    }

    @FindBy(css = "div[class*=searchResultSummaryBar]")
    private WebElement searchResultSummaryBar;
    @FindBy(css = "[id*='SortingBox_']")
    private WebElement sortingBox;

    @FindBy(css = "[value='artanfiyat']")
    private WebElement sortToHigherPrice;

    @FindBy(css = "[data-test-id='product-info-wrapper']")
    private List<WebElement> productList;

    @FindBy(css = "button[data-test-id='product-info-button']")
    private WebElement addToCartBtn;

    public SearchResultPage assertResultBar(String expectedText) {
        var text = getElementText(searchResultSummaryBar);
        assertTrue(text.contains(expectedText));
        log.info("Title is true");
        return this;
    }

    public SearchResultPage clickSortingBox() {
        clickElement(sortingBox);
        log.info("Clicked on sorting box");
        return this;
    }

    public SearchResultPage clickSortToHigherPrice() throws InterruptedException {
        Thread.sleep(500);
        clickWithJS(sortToHigherPrice);
        log.info("Clicked on SortToHigherPrice");
        return this;
    }

    public SearchResultPage moveToProduct(int index) {
        mouseHover(productList.get(index));
        log.info("mouse move to {}. product", index);
        return this;
    }

    public ProductPage clickOnTheProduct(int index) {
        clickElement(productList.get(index));
        log.info("Clicked on addToCartBtn");
        currentWindow = driver.getWindowHandle();
        switchToNewTab();
        return new ProductPage(driver);
    }

    public static String getCurrentWindow() {
        return currentWindow;
    }

    public SearchResultPage assertURL(){
        var url = driver.getCurrentUrl();
        assertEquals(url,"https://www.hepsiburada.com/tablet-c-3008012");
        log.info("url is valid");
        return this;
    }
}
