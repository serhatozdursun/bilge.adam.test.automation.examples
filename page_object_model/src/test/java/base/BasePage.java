package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;

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
            log.info("service start");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void beforeEach() {
        var option = new ChromeOptions();
        option.addArguments("--start-fullscreen");
        driver = new RemoteWebDriver(service.getUrl(), option);
        log.info("Browser started");
        driver.get("https://courses.letskodeit.com/practice");
    }

    @AfterEach
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
        service.stop();
        log.info("service stop");
    }

}
