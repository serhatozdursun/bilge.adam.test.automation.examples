package Pages;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PracticePage extends BaseTest {
    private WebDriver driver;

    private final Logger log = LogManager.getLogger(PracticePage.class);

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

    public PracticePage isBmwSelected() {
        assertTrue(benzOption.isSelected());
        log.info("Benz selected");
        return this;
    }

    public SupportPage clickSupport() {

        return new SupportPage(driver);
    }
}
