package pages;

import enums.ElementTypes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.WebDriverUtils;

import java.util.Map;

import static enums.WaitStrategy.*;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//*[@id='nava']/img")
    private WebElement imageHomePage;
    @FindBy(xpath = "//*[@id='nameofuser']")
    private WebElement welcomeText;

    private final Map<String,By> fieldLocators = Map.of(
            "signUpUsername", By.xpath("//*[@id='sign-username']"),
            "signUpPassword", By.xpath("//*[@id='sign-password']"),
            "signInUsername", By.xpath("//*[@id='loginusername']"),
            "signInPassword", By.xpath("//*[@id='loginpassword']")
    );

    private final Map<String, By> buttonLocators = Map.of(
            "signUp", By.xpath("//*[@id='signin2']"),
            "signUpAccept", By.xpath("//*[@id='signInModal']/div/div/div[3]/button[2]"),
            "signIn", By.xpath("//*[@id='login2']")
    );

    public boolean isTitleDisplayed(){
        return wait.until(ExpectedConditions.visibilityOf(imageHomePage)).isDisplayed();
    }

    public String getTitleText(){
        return wait.until(ExpectedConditions.visibilityOf(welcomeText)).getText();
    }

    public void clickButton(String element){
        By locator = getButtonLocator(element);
        click(locator,CLICKABLE,element);
    }

    public void setCredentials(String value, String field){
        String credential = WebDriverUtils.getCredential(value);
        By locator = getFieldLocator(value);
        sendKeys(locator, credential, PRESENCE, field);
    }

    public By getFieldLocator(String name) {
        return getLocator(fieldLocators, name, ElementTypes.FIELD);
    }
    public By getButtonLocator(String name) {
        return getLocator(buttonLocators, name, ElementTypes.BUTTON);
    }

    /**
     * This method allows to unify if locator is null.
     * Also allows to trait in a more generic way the getLocator.
     * @param locatorMap
     * @param name
     * @param type
     * @return locator
     */
    private By getLocator(Map<String, By> locatorMap, String name, ElementTypes type){
        By locator = locatorMap.get(name);

        if(locator == null){
            log.error("Not valid {} locator: {}", type.name().toLowerCase(), name);
            throw new IllegalArgumentException("Invalid " + type.name().toLowerCase() + ": " + name);
        }

        return locator;
    }

    public void clickPopUp(){
        waitForAlertAndAcceptIt();
        driver.switchTo().defaultContent();
    }
}
