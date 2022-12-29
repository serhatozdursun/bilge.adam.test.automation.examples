package tests;

import base.BasePage;
import org.junit.jupiter.api.Test;
import pages.hepsiburadaPages.HomePage;

public class TestHB extends BasePage {

    @Test
    public void searchKeyword(){
        new HomePage(driver)
                .typeSearchKeyword("Tablet")
                .clickSearch()
                .assertResultBar("Tablet ile ilgili 10000+ ürün bulduk");
    }
}
