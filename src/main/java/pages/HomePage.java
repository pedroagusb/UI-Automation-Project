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
            "signIn", By.xpath("//*[@id='login2']"),
            "login", By.xpath("//*[@id='logInModal']/div/div/div[3]/button[2]"),
            "laptopSonyVaioi5", By.xpath("//*[@id='tbodyid']/div[8]/div/a"),
            "addToCart", By.xpath("//*[@id='tbodyid']/div[2]/div/a"),
            "cart", By.xpath("//*[@id='navbarExample']/ul/li[4]/a")
    );

    public boolean isTitleDisplayed(){
        return wait.until(ExpectedConditions.visibilityOf(imageHomePage)).isDisplayed();
    }

    public String getWelcomeText(){
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

    public void clickPopUp(){
        waitForAlertAndAcceptIt();
        driver.switchTo().defaultContent();
    }
}
