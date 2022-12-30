package stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultPage;

import static org.junit.jupiter.api.Assertions.fail;
import static base.BasePage.getDriver;

public class AddToCardStep {
    private HomePage homePage = new HomePage(getDriver());
    private SearchResultPage searchResultPage = new SearchResultPage(getDriver());
    private ProductPage productPage = new ProductPage(getDriver());

    @Given("On HB home page")
    public void onHBHomePage() {
        homePage.assertLoaded();
    }

    @When("I search {string}")
    public void iSearch(String keyword) {
        homePage.typeSearchKeyword(keyword)
                .clickSearch();
    }

    @Then("All {string} related products should be listed")
    public void allRelatedProductsShouldBeListed(String keyword) {
        searchResultPage.assertResultBar(keyword);
    }

    @When("Sort from lowest to highest price")
    public void sortFromLowestToHighestPrice() {

    }

    @And("Click {int} product")
    public void clickProduct(int index) {
    }
}
