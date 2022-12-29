package pages.hepsiburadaPages;


import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import pages.practicePages.PracticePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchResultPage extends BaseTest {

    private WebDriver driver;
    private final Logger log = LogManager.getLogger(PracticePage.class);

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        var ajax = new AjaxElementLocatorFactory(driver, getWAIT_TIME());
        PageFactory.initElements(ajax, this);
        log.info("HB home page is loaded");
    }
    @FindBy(css = "div[class*=searchResultSummaryBar]")
    private WebElement searchResultSummaryBar;

    public SearchResultPage assertResultBar(String expectetText) {
        var text = getElementText(searchResultSummaryBar);
        assertEquals(expectetText, text);
        return this;
    }
}
