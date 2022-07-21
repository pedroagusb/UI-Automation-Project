package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;
import java.util.Properties;

public class WebDriverUtils {

    static WebDriver driver;
    public static String getProp(String property, String config) {

        File propFile = new File(System.getProperty("user.dir") + "/src/test/resources/" + config + ".properties");
        String ret = null;

        try(InputStream inputStream = new FileInputStream(propFile)){
            Properties prop = new Properties();
            prop.load(inputStream);
            ret =  prop.getProperty(property);
            ret = ret.startsWith("/s") ? System.getProperty("base_dir") + ret : ret;

        } catch (FileNotFoundException e) {
            System.out.println("The property file was not found");
        } catch (IOException e) {
            System.out.println("Can not read property file");
        }
        return ret;
    }

    public void getBrowser() throws Exception {
        driver = BrowserFactory.getBrowserDriver(
                WebDriverUtils.getProp("Browser","config"));

        driver.navigate().to(
                WebDriverUtils.getProp("URL","config"));
    }

    public void clickBrowser(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent()).accept();
        System.out.println("Popup Clicked!");
        driver.switchTo().defaultContent();
    }

    public WebDriver getDriver(){
        return driver;
    }
    public void TearDown() {
        if (driver != null)
            driver.quit();
    }
}
