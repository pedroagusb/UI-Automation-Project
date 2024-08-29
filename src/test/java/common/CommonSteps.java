package common;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CartPage;
import pages.HomePage;
import utils.BrowserFactory;
import utils.WebDriverUtils;

public class CommonSteps {

    WebDriverUtils webDriverUtils = new WebDriverUtils();
    private static final Logger log = LogManager.getLogger(CommonSteps.class);

    @BeforeAll
    public static void setUp(){
        log.info("Automation project started");
    }

    @Given("^User lunch browser and navigate to the Web Page$")
    public void userNavigateToTheWebPage() {
        WebDriverUtils.getBrowserAndNavigateTo();
    }

    @Given("^User verify the Home Page load successfully$")
    public void userVerifyHomePage() {
        boolean imageHomePageDisplayed = new HomePage(webDriverUtils.getDriver()).isTitleDisplayed();
        Assert.assertTrue(imageHomePageDisplayed);
    }

    @When("^User click on '(.*)' button$")
    public void userClicksOn(String button) {
        new HomePage(webDriverUtils.getDriver()).clickButton(button);
    }

    @Then("^User sees the 'Welcome' text$")
    public void userSeesWelcomeText() {
        String elementText = new HomePage(webDriverUtils.getDriver()).getWelcomeText();
        Assert.assertEquals(elementText, "Welcome");
    }

    @When("^User click on Ok button pop up")
    public void clickOnPopUp(){
        new HomePage(webDriverUtils.getDriver()).clickPopUp();
    }

    @When("^User selects the (.*)")
    public void userSelectElement(String element){
        new HomePage(webDriverUtils.getDriver()).clickButton(element);
    }

    @When("^User enters the (.*) credential in (.*)")
    public void userEntersCredentials(String value, String field ){
        new HomePage(webDriverUtils.getDriver()).setCredentials(value,field);
    }

    @Then("^User sees the '(.*)' text in Cart Page$")
    public void userSeesWelcomeText(String text) {
        String elementText = new CartPage(webDriverUtils.getDriver()).seeVisibleText(text);
        Assert.assertEquals(elementText, text);
    }

    @After
    public void tearDown(){
        WebDriver driver = webDriverUtils.getDriver();
        driver.close();
        driver.quit();
    }
}