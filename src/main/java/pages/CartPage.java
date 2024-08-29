package pages;

import enums.ElementTypes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

public class CartPage extends BasePage{

    public CartPage(WebDriver driver){
        super(driver);
    }

    private final Map<String, By> textLocators = Map.of(
            "Sonyvaioi5", By.xpath("//*[@id='tbodyid']/tr/td[2]")
    );

    public String seeVisibleText(String element){
        By locator = getTextLocator(element);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public By getTextLocator(String name) {
        return getLocator(textLocators, name, ElementTypes.TEXT);
    }
}
