package utils;

import constants.Constants;
import enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WaitFactory {

    private WaitFactory(){

    }

    public static WebElement performExplicitWait(WaitStrategy waitStrategy, By by){
        WebElement element = null;
        if (waitStrategy == WaitStrategy.CLICKABLE) {
            element = new WebDriverWait(BrowserFactory.getDriver(), Constants.getExplicitWait())
                    .until(ExpectedConditions.elementToBeClickable(by));
        }
        else if (waitStrategy == WaitStrategy.PRESENCE) {
            element = new WebDriverWait(BrowserFactory.getDriver(), Constants.getExplicitWait())
                    .until(ExpectedConditions.presenceOfElementLocated(by));
        }
        else if (waitStrategy == WaitStrategy.VISIBLE) {
            element = new WebDriverWait(BrowserFactory.getDriver(), Constants.getExplicitWait())
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
        }
        else if (waitStrategy == WaitStrategy.NONE) {
            System.out.println("Not Waiting for anything");
            element = BrowserFactory.getDriver().findElement(by);
        }
        return element;
    }
}
