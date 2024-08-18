package common;

import elements.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.WebDriverUtils;

import java.time.Duration;

public class CommonSteps {

    WebDriverUtils webDriverUtils = new WebDriverUtils();
    ButtonElements buttonElements = new ButtonElements();
    TextElements textElements = new TextElements();
    ImageElements imageElements = new ImageElements();
    FieldElements fieldElements = new FieldElements();
    DropboxElements dropboxElements = new DropboxElements();
    private static final Logger log = LogManager.getLogger(CommonSteps.class);

    @Given("^User lunch browser and navigate to the Web Page$")
    public void userNavigateToTheWebPage() throws Exception {
        webDriverUtils.getBrowser();
    }

    @Given("^User verify the Home Page load successfully$")
    public void userVerifyHomePage() {
        String elementToSearch = "imageHomePage";
        String element = imageElements.getElement(elementToSearch);
        boolean imageHomePageDisplayed = webDriverUtils.getDriver().findElement(By.xpath(element)).isDisplayed();

        Assert.assertTrue(imageHomePageDisplayed);
    }

    @When("^User click on '(.*)' button$")
    public void userClicksOn(String button) {
        String elementToClick = buttonElements.getElement(button);

        new WebDriverWait(webDriverUtils.getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath(elementToClick))).click();
    }

    @Then("^User sees the '(.*)' text$")
    public void userSeeTextIn(String text) {
        String elementToLookFor = textElements.getElement(text);
        String elementText = new WebDriverWait(webDriverUtils.getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementToLookFor))).getText();

        Assert.assertTrue(elementText.contains(text));
    }

    @When("^User enters the (.*): (.*)$")
    public void userEnterInformationInField(String field, String value) {
        String elementToLookFor = fieldElements.getElement(field);

        new WebDriverWait(webDriverUtils.getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementToLookFor))).sendKeys(value);
    }

    @When("^User selects as (.*): (.*)$")
    public void userSelectsInformation(String dropbox, String value) {
        String elementToLookFor = dropboxElements.getElement(dropbox);
        Select comboBox = new Select(webDriverUtils.getDriver().findElement(By.xpath(elementToLookFor)));
        comboBox.selectByVisibleText(value);
    }

    @When("^User click on Ok button pop up")
    public void clickOnPopUp(){
        webDriverUtils.clickBrowser();
    }

    @When("^User upload the file clicking in (.*) button")
    public void userUploadAFile(String buttonUpload){
        String elementToLookFor = buttonElements.getElement(buttonUpload);
        webDriverUtils.getDriver().findElement(By.xpath(elementToLookFor)).sendKeys(System.getProperty("user.dir")+"/First Steps.txt");
    }

    @When("^User selects the (.*)")
    public void userSelectElement(String element){
        String elementToClick = buttonElements.getElement(element);
        webDriverUtils.getDriver().findElement(By.xpath(elementToClick)).click();
    }

    @When("^User enters the (.*) credential in (.*)")
    public void userEntersCredentials(String value, String field ){
        String elementToLookFor = fieldElements.getElement(field);
        String credentialValue = WebDriverUtils.getProperty(value,"credentials");

        new WebDriverWait(webDriverUtils.getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementToLookFor))).sendKeys(credentialValue);
    }
}