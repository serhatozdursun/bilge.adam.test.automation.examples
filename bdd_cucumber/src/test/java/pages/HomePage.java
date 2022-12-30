package pages;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class HomePage extends BaseTest {

    private WebDriver driver;
    private final Logger log = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        this.driver = driver;
        var ajax = new AjaxElementLocatorFactory(driver, getWAIT_TIME());
        PageFactory.initElements(ajax, this);
        log.info("HB home page is loaded");
    }

    @FindBy(css = "[class*=searchBoxOld] input")
    private WebElement searchBox;
    @FindBy(xpath = "//div[text()='ARA']")
    private WebElement searchBtn;
    @FindBy(id = "myAccount")
    private WebElement myAccount;
    @FindBy(id = "login")
    private WebElement login;

    public HomePage typeSearchKeyword(String keyword) {
        sendKeysToWebElement(searchBox, keyword);
        log.info("\"{}\" was searched", keyword);
        return this;
    }

    public SearchResultPage clickSearch() {
        clickElement(searchBtn);
        log.info("Search clicked");
        return new SearchResultPage(driver);
    }

    public HomePage moveToAccount() {
        mouseHover(myAccount);
        return this;
    }

    public LoginPage clickLogin() {
        clickElement(login);
        return new LoginPage(driver);
    }

    public HomePage assertLoaded() {
        var title = driver.getTitle();
        var url = driver.getCurrentUrl();
        assertEquals("Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com", title);
        assertEquals("https://www.hepsiburada.com/", url);
        return this;
    }


}
