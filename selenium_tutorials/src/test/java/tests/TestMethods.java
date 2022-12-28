package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestMethods {
    private final static Logger log = LogManager.getLogger(TestMethods.class);
    private WebDriver driver;
    private int WAIT_TIME = 15;

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

    @Test void select(){
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
