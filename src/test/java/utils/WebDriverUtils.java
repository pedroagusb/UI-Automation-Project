package utils;

import common.CommonSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebDriverUtils {

    static WebDriver driver;
    private static final Logger log = LogManager.getLogger(WebDriverUtils.class);
    public static String getProp(String property, String config) {

        File propFile = new File(System.getProperty("user.dir") + "/src/test/resources/" + config + ".properties");
        String ret = null;

        try(InputStream inputStream = new FileInputStream(propFile)){
            Properties prop = new Properties();
            prop.load(inputStream);
            ret =  prop.getProperty(property);
            ret = ret.startsWith("/s") ? System.getProperty("base_dir") + ret : ret;

            log.info("Getting property: " + property + "from file: " + config + ".properties");

        } catch (FileNotFoundException e) {
            log.fatal("A fatal error occur trying to read the file", e);
        } catch (IOException e) {
            log.error("Can not read property file. {}", e.getMessage(), e);
        }
        return ret;
    }

    public void getBrowser() throws Exception {
        driver = BrowserFactory.getBrowserDriver(
                WebDriverUtils.getProp("Browser","config"));

        driver.navigate().to(
                WebDriverUtils.getProp("URL","config"));

        log.info("Browser created and navigating to {}",
                WebDriverUtils.getProp("URL","config"));
    }

    public void clickBrowser(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent()).accept();
        driver.switchTo().defaultContent();

        log.info("Popup clicked and accepted");
    }

    public WebDriver getDriver(){
        return driver;
    }
    public void TearDown() {
        if (driver != null){
            driver.close();
            driver.quit();

            log.info("Driver closed and quited");
        }
    }
}
