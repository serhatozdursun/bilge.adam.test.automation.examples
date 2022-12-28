package tutorial;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionExamples {

    @Test
    public void equals() {
        String expectedTitle = "Hepsiburada";
        String actualTitle = "Hepsiburada";
        int x = 100;
        int y = 100;

        if (!expectedTitle.equals(actualTitle)) {
            System.out.println("Test basarisiz");
            //break
        }else {
            System.out.println("Test basarili");
        }

        assertEquals(expectedTitle, actualTitle, "Test basarisiz");
        System.out.println("Test basarili");

        assertEquals(x, y, "Test basarisiz");
        System.out.println("Test basarili");
    }

    @Test
    public void notEquals() {
        String expectedTitle = "Hepsiburada";
        String actualTitle = "Tablet";

        assertNotEquals(expectedTitle, actualTitle, "Test basarisiz");
        System.out.println("Test basarili");
    }

    @Test
    public void assertTrueAssertion() {
        String expectedTitle = "Hepsiburada Bilgisayar ";

        assertTrue(expectedTitle.contains("Hepsiburada"), "Test basarisiz");
        System.out.println("Test basarili");

        assertTrue(!expectedTitle.contains("Sahibinden"), "Test basarisiz");
        System.out.println("Test basarili");
    }
}
