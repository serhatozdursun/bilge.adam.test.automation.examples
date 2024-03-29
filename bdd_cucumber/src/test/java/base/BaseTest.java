package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static base.BasePage.getDriver;

public class BaseTest {
    private WebDriver driver;
    private static int WAIT_TIME = 10;

    public BaseTest() {
        this.driver = getDriver();
    }

    // wait objesi olustur
    protected WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));
    }

    //wait objesini kullanarak element tiklanabilir olana kadar bekler
    protected WebElement clickAbleWait(By by) {
        return getWait().until(ExpectedConditions.elementToBeClickable(by));
    }

    //over loading
    protected WebElement clickAbleWait(WebElement elm) {
        return getWait().until(ExpectedConditions.elementToBeClickable(elm));
    }

    //wait objesini kullanarak element sayfada olusana kadar bekler
    protected WebElement presentWait(By by) {
        return getWait().until(ExpectedConditions.presenceOfElementLocated(by));
    }

    //wait objesini kullanarak verilen locator a sahip tum elementleri bekler
    protected List<WebElement> presentWaitForAll(By by) {
        return getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    //wait objesini kullanarak element sayfada gorunur olana kadar bekler
    protected WebElement visibleWait(By by) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected WebElement visibleWait(WebElement element) {
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    //wait objesini kullanarak element sayfada gorunmez(non-display) olana kadar bekler
    protected void invisibleWait(By by) {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected void invisibleWait(WebElement element) {
        getWait().until(ExpectedConditions.invisibilityOf(element));
    }

    protected Select getSelect(By by) {
        return new Select(clickAbleWait(by));
    }

    protected Select getSelect(WebElement element) {
        return new Select(clickAbleWait(element));
    }

    protected void selectByIndex(By by, int index) {
        getSelect(by).selectByIndex(index);
    }

    protected void selectByValue(By by, String value) {
        getSelect(by).selectByValue(value);
    }

    protected void selectByValue(WebElement element, String value) {
        getSelect(element).selectByValue(value);
    }

    protected void selectByText(By by, String text) {
        getSelect(by).selectByVisibleText(text);
    }

    protected void selectByText(WebElement element, String text) {
        getSelect(element).selectByVisibleText(text);
    }

    protected void deselectByText(WebElement element, String text) {
        getSelect(element).deselectByVisibleText(text);
    }

    protected void deselectByValue(WebElement element, String text) {
        getSelect(element).deselectByValue(text);
    }

    protected void clickElement(By by) {
        clickAbleWait(by).click();
    }

    protected void clickElement(WebElement element) {
        scrollIntoViewIfNeed(element);
        clickAbleWait(element).click();
    }

    private JavascriptExecutor js() {
        return (JavascriptExecutor) driver;
    }

    protected void scrollIntoViewIfNeed(WebElement element){
        js().executeScript("arguments[0].scrollIntoViewIfNeeded()",element);
    }
    protected void clickWithJS(WebElement element) {
        js().executeScript("arguments[0].click()", element);
    }

    protected Alert switchToAlert() {
        return driver.switchTo().alert();
    }

    protected void confirmTheAlert() {
        switchToAlert().accept();
    }

    protected void dismissTheAlert() {
        switchToAlert().dismiss();
    }

    public String getAlertText() {
        return switchToAlert().getText();
    }

    public String getElementText(WebElement element) {
        return visibleWait(element).getText();
    }

    protected void sendKeysToAlert(String text) {
        switchToAlert().sendKeys(text);
    }

    protected void sendKeysToWebElement(By by, CharSequence keysToSend) {
        clickAbleWait(by).sendKeys(keysToSend);
    }

    protected void sendEnterToWebElement(WebElement element) {
        clickAbleWait(element).sendKeys(Keys.ENTER);
    }

    protected void sendKeysToWebElement(WebElement element, String text) {
        clickAbleWait(element).sendKeys(text);
    }

    protected Actions getActions() {
        return new Actions(driver);
    }

    protected void mouseHover(WebElement element) {
        getActions().moveToElement(element).build().perform();
    }

    protected static int getWAIT_TIME() {
        return WAIT_TIME;
    }

    protected void switchToNewTab(){
        for (var handler : driver.getWindowHandles()) {
            driver.switchTo().window(handler);
        }
    }

    protected void openNewTab() {
        driver.switchTo().newWindow(WindowType.TAB);
    }
    protected void openNewWindow() {
        driver.switchTo().newWindow(WindowType.WINDOW);
    }
}
