package tests;

import base.BasePage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.hepsiburadaPages.HomePage;

public class TestHB extends BasePage {


    @ParameterizedTest
    @ValueSource(strings = {"Tablet", "Samsung"})
    public void searchKeyword(String product) throws InterruptedException {
        new HomePage(driver)
                .typeSearchKeyword(product)
                .clickSearch()
                .assertResultBar(product)
                .clickSortingBox()
                .clickSortToHigherPrice()
                .moveToProduct(0)
                .clickOnTheProduct(0)
                .clickAddToCard()
                .waitInvisibleOfModal()
                .assertReturnShopping()
                .assertGotToCardBtn()
                .assertSlickSlideIsPresent()
                .clickReturnShopping()
                .assertURL()
                .assertTitle()
                .assertH1();
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
