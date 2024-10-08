package utils;

import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebDriverUtils {

    static WebDriver driver = BrowserFactory.getDriver();
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

            log.info("Getting property: {}, from file: {}.properties", property, config);

        } catch (FileNotFoundException e) {
            log.fatal("A fatal error occur trying to read the file {}", e.getMessage(), e);
        } catch (IOException e) {
            log.error("Can not read property file. {}", e.getMessage(), e);
        }
        return ret;
    }

    public static String getCredential(String property){
        return getProperty(property,"credentials");
    }

    public static void getBrowserAndNavigateTo() {
        driver = BrowserFactory.getBrowserDriver(
                WebDriverUtils.getProperty("Browser","config"));

        driver.navigate().to(
                WebDriverUtils.getProperty("URL","config"));

        log.info("Browser created and navigating to {}", driver.getCurrentUrl());
    }

    public String manipulateElement (String elementToManipulate){
        String element = elementToManipulate.substring(0,1).toLowerCase()+elementToManipulate.substring(1);
        return element.replaceAll("\\s","")+"Button";
    }

    public WebDriver getDriver(){
        return driver;
    }
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }

        log.info("Driver closed and quited");
    }
}
