package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;


public class TestMethods {
    private final  static Logger log = LogManager.getLogger(TestMethods.class);
    WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        log.info("Testler Basladi");
    }

    @BeforeEach
    public void beforeEach() {
        var options = new ChromeOptions();
        options.addArguments("--start-fullscreen");
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        driver = new ChromeDriver(options);
        driver.get("https://courses.letskodeit.com/practice");
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
