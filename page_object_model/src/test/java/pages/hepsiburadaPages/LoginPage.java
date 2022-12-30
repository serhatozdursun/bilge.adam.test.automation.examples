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

public class LoginPage extends BaseTest {
    private WebDriver driver;
    private final Logger log = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        var ajax = new AjaxElementLocatorFactory(driver, getWAIT_TIME());
        PageFactory.initElements(ajax, this);
        log.info("HB home page is loaded");
    }

    @FindBy(id = "txtUserName")
    private WebElement txtUser;

    @FindBy(id = "btnLogin")
    private WebElement btnLogin;

    public LoginPage typeUserName() {
        sendKeysToWebElement(txtUser, "05368361407");
        return this;
    }

    public LoginPage pressEnterOnLogin() {
        sendEnterToWebElement(btnLogin);
        return this;
    }


}
