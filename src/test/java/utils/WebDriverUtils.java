package utils;

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
    public static String getProperty(String property, String config) {

        File filePath = new File(System.getProperty("user.dir") + "/src/test/resources/" + config + ".properties");
        String ret = null;

        try(InputStream inputStream = new FileInputStream(filePath))
        {
            Properties properties = new Properties();
            properties.load(inputStream);
            ret =  properties.getProperty(property);

            if(ret != null && ret.startsWith("/s"))
                ret = System.getProperty("base_dir") + ret;

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
                WebDriverUtils.getProperty("Browser","config"));

        driver.navigate().to(
                WebDriverUtils.getProperty("URL","config"));

        log.info("Browser created and navigating to {}",
                WebDriverUtils.getProperty("URL","config"));
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
