package Pages;

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

public class CoursesPage extends BaseTest {
    private WebDriver driver;
    private final Logger log = LogManager.getLogger(PracticePage.class);

    public CoursesPage(WebDriver driver) {
        this.driver = driver;
        var ajax = new AjaxElementLocatorFactory(driver, getWAIT_TIME());
        PageFactory.initElements(ajax, this);
        log.info("CoursesPage is loaded");
    }

    @FindBy(name = "categories")
    private WebElement categories;
    @FindBy(xpath = "//h4[contains(text(),'Selenium WebDriver With Java')]")
    private WebElement seleniumJava;
    @FindBy(xpath = "//h4[contains(text(),'Complete Test Automation Bundle')]")
    private WebElement automationBundle;
    @FindBy(tagName = "h4")
    private List<WebElement> h4s;
    @FindBy(css = "[value='1604']")
    private WebElement SWTestingOption;

    public CoursesPage selectSWTesting() {
        selectByValue(categories, "1604");
        log.info("Select \"Selenium WebDriver With Java\"}");
        return this;
    }

    public CoursesPage isSWTestingSelected() {
        assertTrue(SWTestingOption.isSelected());
        log.info("\"SWTestingOption\" selected");
        return this;
    }

    public CoursesPage waitInvisibleOfAutomationBundle() {
        invisibleWait(automationBundle);
        log.info("Waiting for automationBundle invisible");
        return this;
    }

    public CoursesPage isSWTestingFiltered() {
        assertEquals(1, h4s.size());
        assertTrue(seleniumJava.isDisplayed());
        log.info("Page display only \"Selenium WebDriver With Java\"");
        return this;
    }

    public PracticePage switchToPracticePage() {
        driver.close();
        driver.switchTo().window(PracticePage.getCurrentWindow());
        return new PracticePage(driver);
    }


}
