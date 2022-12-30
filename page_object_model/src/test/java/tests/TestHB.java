package tests;

import base.BasePage;
import org.junit.jupiter.api.Test;
import pages.hepsiburadaPages.HomePage;

public class TestHB extends BasePage {

    @Test
    public void searchKeyword() throws InterruptedException {
        new HomePage(driver)
                .typeSearchKeyword("Tablet")
                .clickSearch()
                .assertResultBar("Tablet")
                .clickSortingBox()
                .clickSortToHigherPrice()
                .moveToProduct(0)
                .clickOnTheProduct(0)
                .clickAddToCard();
    }

    @Test
    public void goLoginPage() {
        new HomePage(driver)
                .moveToAccount()
                .clickLogin()
                .typeUserName()
                .pressEnterOnLogin();
    }
}
