package stepsDefination;


import hooks.TestHooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.HomePage;


public class HomeStep {
    HomePage home = new HomePage();

    @Given("I open the application")
    public void i_open_the_application() {
        String url = TestHooks.getBaseUrl();
        home.open(url);
    }


    @Then("page title should contain {string}")
    public void page_title_should_contain(String expected) {
        String title = home.getTitle();
        Assert.assertTrue(title.contains(expected), "Title did not contain expected text. Actual: " + title);
    }
}