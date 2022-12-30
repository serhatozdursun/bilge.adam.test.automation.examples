package base;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.time.Duration;

public class BasePage {
    private static final Logger log = LogManager.getLogger(BasePage.class);
    protected static WebDriver driver;
    private static ChromeDriverService service;

    @BeforeAll
    public static void beforeAll() {
        service = new ChromeDriverService.Builder()
                .usingAnyFreePort()
                .build();
        try {
            service.start();
            log.info("Service start");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void beforeEach() {
        var option = new ChromeOptions();
        option.addArguments("--start-fullscreen");
        driver = new RemoteWebDriver(service.getUrl(), option);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(15));
        log.info("Browser started");
        driver.get("https://www.hepsiburada.com/");
    }

    @After
    public void afterEach() {
        if (driver != null) {
            driver.close();
            driver.quit();
            log.info("Browser quit");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @AfterAll
    public static void tearDown() {
        try {
            service.stop();
            log.info("Service stop");
        }catch (Exception exception){
            log.warn("Service cannot stop an error occurred");
        }
    }

}
