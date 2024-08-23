package pages;

import enums.ElementTypes;
import enums.WaitStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserFactory;
import utils.WaitFactory;

import java.time.Duration;
import java.util.Map;

public class BasePage {
    protected static final Logger log = LogManager.getLogger(BasePage.class);
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void click(By by, WaitStrategy waitStrategy, String element){
        WaitFactory.performExplicitWait(waitStrategy, by).click();
        log.info("The element has been clicked: {}", element);
    }

    protected void sendKeys(By by, String valueToSend, WaitStrategy waitStrategy, String element){
        WaitFactory.performExplicitWait(waitStrategy,by).sendKeys(valueToSend);
        log.info("The value {} has been sent to the element: {}", valueToSend, element);
    }

    protected void clear(By by, WaitStrategy waitStrategy, String element){
        WaitFactory.performExplicitWait(waitStrategy, by).clear();
        log.info("The content has been cleared from the element: {}", element);
    }

    protected  void elementLocated(By by, WaitStrategy waitStrategy, String element){
        WaitFactory.performExplicitWait(waitStrategy, by).clear();
        log.info("The content has been located: {}", element);
    }

    protected void waitForAlertAndAcceptIt(){
        wait.until(ExpectedConditions.alertIsPresent()).accept();

        log.info("Alert is present and was accepted");
    }


    /**
     * This method allows to unify if locator is null.
     * Also allows to trait in a more generic way the getLocator.
     * @param locatorMap
     * @param name
     * @param type
     * @return locator
     */
    protected By getLocator(Map<String, By> locatorMap, String name, ElementTypes type){
        By locator = locatorMap.get(name);

        if(locator == null){
            log.error("Not valid {} locator: {}", type.name().toLowerCase(), name);
            throw new IllegalArgumentException("Invalid " + type.name().toLowerCase() + ": " + name);
        }

        return locator;
    }

    protected void captureScreenshot(){ }
}
