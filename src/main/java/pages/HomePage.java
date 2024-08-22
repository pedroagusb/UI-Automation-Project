package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
    private final By usernameField = By.xpath("//*[@id='sign-username']");
    private final By passwordField = By.xpath("//*[@id='sign-password']");

    public boolean isTitleDisplayed(){
        return wait.until(ExpectedConditions.visibilityOf(imageHomePage)).isDisplayed();
    }

    public String getTitleText(){
        return wait.until(ExpectedConditions.visibilityOf(welcomeText)).getText();
    }

    public void clickButton(String element){
        if(element.contains("signUp")){
            click(signUpButton, CLICKABLE, element);
        }
    }

    public void setCredentials(String value, String field){
        if(field.contains("Username")){
            sendKeys(usernameField,value,PRESENCE, field);
        } else if (field.contains("Password")) {
            sendKeys(passwordField,value,PRESENCE,field);
        }
    }
}
