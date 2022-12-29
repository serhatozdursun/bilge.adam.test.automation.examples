package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.WindowType.TAB;
import static org.openqa.selenium.WindowType.WINDOW;


public class TestMethods {
    private final static Logger log = LogManager.getLogger(TestMethods.class);
    private WebDriver driver;
    private int WAIT_TIME = 10;

    @BeforeAll
    public static void beforeAll() {
        log.info("Testler Basladi");
    }

    @BeforeEach
    public void beforeEach() {
        var option = new ChromeOptions();
        option.addArguments("--start-fullscreen");
        driver = new ChromeDriver(option);
        driver.get("https://courses.letskodeit.com/practice");
    }

    // wait objesi olustur
    public WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));
    }
    //wait objesini kullanarak element tiklanabilir olana kadar bekler
    public WebElement clickAbleWait(By by) {
        return getWait().until(ExpectedConditions.elementToBeClickable(by));
    }

    //over loading
    public WebElement clickAbleWait(WebElement elm) {
        return getWait().until(ExpectedConditions.elementToBeClickable(elm));
    }

    //wait objesini kullanarak element sayfada olusana kadar bekler
    public WebElement presentWait(By by) {
        return getWait().until(ExpectedConditions.presenceOfElementLocated(by));
    }

    //wait objesini kullanarak verilen locator a sahip tum elementleri bekler
    public List<WebElement> presentWaitForAll(By by) {
        return getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    //wait objesini kullanarak element sayfada gorunur olana kadar bekler
    public WebElement visibleWait(By by) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    //wait objesini kullanarak element sayfada gorunmez(non-display) olana kadar bekler
    public void invisibleWait(By by) {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public Select getSelect(By by) {
        return new Select(clickAbleWait(by));
    }

    public void selectByIndex(By by, int index) {
        getSelect(by).selectByIndex(index);
    }

    public void selectByValue(By by, String value) {
        getSelect(by).selectByValue(value);
    }

    public void selectByText(By by, String text) {
        getSelect(by).selectByVisibleText(text);
    }

    public void clickElement(By by) {
        clickAbleWait(by).click();
    }

    public void clickElement(WebElement element) {
        clickAbleWait(element).click();
    }

    public Alert switchToAlert() {
        return driver.switchTo().alert();
    }

    public void confirmTheAlert() {
        switchToAlert().accept();
    }

    public void dismissTheAlert() {
        switchToAlert().dismiss();
    }

    public String getAlertText() {
        return switchToAlert().getText();
    }

    public void sendKeysToAlert(String text) {
        switchToAlert().sendKeys(text);
    }

    public void sendKeysToWebElement(By by, String text) {
        clickAbleWait(by).sendKeys(text);
    }

    public Actions getActions() {
        return new Actions(driver);
    }

    public void mouseHover(WebElement element) {
        getActions().moveToElement(element).build().perform();
    }

    @Test
    public void radioButtonExample() {
        clickAbleWait(By.id("bmwradio")).click();
        clickAbleWait(By.id("benzradio")).click();
        clickAbleWait(By.id("hondaradio")).click();

        var radioButtons = driver.findElements(By.cssSelector("#radio-btn-example input"));
        for (var radio : radioButtons) {
            clickAbleWait(radio).click();
            //radio button secildi mi kontrolu
            assertTrue(radio.isSelected(), "Radio buttonlar secilemedi");
        }
    }

    @Test
    public void selectExamples() throws InterruptedException {
        // eger element bir select elementi ise kullanilabilir
        selectByIndex(By.id("carselect"), 0);
        selectByValue(By.id("carselect"), "benz");
        selectByText(By.id("carselect"), "BMW");

        var select = new Select(clickAbleWait(By.id("carselect")));

        for (var options : select.getOptions()) {
            select.selectByVisibleText(options.getText());
            Thread.sleep(600);
        }

        for (var options : select.getOptions()) {
            select.selectByValue(options.getAttribute("value"));
            Thread.sleep(600);
        }

        for (int i = 0; i < select.getOptions().size(); i++) {
            select.selectByIndex(i);
            Thread.sleep(600);
            assertTrue(select.getOptions().get(i).isSelected());
        }
    }

    @Test
    public void multipleSelect() throws InterruptedException {
        var select = getSelect(By.id("multiple-select-example"));
        for (int i = 0; i < select.getOptions().size(); i++) {
            select.selectByIndex(i);
            Thread.sleep(600);
            assertTrue(select.getOptions().get(i).isSelected());
        }

        for (int i = 0; i < select.getOptions().size(); i++) {
            select.deselectByIndex(i);
            Thread.sleep(600);
            assertFalse(select.getOptions().get(i).isSelected());
        }

        // deselect all
        for (int i = 0; i < select.getOptions().size(); i++) {
            select.selectByIndex(i);
            Thread.sleep(600);
            assertTrue(select.getOptions().get(i).isSelected());
        }

        select.deselectAll();

        for (int i = 0; i < select.getOptions().size(); i++) {
            select.selectByIndex(i);
        }

        List<WebElement> selectElement = select.getAllSelectedOptions();
        for (var option : selectElement)
            log.info(option.getText());
    }

    @Test
    void select() {
        //helper metodlar olmadan select
        WebElement element = driver.findElement(By.id("carselect"));
        Select select = new Select(element);
        select.selectByIndex(2);
        assertTrue(select.getOptions().get(2).isSelected());
        select.selectByValue("honda");
        assertEquals("honda",
                select.getFirstSelectedOption().getAttribute("value"));
        select.selectByVisibleText("Honda");
        assertEquals("Honda",
                select.getFirstSelectedOption().getText());
    }

    @Test
    public void checkboxExamples() {
        WebElement bmwcheck = presentWait(By.id("bmwcheck"));
        clickElement(bmwcheck);
        assertTrue(bmwcheck.isSelected());
        clickElement(bmwcheck);
        assertFalse(bmwcheck.isSelected());

        var allCheckBoxes = presentWaitForAll(By.cssSelector("#checkbox-example-div input"));

        for (WebElement checkBox : allCheckBoxes) {
            clickElement(checkBox);
            assertTrue(checkBox.isSelected());
            clickElement(checkBox);
            assertFalse(checkBox.isSelected());
        }
    }

    @Test
    public void switchWindow() throws InterruptedException {
        clickElement(By.id("openwindow"));

        // default window handler store edildi
        var defaultWindowHandler = driver.getWindowHandle();

        // en son acilan window switch
        for (var handler : driver.getWindowHandles()) {
            driver.switchTo().window(handler);
        }
        selectByValue(By.name("categories"), "1604");
        invisibleWait(By.xpath("//h4[contains(text(),'Complete Test Automation Bundle')]"));

        var titleH4 = presentWaitForAll(By.tagName("h4"));
        assertEquals(1, titleH4.size());
        assertEquals("Selenium WebDriver With Java", titleH4.get(0).getText());

        // sonradan aclan window kapatildi
        driver.close();
        // default window'a geri donuldu
        driver.switchTo().window(defaultWindowHandler);

        var switchWindowTitle = presentWait(By.cssSelector("#open-window-example-div fieldset"));
        var titleText = switchWindowTitle.getText();
        assertEquals("Switch Window Example\nOpen Window", titleText);


    }

    // Yeni bir pencere acip islem yapmak icin
    @Test
    public void newWindow() {
        var defaultWindowHandler = driver.getWindowHandle();
        // yeni pencere acar
        driver.switchTo().newWindow(WINDOW);
        driver.get("https://www.hepsiburada.com/");
        //todo yeni pencerede yapilacak islemler buraya
        //acilan pencereyi kapatir
        driver.close();
        //eski pencrere geri doner
        driver.switchTo().window(defaultWindowHandler);
    }

    @Test
    public void newTab() throws InterruptedException {
        var defaultWindowHandler = driver.getWindowHandle();
        // yeni tab acar
        driver.switchTo().newWindow(TAB);
        driver.get("https://www.hepsiburada.com/");
        //todo yeni tab da yapilacak islemler buraya
        //acilan pencereyi kapatir
        driver.close();
        //eski pencrere geri doner
        driver.switchTo().window(defaultWindowHandler);
    }

    @Test
    public void switchAlert() throws InterruptedException {
        clickElement(By.id("alertbtn"));
        assertEquals("Hello , share this practice page and share your knowledge", getAlertText());
        confirmTheAlert();
    }

    @Test
    public void switchAlertAndDismiss() throws InterruptedException {
        clickElement(By.id("confirmbtn"));
        var alert = driver.switchTo().alert();
        assertEquals("Hello , Are you sure you want to confirm?", alert.getText());
        alert.dismiss();
    }

    @Test
    public void switchAlertAndConfirm() throws InterruptedException {
        clickElement(By.id("confirmbtn"));
        var alert = driver.switchTo().alert();
        assertEquals("Hello , Are you sure you want to confirm?", alert.getText());
        alert.accept();
    }

    @Test
    public void table() {
        //xpath le data table yon.
        var table = presentWait(By.id("product"));
        int row = 3;
        int cell = 2;
        var element = table.findElement(By.xpath("((//tr)[" + row + "]/td)[" + cell + "]"));

        log.info(element.getText());
        //diger kullanimlar
        var tds = presentWaitForAll(By.cssSelector("#product td"));
        log.info(tds.get(4).getText());

        var trs = presentWaitForAll(By.cssSelector("#product tr")).get(2);
        log.info(trs.findElements(By.tagName("td")).get(1).getText());
    }

    @Test
    public void sendKeys() throws InterruptedException {
        clickAbleWait(By.id("name")).sendKeys("Serhat");
        clickElement(By.id("confirmbtn"));
        assertEquals("Hello Serhat, Are you sure you want to confirm?", getAlertText());
        confirmTheAlert();
    }

    @Test
    public void disableEnable() {
        clickElement(By.id("disabled-button"));
        assertFalse(presentWait(By.id("enabled-example-input")).isEnabled());
        clickElement(By.id("enabled-button"));
        assertTrue(presentWait(By.id("enabled-example-input")).isEnabled());

       /* clickElement(By.id("disabled-button"));
        sendKeysToWebElement(By.id("enabled-example-input"),"Test");*/
    }

    @Test
    public void hideShow() {
        clickElement(By.id("hide-textbox"));
        invisibleWait(By.id("displayed-text"));
        assertFalse(presentWait(By.id("displayed-text")).isDisplayed());

        clickElement(By.id("show-textbox"));
        visibleWait(By.id("displayed-text"));
        assertTrue(presentWait(By.id("displayed-text")).isDisplayed());
    }
    @Test
    void getRowText() {
        log.info(presentWait(By.xpath("((//tr)[4]/td)[2]")).getText());
    }
    @Test
    public void mouseHoverTest() throws InterruptedException {
        var mouseHoverMenu = presentWait(By.id("mousehover"));
        Actions actions = new Actions(driver);
        assertFalse(presentWait(By.cssSelector("[href='#top']")).isDisplayed());
        Thread.sleep(1000);
        actions
                .moveToElement(mouseHoverMenu)
                .build()
                .perform();
        Thread.sleep(1000);
        assertTrue(driver.findElements(By.cssSelector("[href='#top']")).size() > 0);
        assertTrue(presentWait(By.cssSelector("[href='#top']")).isDisplayed());

    }
    @Test
    public void mouseHoverTest2() {
        assertFalse(presentWait(By.cssSelector("[href='#top']")).isDisplayed());
        var mouseHoverMenu = presentWait(By.id("mousehover"));
        mouseHover(mouseHoverMenu);
        assertTrue(driver.findElements(By.cssSelector("[href='#top']")).size() > 0);
        assertTrue(presentWait(By.cssSelector("[href='#top']")).isDisplayed());

    }
    @Test
    public void iFrame() {
        var iframe = presentWait(By.id("courses-iframe"));
        driver.switchTo().frame(iframe);
        selectByValue(By.name("categories"), "1604");
        invisibleWait(By.xpath("//h4[contains(text(),'Complete Test Automation Bundle')]"));

        var titleH4 = presentWaitForAll(By.tagName("h4"));
        assertEquals(1, titleH4.size());
        assertEquals("Selenium WebDriver With Java", titleH4.get(0).getText());
        driver.switchTo().defaultContent();
        log.info(presentWait(By.xpath("((//tr)[4]/td)[2]")).getText());
    }
    @Test
    public void iFrameIndex() {
        var iframe = presentWait(By.id("courses-iframe"));
        //index ile degistir
        driver.switchTo().frame(0);
        selectByValue(By.name("categories"), "1604");
        invisibleWait(By.xpath("//h4[contains(text(),'Complete Test Automation Bundle')]"));

        var titleH4 = presentWaitForAll(By.tagName("h4"));
        assertEquals(1, titleH4.size());
        assertEquals("Selenium WebDriver With Java", titleH4.get(0).getText());
        driver.switchTo().defaultContent();
        log.info(presentWait(By.xpath("((//tr)[4]/td)[2]")).getText());
    }
    @Test
    public void iFrameNameOrID() {
        //index ile degistir
        driver.switchTo().frame("courses-iframe");
        selectByValue(By.name("categories"), "1604");
        invisibleWait(By.xpath("//h4[contains(text(),'Complete Test Automation Bundle')]"));

        var titleH4 = presentWaitForAll(By.tagName("h4"));
        assertEquals(1, titleH4.size());
        assertEquals("Selenium WebDriver With Java", titleH4.get(0).getText());
        driver.switchTo().defaultContent();
        log.info(presentWait(By.xpath("((//tr)[4]/td)[2]")).getText());
    }
    @AfterEach
    public void afterEach() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
    @AfterAll
    public static void afterAll() {
        log.info("test tamamlandi");
    }
}
