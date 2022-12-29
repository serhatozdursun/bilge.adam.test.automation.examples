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

import static org.junit.jupiter.api.Assertions.*;

public class PracticePage extends BaseTest {
    private WebDriver driver;

    private final Logger log = LogManager.getLogger(PracticePage.class);

    private static String currentWindow = null;

    public PracticePage(WebDriver driver) {
        this.driver = driver;
        var ajax = new AjaxElementLocatorFactory(driver, getWAIT_TIME());
        PageFactory.initElements(ajax, this);
        log.info("PracticePage is loaded");
    }

    @FindBy(css = "#radio-btn-example input")
    private List<WebElement> allRadioButtons;
    @FindBy(id = "bmwradio")
    private WebElement bmwRadioBtn;
    @FindBy(id = "benzradio")
    private WebElement benzRadioBtn;
    @FindBy(id = "hondaradio")
    private WebElement hondaRadioBtn;
    @FindBy(id = "carselect")
    private WebElement carSelect;
    @FindBy(css = "option[value='benz']")
    private WebElement benzOption;
    @FindBy(id = "multiple-select-example")
    private WebElement multipleSelect;
    @FindBy(css = "option[value='apple']")
    private WebElement appleOption;
    @FindBy(css = "option[value='peach']")
    private WebElement peachOption;
    @FindBy(css = "option[value='orange']")
    private WebElement orangeOption;
    @FindBy(css = "[href='/courses']")
    private WebElement courses;
    @FindBy(id = "opentab")
    private WebElement opentab;

    public PracticePage clickBmwRadio() {
        clickElement(bmwRadioBtn);
        log.info("Bmw radio button tiklandi");
        return this;
    }

    public PracticePage assertBmwButtonIsSelected() {
        assertTrue(bmwRadioBtn.isSelected());
        log.info("Bmw radio button secili");
        return this;
    }

    public PracticePage clickBenzRadio() {
        clickElement(benzRadioBtn);
        log.info("Benz radio button tiklandi");
        return this;
    }

    public PracticePage assertBenzButtonIsSelected() {
        assertTrue(benzRadioBtn.isSelected());
        log.info("Benz radio button secili");
        return this;
    }

    public PracticePage clickHondaRadio() {
        clickElement(hondaRadioBtn);
        log.info("Honda radio button tiklandi");
        return this;
    }

    public PracticePage assertHondaButtonIsSelected() {
        assertTrue(hondaRadioBtn.isSelected());
        log.info("Honda radio button secili");
        return this;
    }

    public PracticePage checkAllRadioButton() {
        var radioButtons = allRadioButtons;
        for (var radio : radioButtons) {
            clickAbleWait(radio).click();
            //radio button secildi mi kontrolu
            assertTrue(radio.isSelected(), "Radio buttonlar secilemedi");
            log.info("{} radio buton tiklanid", radio.getText());
        }
        return this;
    }

    public PracticePage selectBenz() {
        selectByValue(carSelect, "benz");
        log.info("Select benz");
        return this;
    }

    public PracticePage isBenzSelected() {
        assertTrue(benzOption.isSelected());
        log.info("Benz selected");
        return this;
    }

    public PracticePage selectApple() {
        selectByText(multipleSelect, "Apple");
        log.info("Select Apple");
        return this;
    }

    public PracticePage isAppleSelected() {
        assertTrue(appleOption.isSelected());
        log.info("Apple selected");
        return this;
    }

    public PracticePage deselectApple() {
        deselectByText(multipleSelect, "Apple");
        log.info("deselect Apple");
        return this;
    }

    public PracticePage isAppleDeselected() {
        assertFalse(appleOption.isSelected());
        log.info("Apple deselected");
        return this;
    }

    public PracticePage selectOrange() {
        selectByText(multipleSelect, "Orange");
        log.info("Select Orange");
        return this;
    }

    public PracticePage isOrangeSelected() {
        assertTrue(orangeOption.isSelected());
        log.info("Orange selected");
        return this;
    }

    public PracticePage selectPeach() {
        selectByText(multipleSelect, "Peach");
        log.info("Select Peach");
        return this;
    }

    public PracticePage isPeachSelected() {
        assertTrue(peachOption.isSelected());
        log.info("Peach selected");
        return this;
    }

    public CoursesPage clickAllCourses() {
        clickElement(courses);
        return new CoursesPage(driver);
    }

    public CoursesPage clickNewTab() {
        currentWindow = driver.getWindowHandle();
        clickElement(opentab);

        for (var handler : driver.getWindowHandles()) {
            driver.switchTo().window(handler);
        }

        return new CoursesPage(driver);
    }

    public static String getCurrentWindow() {
        return currentWindow;
    }
}
