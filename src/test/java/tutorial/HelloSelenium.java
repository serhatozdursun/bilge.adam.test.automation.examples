package tutorial;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSelenium {
    WebDriver driver;
    @BeforeAll
    public static void beforeAll() {
        System.out.println("Testler Basladi");
    }

    @BeforeEach
    public void beforeEach()  {
        driver = new ChromeDriver();
        driver.get("https://www.hepsiburada.com");
        driver.manage().window().fullscreen();
    }

    @Test
    public void test1() {
        System.out.println("test basarili");
    }

    @Test
    public void test2() {
        System.out.println("test basarili");
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
        System.out.println("test tamamlandi");
    }
}
