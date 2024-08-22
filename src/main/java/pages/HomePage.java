package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverUtils;

import static enums.WaitStrategy.*;

public class HomePage extends BasePage{
    /*
    Los elementos de la página se definen como variables privadas (por ejemplo, utilizando Page Factory).
    Los métodos públicos encapsulan las interacciones con esos elementos (por ejemplo, hacer clic en un botón,
    escribir en un campo de texto).
     */

    public HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//*[@id='nava']/img")
    private WebElement imageHomePage;
    @FindBy(xpath = "//*[@id='nameofuser']")
    private WebElement welcomeText;
    private final By signUpButton = By.xpath("//*[@id='signin2']");
    private final By signUpAcceptButton = By.xpath("//*[@id='signInModal']/div/div/div[3]/button[2]");
    private final By usernameSignUpField = By.xpath("//*[@id='sign-username']");
    private final By passwordSignUpField = By.xpath("//*[@id='sign-password']");
    private final By usernameSignInField = By.xpath("//*[@id='loginusername']");
    private final By passwordSignInField = By.xpath("//*[@id='loginpassword']");
    private final By logInButton = By.xpath("//*[@id='login2']");

    public boolean isTitleDisplayed(){
        return wait.until(ExpectedConditions.visibilityOf(imageHomePage)).isDisplayed();
    }

    public String getTitleText(){
        return wait.until(ExpectedConditions.visibilityOf(welcomeText)).getText();
    }

    public void clickButton(String element){
        if(element.contains("signUp")){
            click(signUpButton, CLICKABLE, element);
        } else if (element.contains("signUpAccept")){
            click(signUpAcceptButton, CLICKABLE, element);
        } else if (element.contains("logIn")) {
            click(logInButton,CLICKABLE,element);
        }
    }

    public void setCredentials(String value, String field){
        if(field.contains("signUpUsername")){
            String username = WebDriverUtils.getCredential(value);
            sendKeys(usernameSignUpField,username,PRESENCE, field);
        }
        else if (field.contains("signUpPassword")) {
            String password = WebDriverUtils.getCredential(value);
            sendKeys(passwordSignUpField,password,PRESENCE,field);
        }
        else if (field.contains("signInUsername")) {
            String password = WebDriverUtils.getCredential(value);
            sendKeys(usernameSignInField,password,PRESENCE,field);
        }
        else if (field.contains("signInPassword")) {
            String password = WebDriverUtils.getCredential(value);
            sendKeys(passwordSignInField,password,PRESENCE,field);
        }
    }

    public void clickPopUp(){
        waitForAlertAndAcceptIt();
        driver.switchTo().defaultContent();
    }
}
