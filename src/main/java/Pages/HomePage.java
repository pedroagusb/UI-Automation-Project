package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    /*
    Los elementos de la página se definen como variables privadas (por ejemplo, utilizando Page Factory).
    Los métodos públicos encapsulan las interacciones con esos elementos (por ejemplo, hacer clic en un botón,
    escribir en un campo de texto).
     */

    @FindBy(xpath = "//*[@id='nava']/img")
    private WebElement imageHomePage;

    @FindBy(id = "username")
    private WebElement usernameField;


}
